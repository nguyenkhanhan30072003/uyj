package com.gh.mp3player.travelingnote.act;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.gh.mp3player.travelingnote.R;
import com.gh.mp3player.travelingnote.databinding.ActivityMainBinding;
import com.gh.mp3player.travelingnote.fragment.Main_Fragment;
import com.gh.mp3player.travelingnote.viewmodel.CommonVM;

public class MainActivity extends BaseActivity<ActivityMainBinding, CommonVM> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkMapPermission();
    }

    private void checkMapPermission() {
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]!=PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Accept permission to show Map", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected void initView() {
showFragment(Main_Fragment.TAG,null,false);
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}