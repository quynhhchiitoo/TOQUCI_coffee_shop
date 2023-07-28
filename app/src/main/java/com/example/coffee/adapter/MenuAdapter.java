package com.example.coffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import java.util.List;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffee.Menu;
import com.example.coffee.R;

public class MenuAdapter extends BaseAdapter{

    Context myContext;
    int myLayout;
    List<Menu> MenuArray;
    public MenuAdapter(Context context, int layout, List<Menu> MenuList){
        myContext = context;
        myLayout = layout;
        MenuArray = MenuList;
    }

    @Override
    public int getCount(){
        return MenuArray.size();
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);

        //anhxagangiatri
        TextView txtName = (TextView)  convertView.findViewById(R.id.name1);
        txtName.setText(MenuArray.get(position).Name);
        ImageView imgPics = (ImageView) convertView.findViewById(R.id.food1);
        imgPics.setImageResource(MenuArray.get(position).Pics);

        return convertView;
    }
}
