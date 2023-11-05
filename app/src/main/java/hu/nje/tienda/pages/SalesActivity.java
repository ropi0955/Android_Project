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


        int totalAssets = getIntent().getIntExtra("totalAssets ",0);
        Log.d("SalesActivity","Received totalAssets:" + totalAssets);


        EditText totalValueEditText = findViewById(R.id.OutgoingM);
        totalValueEditText.setText(" "+ totalAssets);
    }

    private void backMainActivity3() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

