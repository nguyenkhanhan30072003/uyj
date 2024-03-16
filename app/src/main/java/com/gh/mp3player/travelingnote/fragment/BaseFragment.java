package com.gh.mp3player.travelingnote.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.gh.mp3player.travelingnote.OnMainCallBack;

public abstract class BaseFragment<B extends ViewBinding> extends Fragment implements View.OnClickListener {
    protected Context context;
    protected B mbinding;

    protected OnMainCallBack callBack;
    protected Object mData;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mbinding = initViewBinding(inflater, container);
        return mbinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
    }


    protected abstract void initView();

    protected abstract B initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    public void setmData(Object mData) {
        this.mData = mData;
    }

    public void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }
}
