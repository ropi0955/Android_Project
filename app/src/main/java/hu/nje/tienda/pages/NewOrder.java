package hu.nje.tienda.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import hu.nje.tienda.MainActivity;
import hu.nje.tienda.ProductList;
import hu.nje.tienda.R;

public class NewOrder extends AppCompatActivity {

    private FloatingActionButton backMainActivityButton3;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private ArrayList<String> data2 = new ArrayList<String>();
    private ArrayList<String> data3 = new ArrayList<String>();

    private TableLayout table;

    EditText ed1, ed2, ed3;
    Button b1;
    TextView totalSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        backMainActivityButton3 = findViewById(R.id.backMainActivityButton3);
        backMainActivityButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMainActivity3();
            }
        });

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);

        b1 = findViewById(R.id.btn1);
        totalSum = findViewById(R.id.t4);
        table = findViewById(R.id.tb1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
    }

    private void backMainActivity3() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void add() {
        String prodname = ed1.getText().toString();
        int price = Integer.parseInt(ed2.getText().toString());
        int qty = Integer.parseInt(ed3.getText().toString());
        int tot = price * qty;

        data.add(prodname);
        data1.add(String.valueOf(price));
        data2.add(String.valueOf(qty));
        data3.add(String.valueOf(tot));

        TableRow row = new TableRow(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);
        TextView t3 = new TextView(this);
        TextView t4 = new TextView(this);

        t1.setText(prodname);
        t2.setText(String.valueOf(price));
        t3.setText(String.valueOf(qty));
        t4.setText(String.valueOf(tot));

        row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        row.addView(t4);

        table.addView(row);

        int sum = 0;

        for (int i = 0; i < data3.size(); i++) {
            sum += Integer.parseInt(data3.get(i));
        }

        totalSum.setText(String.valueOf(sum));


        Intent intent = new Intent(this, ProductList.class);
        intent.putExtra(" ",sum);
        startActivity(intent);

        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed1.requestFocus();
    }
}
