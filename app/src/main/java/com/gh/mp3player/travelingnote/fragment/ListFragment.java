package com.gh.mp3player.travelingnote.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gh.mp3player.travelingnote.act.PlaceAdapter;
import com.gh.mp3player.travelingnote.databinding.ListFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment<ListFragmentBinding> {

    public static final String TAG = ListFragment.class.getName();

    @Override
    protected void initView() {
        ArrayList<PlaceItem> list= (ArrayList<PlaceItem>) mData;
        PlaceAdapter adapter=new PlaceAdapter(list, context, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mbinding.vpPlace.setAdapter(adapter);
    }

    @Override
    protected ListFragmentBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return ListFragmentBinding.inflate(inflater,container,false);
    }
}
