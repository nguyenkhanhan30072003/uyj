package com.gh.mp3player.travelingnote.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.gh.mp3player.travelingnote.OnMainCallBack;
import com.gh.mp3player.travelingnote.R;
import com.gh.mp3player.travelingnote.fragment.BaseFragment;

import java.lang.reflect.Constructor;

public abstract class BaseActivity<B extends ViewBinding,V extends ViewModel> extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
    protected B mbinding;
protected V viewmodel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding=initViewBinding();
        setContentView(mbinding.getRoot());
        viewmodel=new ViewModelProvider(this).get(getClassVM());
        initView();
    }

    protected abstract Class<V> getClassVM();


    protected abstract void initView();

    protected abstract B initViewBinding();

    @Override
    public void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
    }
    @Override
    public void showFragment(String tag, Object data, boolean isBack) {
        try {
            Class<?> clazz= Class.forName(tag);//Trỏ vào 1 Fragment class
            Constructor<?> constructor=clazz.getConstructor();
            BaseFragment fragment=(BaseFragment) constructor.newInstance();
            fragment.setmData(data);
            fragment.setCallBack(this);

            FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
            if(isBack){
                trans.addToBackStack(null);
            }
            trans.replace(R.id.ln_main,fragment,tag).commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
