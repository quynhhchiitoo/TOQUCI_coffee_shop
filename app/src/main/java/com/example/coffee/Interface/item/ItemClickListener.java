package com.example.coffee.Interface.item;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;
import android.widget.ViewFlipper;

public interface ItemClickListener {
    void onClick(View view, int pos, boolean isLongClick);
}
