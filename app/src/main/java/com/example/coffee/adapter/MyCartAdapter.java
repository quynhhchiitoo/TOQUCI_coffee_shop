package com.example.coffee.adapter;

import androidx.recyclerview.widget.RecyclerView;
import com.example.coffee.MyCart;
import com.example.coffee.R;
//import com.example.coffee.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder>{
    Context context;
    List<MyCart> MyCartList;

    public MyCartAdapter(Context context, List<MyCart>MyCartList){
        this.context = context;
        this.MyCartList = MyCartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent,false);
        return new MyCartAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        MyCart item = MyCartList.get(position);
        holder.item_cart_name.setText(item.getName());
        holder.item_cart_quantity.setText("x" + String.valueOf(item.getQuantity()));
        holder.item_cart_image.setImageResource(item.getImage());
        holder.pricenum.setText("$" + String.valueOf(item.getQuantity() * item.getPrice()));
        String spicy = item.isSpicy() ? "Extra spicy" : "No extra spicy";
        String sauce = item.isSpicy() ? "Extra sauce" : "No extra sauce";
        String cheese = item.getCheeseQuantity() == 1? "100gr cheese" : "200gr cheese";
        holder.item_cart_description.setText(spicy + " | " + sauce + " | " + cheese);
    }
    @Override
    public int getItemCount(){
        return MyCartList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView item_cart_image;
        TextView item_cart_name, item_cart_description, item_cart_quantity, pricenum;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            item_cart_image = itemView.findViewById(R.id.item_cart_image);
            item_cart_name = itemView.findViewById(R.id.item_cart_name);
            item_cart_description = itemView.findViewById(R.id.item_cart_description);
            item_cart_quantity = itemView.findViewById(R.id.item_cart_quantity);
            pricenum = itemView.findViewById(R.id.pricenum);
        }
    }
}