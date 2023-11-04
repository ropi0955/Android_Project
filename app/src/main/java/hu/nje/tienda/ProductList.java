package hu.nje.tienda;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton backMainActivityButton4;
    FloatingActionButton addButton;
    MyDatabaseHelper myDB;
    ArrayList<String> product_id, product_name, product_quantity, product_price, product_description;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        backMainActivityButton4 = findViewById(R.id.backMainActivityButton4);
        recyclerView =findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductList.this, ProductAdd.class);
                startActivity(intent);
            }
        });

        backMainActivityButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMainActivity4();
            }
        });


        myDB = new MyDatabaseHelper(ProductList.this);
        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_quantity = new ArrayList<>();
        product_price = new ArrayList<>();
        product_description = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ProductList.this, ProductList.this, product_id, product_name, product_quantity, product_price, product_description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductList.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                product_name.add(cursor.getString(1));
                product_quantity.add(cursor.getString(2));
                product_price.add(cursor.getString(3));
                product_description.add(cursor.getString(4));
            }
        }
    }


    private void backMainActivity4() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivities(new Intent[]{intent});
    }
}