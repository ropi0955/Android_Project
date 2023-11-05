package hu.nje.tienda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductUpdate extends AppCompatActivity {

    Button homeButton2;
    EditText pr_name_text, pr_quantity_text, pr_price_text, pr_description_text;
    Button updateButton;
    String id, name, quantity, price, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);
        homeButton2 = findViewById(R.id.homeButton2);

        pr_name_text = findViewById(R.id.pr_name_text2);
        pr_quantity_text = findViewById(R.id.pr_quantity_text2);
        pr_price_text = findViewById(R.id.pr_price_text2);
        pr_description_text = findViewById(R.id.pr_description_text2);
        updateButton = findViewById(R.id.updateButton);

        //First we call this
        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ProductUpdate.this);
                //And only then we call this
                name = pr_name_text.getText().toString().trim();
                quantity = pr_quantity_text.getText().toString().trim();
                price = pr_price_text.getText().toString().trim();
                description = pr_description_text.getText().toString().trim();
                myDB.updateData(id, name, quantity, price, description);

            }
        });




        homeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tohome();
            }
        });



    }
    private void tohome() {
        Intent intent = new Intent(this, ProductList.class);
       startActivities(new Intent[]{intent});

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id")
                && getIntent().hasExtra("name")
                && getIntent().hasExtra("quantity")
                && getIntent().hasExtra("price")
                && getIntent().hasExtra("description")){

            //Getting Data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            quantity = getIntent().getStringExtra("quantity");
            price = getIntent().getStringExtra("price");
            description = getIntent().getStringExtra("description");

            //Setting Data from intent
            pr_name_text.setText(name);
            pr_quantity_text.setText(quantity);
            pr_price_text.setText(price);
            pr_description_text.setText(description);
        }else
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}