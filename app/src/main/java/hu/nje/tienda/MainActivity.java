package hu.nje.tienda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import hu.nje.tienda.pages.MarketActivity;
import hu.nje.tienda.pages.NewOrder;
import hu.nje.tienda.pages.SalesActivity;

public class MainActivity extends AppCompatActivity {
private Button button_new_order;
private Button button_stock;
private Button button_finance;
private Button button_profile;
private ImageButton profileButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_new_order = findViewById(R.id.button_new_order);
        button_stock = findViewById(R.id.button_stock);
        button_finance = findViewById(R.id.button_finance);
        button_profile = findViewById(R.id.button_profile);







        //navigációk
        
        button_new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openNewOrder();
            }
        });

        button_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openStock();
            }
        });

        button_finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openSalesActivity(); }
        });

       button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openProfileActivity(); }
        });

       
    }

    private void openNewOrder() {
        Intent intent = new Intent(this, NewOrder.class);
        startActivities(new Intent[]{intent});
    }

    private void openStock(){
        Intent intent = new Intent(this, MarketActivity.class);
        startActivities(new Intent[]{intent});
    }
    private void openSalesActivity(){
        Intent intent = new Intent(this, SalesActivity.class);
        startActivities(new Intent[]{intent});
    }
    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivities(new Intent[]{intent});
    }


}