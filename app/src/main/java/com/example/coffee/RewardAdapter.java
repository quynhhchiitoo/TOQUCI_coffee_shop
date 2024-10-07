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
public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.MyViewHolder>{
    Context context;
    List<Order> orderList;

    public RewardAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public RewardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_reward_card, parent,false);
        return new RewardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardAdapter.MyViewHolder holder, int position) {
        Order item = orderList.get(position);
        holder.point_rewarded.setText("+" + String.valueOf(item.getItem_quantity() * 10));
        holder.item_reward_name.setText(item.getItem_name());
        holder.item_reward_date.setText(item.getDay() + " | " + item.getTime());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView point_rewarded, item_reward_name, item_reward_date;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            point_rewarded = itemView.findViewById(R.id.point_rewarded);
            item_reward_name = itemView.findViewById(R.id.item_reward_name);
            item_reward_date = itemView.findViewById(R.id.item_reward_date);
        }
    }
}