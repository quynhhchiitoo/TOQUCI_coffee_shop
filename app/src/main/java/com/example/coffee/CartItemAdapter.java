package com.example.coffee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CartItem> cartItems;

    public CartItemAdapter(Context context, ArrayList<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        // Set the values of the views in the item_cart layout
        holder.itemName.setText(cartItem.getName());
        holder.itemDescription.setText(cartItem.getDescription());
        holder.itemQuantity.setText("Quantity: " + cartItem.getQuantity());
        holder.itemTotalPrice.setText("$" + cartItem.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemDescription, itemQuantity, itemTotalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_cart_name);
            itemDescription = itemView.findViewById(R.id.item_cart_description);
            itemQuantity = itemView.findViewById(R.id.item_cart_quantity);
            itemTotalPrice = itemView.findViewById(R.id.pricenum);
        }
    }
}
