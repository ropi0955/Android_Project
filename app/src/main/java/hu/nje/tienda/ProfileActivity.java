package hu.nje.tienda;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.nje.tienda.database.DatabaseHelper;
import hu.nje.tienda.services.UserDatasService;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private UserDatasService userDataService;

    private TextView textFullName;
    private EditText textEmailAddress;
    private EditText textPhone;
    private EditText textPostalAddress;
    private EditText textPostCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databaseHelper = new DatabaseHelper(this);
        userDataService = new UserDatasService();

        textFullName = findViewById(R.id.textFullName);
        textEmailAddress = findViewById(R.id.textEmailAddress);
        textPhone = findViewById(R.id.textPhone);
        textPostalAddress = findViewById(R.id.textPostalAddress);
        textPostCode = findViewById(R.id.textPostCode);

        String userEmail = userDataService.getUser_email_address();
        databaseHelper.setDatas(userEmail);

        textFullName.setText(userDataService.getUser_first_name() + " " + userDataService.getUser_last_name());
        textEmailAddress.setText(userEmail);
        textPhone.setText(userDataService.getPhone_number());
        textPostalAddress.setText(userDataService.getUser_city() + ", " + userDataService.getUser_street() + ", " + userDataService.getUser_street_number());
        textPostCode.setText("irányítószám"); // Itt az irányítószámot kell beállítani a megfelelő adat alapján
    }
}
