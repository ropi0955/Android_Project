package hu.nje.tienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ProductAdd extends AppCompatActivity {
    Button homeButton;
    EditText pr_name_text, pr_quantity_text, pr_price_text, pr_description_text;
    Button buttonSave2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        homeButton = findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeactivity();
            }
        });
        pr_name_text = findViewById(R.id.pr_name_text);
        pr_quantity_text = findViewById(R.id.pr_quantity_text);
        pr_price_text = findViewById(R.id.pr_price_text);
        pr_description_text = findViewById(R.id.pr_description_text);
        buttonSave2 = findViewById(R.id.buttonSave2);
        buttonSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ProductAdd.this);
                myDB.addBook(pr_name_text.getText().toString().trim(),
                             Integer.valueOf(pr_quantity_text.getText().toString().trim()),
                        Integer.valueOf(pr_price_text.getText().toString().trim()),
                        pr_description_text.getText().toString().trim());
            }
        });


    }

    private void homeactivity() {
        Intent intent = new Intent(this, ProductList.class);
        startActivities(new Intent[]{intent});
    }



}