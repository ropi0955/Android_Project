package hu.nje.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
private Button button_new_order;
private Button button_profile;
private ImageButton profileButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_new_order = findViewById(R.id.button_new_order);
        button_profile = findViewById(R.id.button_profile);







        //navigáció New Product oldalra
        button_new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openNewProduct();
            }
        });
       button_profile.setOnClickListener(new View.OnClickListener() {
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