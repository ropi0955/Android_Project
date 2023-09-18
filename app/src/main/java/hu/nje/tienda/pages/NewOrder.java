package hu.nje.tienda.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import hu.nje.tienda.R;

public class NewOrder extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {
private Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        spinner2 = findViewById(R.id.spinner2);

        initSpinner(R.array.howmanypieces,spinner2);
    }
       private void initSpinner(int resId, Spinner spinner)
        {
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.howmanypieces, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
}*/
    }