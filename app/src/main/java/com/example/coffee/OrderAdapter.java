package com.example.coffee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.Order;
import com.example.coffee.R;

import java.util.List;
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{
    Context context;
    List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent,false);
        return new OrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        Order item = orderList.get(position);
        holder.order_total.setText(item.getItem_price());
        holder.dish_name.setText(item.getItem_name());
        holder.location.setText(item.getAddress());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView order_total, dish_name, location;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            order_total = itemView.findViewById(R.id.order_total);
            dish_name = itemView.findViewById(R.id.dish_name);
            location = itemView.findViewById(R.id.location);
        }
    }
}