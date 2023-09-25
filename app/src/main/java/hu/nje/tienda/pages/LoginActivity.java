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

import hu.nje.tienda.R;
import hu.nje.tienda.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {


    TextView go_to_reg;
    EditText email, password;
    Button signin;
    DatabaseHelper MyDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        signin = findViewById(R.id.login_button);
        MyDatabase = new DatabaseHelper(this);
        go_to_reg = findViewById(R.id.signupRedirectText);

        go_to_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_email = email.getText().toString();
                String user_password = password.getText().toString();

                if (TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_password)){
                    Toast.makeText(LoginActivity.this, "Nem töltött ki minden mezőt", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuseremailpass = MyDatabase.checkEmailAndPassword(user_email, user_password);
                    if (checkuseremailpass){
                        Toast.makeText(LoginActivity.this, "Sikeres bejelentkezés", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Sikertelen bejelentkezés", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}