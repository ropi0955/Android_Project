package hu.nje.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button buttonNewProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNewProduct = findViewById(R.id.buttonNewProduct);





        //navigáció New Product oldalra
        buttonNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openNewProduct();
            }
        });
    }

    private void openNewProduct() {
        Intent intent = new Intent(this, NewProduct.class);
        startActivities(new Intent[]{intent});
    }
}