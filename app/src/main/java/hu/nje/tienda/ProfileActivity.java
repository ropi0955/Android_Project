package hu.nje.tienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hu.nje.tienda.services.UserDatasService;

public class ProfileActivity extends AppCompatActivity {

    private FloatingActionButton backMainActivityButton2;

    public UserDatasService usrv;

    private TextView usernameText;
    private EditText textFullName;
    private EditText textEmailAddress;
    private EditText textPhone;
    private EditText textPostalAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usrv = new UserDatasService();

        usernameText = findViewById(R.id.usernameText);
        textFullName = findViewById(R.id.textFullName);
        textEmailAddress = findViewById(R.id.textEmailAddress);
        textPhone = findViewById(R.id.textPhone);
        textPostalAddress = findViewById(R.id.textPostalAddress);

        String user_username = usrv.getUser_name();
        String user_fullName = usrv.getUser_first_name() + " " + usrv.getUser_last_name();
        String user_emailAddress = usrv.getUser_email_address();
        String user_phone = usrv.getPhone_number();
        String user_postalAddress = usrv.getUser_city() + ", " + usrv.getUser_street() + " " + usrv.getUser_street_number();

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
