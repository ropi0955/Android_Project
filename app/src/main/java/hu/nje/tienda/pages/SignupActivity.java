package hu.nje.tienda.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

import hu.nje.tienda.MainActivity;
import hu.nje.tienda.R;
import hu.nje.tienda.database.DatabaseHelper;
import hu.nje.tienda.services.RegisterCheckService;
import hu.nje.tienda.services.UserDatasService;

public class SignupActivity extends AppCompatActivity {

    // TODO REAMDE
    /* A Regisztráció ablak */

    public RegisterCheckService regsrv = new RegisterCheckService();
    CheckBox data_manage;
    TextView go_to_login;
    EditText email, last_name, first_name, city, street_name, street_number, user_name, password, repassword, phone_number, birthday;
    Button signup, dataopen;
    DatabaseHelper MyDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Input mezők
        email = findViewById(R.id.signup_email);
        last_name = findViewById(R.id.signup_lastname);
        first_name = findViewById(R.id.signup_firstname);
        city = findViewById(R.id.signup_city);
        street_name = findViewById(R.id.signup_street_name);
        street_number = findViewById(R.id.signup_street_number);
        user_name = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);
        repassword = findViewById(R.id.signup_repassword);
        phone_number = findViewById(R.id.phone_number);
        birthday = findViewById(R.id.birthday);
        data_manage = findViewById(R.id.data_checkbox);
        MyDatabase = new DatabaseHelper(this);

        //Gomb(ok)
        signup = findViewById(R.id.signup_button);
        go_to_login = findViewById(R.id.loginRedirectText);
        dataopen = findViewById(R.id.data_open);

        dataopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Erste adatkezelési tájékoztató (de most sajátnak tekintjük)
                String url = "https://cdn0.erstegroup.com/content/dam/hu/ebh/www_erstebank_hu/adatkezelesi/EBH/Adatkezelesi_tajekoztato_20230925.pdf?forceDownload=1";
                Uri uri = Uri.parse(url);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        // Alul a szöveg amely átirányít a bejelentkezéshez
        go_to_login.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        // Regisztráció gombra kattintva
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String user_email = email.getText().toString();
                String user_last_name = last_name.getText().toString();
                String user_first_name = first_name.getText().toString();
                String user_city = city.getText().toString();
                String user_street_name = street_name.getText().toString();
                String user_street_number = street_number.getText().toString();
                String user_username = user_name.getText().toString();
                String user_password = password.getText().toString();
                String repass = repassword.getText().toString();
                String user_phone_number = phone_number.getText().toString();
                String user_birthday = birthday.getText().toString();
                Boolean checkValues = regsrv.checkValueIsNull(user_email,user_last_name,user_first_name,user_city,user_street_name,user_street_number,user_username,user_password,repass, user_phone_number, user_birthday);

                if (checkValues){
                    if (user_password.equals(repass)){
                        if (regsrv.securePassCheck(user_password)){
                            if (regsrv.containAtCharacter(user_email)){
                                if (regsrv.securePhoneNumber(user_phone_number)){
                                    if (regsrv.secureBirthday(user_birthday)){
                                        if (regsrv.over16(user_birthday)){
                                            Boolean checkemail = MyDatabase.checkEmail(user_email);
                                            if (!checkemail){
                                                if (data_manage.isChecked()){
                                                    Boolean insert = MyDatabase.insertData(user_email,user_password,user_last_name,user_first_name,user_username,user_city,user_street_name,user_street_number, user_phone_number, user_birthday);
                                                    if (insert){
                                                        Toast.makeText(SignupActivity.this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                        startActivity(intent);
                                                    }else{
                                                        Toast.makeText(SignupActivity.this, "Sikertelen regisztráció", Toast.LENGTH_SHORT).show();
                                                    }
                                                }else{
                                                    Toast.makeText(SignupActivity.this, "Nem fogadta el az adatkezelési tájékoztatót", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(SignupActivity.this, "Ez az e-mail cím már foglalt", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(SignupActivity.this, "El kell érned a 16 éves életkort", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(SignupActivity.this, "Nem jó születési dátum formátum", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(SignupActivity.this, "Helyes születési dátum formátum: yyyy/mm(max: 12)/dd(max. 31)", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(SignupActivity.this, "Nem jó telefonszám formátum", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(SignupActivity.this, "Helyes telefonszám formátum pl: 06123456789", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(SignupActivity.this, "Az Email címe nem tartalmaz '@' szimbólumot", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignupActivity.this, "A jelszava nem elég biztonságos", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "A jelszavak nem egyeznek", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignupActivity.this,"Nem töltött ki minden mezőt", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}