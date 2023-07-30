//package com.example.coffee;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.ViewFlipper;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//public class HomePage extends AppCompatActivity {
//    ViewFlipper viewFlipper;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.homepage);
//        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
//        viewFlipper.setFlipInterval(3000);
//        viewFlipper.setAutoStart(true);
//    }
//}
//
package com.example.coffee;

import android.content.ClipData;
import android.media.Image;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.coffee.R;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import java.util.ArrayList;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity {
    ArrayList<Menu> MenuArray;
    ArrayList<Menu> MenuArraydetails;
    ViewFlipper viewFlipper;

    ImageView avaImageView, myCartView;

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_store) {
                    return true;
                } else if (item.getItemId() == R.id.bottom_giftbox) {
                    startActivity(new Intent(HomePage.this, Reward.class));
                    return true;
                } else if (item.getItemId() == R.id.bottom_bill) {
                    startActivity(new Intent(HomePage.this, MyOrders.class));
                    return true;
                }
                return false;
            }
        });

        TextView username = findViewById(R.id.user_name);
        Database database = new Database(getApplicationContext());
        User user = database.getFirstUser();
        if(user == null)
            username.setText("");
        else
            username.setText(user.getFullname());

        ImageView avaImageView = findViewById(R.id.ava);
        avaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Profile.class);
                startActivity(intent);
            }
        });

        myCartView = findViewById(R.id.shoppingbag);
        myCartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, MyCartActivity.class);
                startActivity(intent);
            }
        });

        viewFlipper = findViewById(R.id.viewFlipper);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        MenuArray = new ArrayList<Menu>();
        MenuArray.add(new Menu("Mac n Cheese", R.drawable.macncheese));
        MenuArray.add(new Menu("French Toast", R.drawable.frenchtoast));
        MenuArray.add(new Menu("Korean Corndog", R.drawable.corndog));
        MenuArray.add(new Menu("Cheese Ball", R.drawable.cheeseball));
        MenuArray.add(new Menu("Cheese Bulgogi Burger", R.drawable.cheeseburrger));

        MenuArraydetails = new ArrayList<Menu>();
        MenuArraydetails.add(new Menu("Mac n Cheese", 5,R.drawable.macncheese));
        MenuArraydetails.add(new Menu("French Toast", 3,R.drawable.frenchtoast));
        MenuArraydetails.add(new Menu("Korean Corndog", 2,R.drawable.corndog));
        MenuArraydetails.add(new Menu("Cheese Ball", 2,R.drawable.cheeseball));
        MenuArraydetails.add(new Menu("Cheese Bulgogi Burger", 3,R.drawable.cheeseburrger));

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        int numColumns = 2;
        int numRows = (int) Math.ceil((double) MenuArray.size() / numColumns);

        gridLayout.setRowCount(numRows);
        gridLayout.setColumnCount(numColumns);

        for (int i = 0; i < MenuArray.size(); i++) {
            // Inflate the grid item layout
            View gridItem = getLayoutInflater().inflate(R.layout.grid_items, gridLayout, false);

            // Find views inside the grid item layout
            ImageView imageView = gridItem.findViewById(R.id.food1);
            TextView textView = gridItem.findViewById(R.id.name1);

            // Set data to the views
            Menu foodItem = MenuArray.get(i);
            imageView.setImageResource(foodItem.Pics);
            textView.setText(foodItem.Name);

            // Set margins for spacing between items (adjust the value as needed)
            int spacing = getResources().getDimensionPixelSize(R.dimen.card_view_spacing);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(spacing, spacing, spacing, spacing);
            gridItem.setLayoutParams(params);


            gridItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the position of the clicked item
                    int position = gridLayout.indexOfChild(v);

                    // Get the corresponding menu item from the array
                    Menu selectedMenuItem = MenuArraydetails.get(position);

                    // Start the DetailsActivity and pass the selected menu item's details
                    Intent intent = new Intent(HomePage.this, Details.class);
                    intent.putExtra("menuName", selectedMenuItem.Name);
                    intent.putExtra("menuImage", selectedMenuItem.Pics);
                    intent.putExtra("menuPrice", selectedMenuItem.Price);
                    startActivity(intent);
                }
            });
            gridLayout.addView(gridItem);
        }
    }
}