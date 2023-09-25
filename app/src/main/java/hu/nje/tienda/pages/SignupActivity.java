package hu.nje.tienda.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hu.nje.tienda.MainActivity;
import hu.nje.tienda.R;
import hu.nje.tienda.database.DatabaseHelper;
import hu.nje.tienda.services.RegisterCheckService;

public class SignupActivity extends AppCompatActivity {

    public RegisterCheckService regsrv = new RegisterCheckService();

    TextView go_to_login;
    EditText email, last_name, first_name, city, street_name, street_number, user_name, password, repassword;
    Button signup;
    DatabaseHelper MyDatabase;
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
        MyDatabase = new DatabaseHelper(this);

        //Gomb(ok)
        signup = findViewById(R.id.signup_button);
        go_to_login = findViewById(R.id.loginRedirectText);

        go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

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
                Boolean checkValues = regsrv.checkValueIsNull(user_email,user_last_name,user_first_name,user_city,user_street_name,user_street_number,user_username,user_password,repass);

                if (checkValues){
                    if (user_password.equals(repass)){
                        if (regsrv.securePassCheck(user_password)){
                            if (regsrv.containAtCharacter(user_email)){
                                Boolean checkemail = MyDatabase.checkEmail(user_email);
                                if (!checkemail){
                                    Boolean insert = MyDatabase.insertData(user_email,user_password,user_last_name,user_first_name,user_username,user_city,user_street_name,user_street_number);
                                    if (insert){
                                        Toast.makeText(SignupActivity.this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(SignupActivity.this, "Sikertelen regisztráció", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(SignupActivity.this, "Ez az e-mail cím már foglalt", Toast.LENGTH_SHORT).show();
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