package com.example.coffee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.itemName.setText(cartItem.getName());
        holder.itemDescription.setText(cartItem.getDescription());
        holder.itemQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.itemTotalPrice.setText(String.valueOf(cartItem.getTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemDescription, itemQuantity, itemTotalPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_cart_name);
            itemDescription = itemView.findViewById(R.id.item_cart_description);
            itemQuantity = itemView.findViewById(R.id.item_cart_quantity);
            itemTotalPrice = itemView.findViewById(R.id.pricenum);
        }
    }
}
