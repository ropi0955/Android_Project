package hu.nje.tienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileActivity extends AppCompatActivity {

    private FloatingActionButton backMainActivityButton2;

    private TextView usernameText;
    private EditText textFullName;
    private EditText textEmailAddress;
    private EditText textPhone;
    private EditText textPostalAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String adat = intent.getStringExtra("fh");

        String[] userDatas = adat.split("\\|");

        usernameText = findViewById(R.id.usernameText);
        textFullName = findViewById(R.id.textFullName);
        textEmailAddress = findViewById(R.id.textEmailAddress);
        textPhone = findViewById(R.id.textPhone);
        textPostalAddress = findViewById(R.id.textPostalAddress);

       String user_username = userDatas[6];
        String user_fullName = userDatas[2] + " " + userDatas[1];
        String user_emailAddress = userDatas[0];
        String user_phone = userDatas[7];
        String user_postalAddress = userDatas[3] + ", " + userDatas[4] + " " + userDatas[5];

        usernameText.setText(user_username);
        textFullName.setText(user_fullName);
        textEmailAddress.setText(user_emailAddress);
        textPhone.setText(user_phone);
        textPostalAddress.setText(user_postalAddress);


        backMainActivityButton2 = findViewById(R.id.backMainActivityButton2);
        backMainActivityButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMainActivityButton2();
            }
        });

    }
    private void backMainActivityButton2() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivities(new Intent[]{intent});
    }
}
