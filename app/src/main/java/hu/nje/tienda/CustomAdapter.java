package hu.nje.tienda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hu.nje.tienda.pages.SalesActivity;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList product_id, product_name, product_quantity, product_price, product_description;

    CustomAdapter(Context context,
                  Activity activity,
                  ArrayList product_id,
                  ArrayList product_name,
                  ArrayList product_quantity,
                  ArrayList product_price,
                  ArrayList product_description) {
        this.activity = activity;
        this.context = context;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
        this.product_description = product_description;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    // @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();


        holder.product_id_txt.setText(String.valueOf(product_id.get(currentPosition)));
        holder.product_name_txt.setText(String.valueOf(product_name.get(currentPosition)));
        holder.product_quantity_txt.setText(String.valueOf(product_quantity.get(currentPosition)));
        holder.product_price_txt.setText(String.valueOf(product_price.get(currentPosition)));
        holder.product_description_txt.setText(String.valueOf(product_description.get(currentPosition)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                Intent intent = new Intent(context, ProductUpdate.class);
                intent.putExtra("id", String.valueOf(product_id.get(clickedPosition)));
                intent.putExtra("name", String.valueOf(product_name.get(clickedPosition)));
                intent.putExtra("quantity", String.valueOf(product_quantity.get(clickedPosition)));
                intent.putExtra("price", String.valueOf(product_price.get(clickedPosition)));
                intent.putExtra("description", String.valueOf(product_description.get(clickedPosition)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_id_txt, product_name_txt, product_quantity_txt, product_price_txt, product_description_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_name_txt = itemView.findViewById(R.id.product_name_txt);
            product_quantity_txt = itemView.findViewById(R.id.product_quantity_txt);
            product_price_txt = itemView.findViewById(R.id.product_price_txt);
            product_description_txt = itemView.findViewById(R.id.product_description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    public int calculateTotalAssets() {
        int totalAssets = 0;
        for (int i = 0; i < product_id.size(); i++) {
            int quantity = Integer.parseInt(String.valueOf(product_quantity.get(i)));
            int price = Integer.parseInt(String.valueOf(product_price.get(i)));
            int asset = quantity * price;
            totalAssets += asset;
        }
        return totalAssets;
    }

}
