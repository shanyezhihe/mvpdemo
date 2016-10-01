package com.example.mvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.bean.Version;
import com.example.mvpdemo.mvp.presenter.impl.VersionPresenter;
import com.example.mvpdemo.mvp.view.impl.VersionView;

public class VersionActivity extends AppCompatActivity implements VersionView{
    private TextView tv_versionName;
    private TextView tv_desc;
    private VersionPresenter mVersionPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mVersionPresenter=VersionPresenter.getInstance(this,this);
        tv_desc=(TextView) findViewById(R.id.tv_desc);
        tv_versionName=(TextView) findViewById(R.id.tv_versionname);
        findViewById(R.id.tv_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVersionPresenter.getNewVersion("http://10.180.152.101:8080/NetWorkServer/updateappversion"+"?currentversion=1.4");
            }
        });
    }

    @Override
    public void showVersionNameAndDesc(Version version) {
        tv_versionName.setText("最新版本名: "+version.getLatest_version());
        tv_desc.setText(version.getDesc());

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(VersionActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
