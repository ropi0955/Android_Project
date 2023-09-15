package hu.nje.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
private Button buttonNewProduct;
private ImageButton profileButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNewProduct = findViewById(R.id.buttonNewProduct);
        profileButton = findViewById(R.id.profileButton);






        //navigáció New Product oldalra
        buttonNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openNewProduct();
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openProfileActivity(); }
        });
    }

    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivities(new Intent[]{intent});
    }

    private void openNewProduct() {
        Intent intent = new Intent(this, NewProduct.class);
        startActivities(new Intent[]{intent});
    }
}