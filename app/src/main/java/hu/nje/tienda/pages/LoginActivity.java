package hu.nje.tienda.pages;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hu.nje.tienda.MainActivity;
import hu.nje.tienda.R;
import hu.nje.tienda.database.DatabaseHelper;
import hu.nje.tienda.services.UserDatasService;

public class LoginActivity extends AppCompatActivity {

    // TODO REAMDE
    /* A Bejelentkezés ablak */

    TextView go_to_reg;
    EditText email, password;
    Button signin;
    DatabaseHelper MyDatabase;

    public UserDatasService usrv = new UserDatasService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        signin = findViewById(R.id.login_button);
        MyDatabase = new DatabaseHelper(this);
        go_to_reg = findViewById(R.id.signupRedirectText);

        // Alul a szöveg amely átirányít a regisztrációhoz
        go_to_reg.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        // Bejelentkezés gombra kattintva
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                /*SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user_email", user_email);
                editor.apply();*/

                if (TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password)){
                    Toast.makeText(LoginActivity.this, "Nem töltött ki minden mezőt", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuseremailpass = MyDatabase.checkEmailAndPassword(user_email, user_password);
                    if (checkuseremailpass){
                        MyDatabase.setDatas(user_email);
                        Toast.makeText(LoginActivity.this, "Sikeres bejelentkezés", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Sikertelen bejelentkezés", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}