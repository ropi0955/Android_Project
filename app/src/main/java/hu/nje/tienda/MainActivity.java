package hu.nje.tienda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import hu.nje.tienda.pages.LoginActivity;
import hu.nje.tienda.pages.NewOrder;

public class MainActivity extends AppCompatActivity {
private Button button_new_order;
private Button button_stock;
private Button button_profile;
private Button button_log_out;

private Button addButton2;
private ImageButton profileButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_new_order = findViewById(R.id.button_new_order);
        button_stock = findViewById(R.id.button_stock);
        addButton2 = findViewById(R.id.addButton2);
        button_log_out = findViewById(R.id.button_log_out);







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
        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProductAdd();
            }
        });

        button_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

       
    }

    private void openNewOrder() {
        Intent intent = new Intent(this, NewOrder.class);
        startActivities(new Intent[]{intent});
    }

    private void openStock(){
        Intent intent = new Intent(this, ProductList.class);
        startActivities(new Intent[]{intent});
    }
    private void openProductAdd(){
        Intent intent = new Intent(this, ProductAdd.class);
        startActivities(new Intent[]{intent});
    }
    private void  openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivities(new Intent[]{intent});
    }


}