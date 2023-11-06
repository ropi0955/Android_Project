package hu.nje.tienda.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hu.nje.tienda.MainActivity;
import hu.nje.tienda.R;

public class SalesActivity extends AppCompatActivity {

    private FloatingActionButton backMainActivityButton3;
    EditText OutgoingM, IncomeM, SummaM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        int resource = 1500000;
        IncomeM = findViewById(R.id.IncomeM);
        IncomeM.setText(String.valueOf(resource));

        if (getIntent().hasExtra("sumAsset")) {
            int sumAsset = getIntent().getIntExtra("sumAsset", 0);

            OutgoingM = findViewById(R.id.OutgoingM);
            OutgoingM.setText(String.valueOf(sumAsset));

            SummaM = findViewById(R.id.SummaM);
            int extraction = resource - sumAsset;
            SummaM.setText(String.valueOf(extraction));
        }

        backMainActivityButton3 = findViewById(R.id.backMainActivityButton3);
        backMainActivityButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMainActivity3();
            }
        });

    }

    private void backMainActivity3() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

