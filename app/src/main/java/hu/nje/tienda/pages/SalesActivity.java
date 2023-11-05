package hu.nje.tienda.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hu.nje.tienda.MainActivity;
import hu.nje.tienda.R;

public class SalesActivity extends AppCompatActivity {

    private FloatingActionButton backMainActivityButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        backMainActivityButton3 = findViewById(R.id.backMainActivityButton3);
        backMainActivityButton3.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           backMainActivity3();
                                                       }
                                                   });

                // Retrieve the total value from the intent
        int totalSum = getIntent().getIntExtra(" ", 0);

        // Now you can use the totalValue in this activity
        // For example, you can display it in a TextView
        EditText totalValueEditText = findViewById(R.id.OutgoingM);
        totalValueEditText.setText(" "+ totalSum);
    }

    private void backMainActivity3() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

