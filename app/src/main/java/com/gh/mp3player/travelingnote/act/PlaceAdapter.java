package com.gh.mp3player.travelingnote.act;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.gh.mp3player.travelingnote.R;
import com.gh.mp3player.travelingnote.fragment.PlaceItem;

import java.util.ArrayList;

public class PlaceAdapter extends PagerAdapter {
    private ArrayList<PlaceItem> list;
    private Context context;
    private View.OnClickListener event;

    @Override
    public int getCount() {
        return list.size();
    }

    public PlaceAdapter(ArrayList<PlaceItem> list, Context context, View.OnClickListener event) {
        this.list = list;
        this.context = context;
        this.event = event;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_place,container,false);
        ImageView ivPlace=v.findViewById(R.id.iv_picture);
        TextView tvName=v.findViewById(R.id.tv_name);
        TextView tvInfo=v.findViewById(R.id.tv_infor);
        
        PlaceItem item=list.get(position);
        Glide.with(context).load(item.getLinkPhoTo()).into(ivPlace);

        tvInfo.setText(item.getDesc());
        tvName.setText(item.getName());
        ivPlace.setTag(item);
        ivPlace.setOnClickListener(event);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
