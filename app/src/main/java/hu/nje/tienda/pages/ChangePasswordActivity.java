package hu.nje.tienda.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hu.nje.tienda.ProfileActivity;
import hu.nje.tienda.R;

public class ChangePasswordActivity extends AppCompatActivity {
    private Button submitPwButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        submitPwButton = findViewById(R.id.submitPwButton);
        submitPwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitPwButton();
            }
        });
    }
    private void submitPwButton() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivities(new Intent[]{intent});
    }
}