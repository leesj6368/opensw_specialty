package com.cookandroid.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class CenterAdapter extends ArrayAdapter<center> {

    public CenterAdapter(Context context, int resource, List<center> centerList){
        super(context, resource, centerList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        center center =getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.animal_item,parent,false);
        }

        TextView tv = convertView.findViewById(R.id.center_name);
        ImageView iv = convertView.findViewById(R.id.center_image);

        tv.setText(center.getName());
        iv.setImageResource(center.getImage());

        return convertView;

    }
}
