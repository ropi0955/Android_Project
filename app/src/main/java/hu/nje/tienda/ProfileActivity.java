package hu.nje.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hu.nje.tienda.pages.ChangePasswordActivity;

public class ProfileActivity extends AppCompatActivity {
    private FloatingActionButton backMainActivityButton2;
    private FloatingActionButton modifyProfileButton;//valahogy az összes textboxot konténerbe kéne tenni és akkor egybe szerkeszhetővé lehetne tenni..
    private Button changePasswordbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        backMainActivityButton2 = findViewById(R.id.backMainActivityButton2);
        backMainActivityButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMainActivity2();
            }
        });
        //valszeg nem így kell majd ha azt akarjuk hogy szerkessze az oldal adatait
        //meg kell tudni milyen onclicklistener tud ilyet
        modifyProfileButton = findViewById(R.id.modifyProfileButton);
        modifyProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyProfileButton();
            }
        });
        changePasswordbutton = findViewById(R.id.changePasswordbutton);
        changePasswordbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePasswordbutton();
            }
        });
    }
    private void backMainActivity2() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivities(new Intent[]{intent});
    }

    //ez itt nem tudom kell-e?!
    private void modifyProfileButton() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivities(new Intent[]{intent});
    }
    private void changePasswordbutton() {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivities(new Intent[]{intent});
    }
}