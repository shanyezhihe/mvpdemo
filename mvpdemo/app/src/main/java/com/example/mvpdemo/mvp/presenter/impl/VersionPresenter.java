package com.example.mvpdemo.mvp.presenter.impl;

import android.app.Activity;
import android.content.Context;

import com.example.mvpdemo.bean.Version;
import com.example.mvpdemo.mvp.model.impl.VersionModel;
import com.example.mvpdemo.mvp.presenter.IPresenter;
import com.example.mvpdemo.mvp.view.IView;
import com.example.mvpdemo.mvp.view.impl.VersionView;

/**
 * Created by pengdongyuan491 on 16/10/1.
 */
public class VersionPresenter extends IPresenter {
    private static VersionPresenter mVersionPresenter;
    private VersionModel mVersionModel;
    private VersionView mVersionView;
    private Context mContext;
    private VersionPresenter(Context context,IView view){
        this.mContext=context;
        this.mVersionView=(VersionView) view;
        this.mVersionModel=new VersionModel();
    }

    public static VersionPresenter getInstance(Context context, VersionView view){
        if (mVersionPresenter==null){
            synchronized (VersionPresenter.class){
                if (mVersionPresenter==null){
                    mVersionPresenter=new VersionPresenter(context,view);
                }
            }
        }
        return mVersionPresenter;
    }
    //获取最新版本
    public void getNewVersion(String url){
        mVersionModel.getNewVersion(mContext,url, new VersionModel.VersionCallback() {
            @Override
            public void onVersionCallback(final  Version version) {
                ((Activity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mVersionView.showVersionNameAndDesc(version);
                        if (version.getHasNewVersion().equals("true")){
                            mVersionView.showToast("App有新版本,请更新");
                        }
                    }
                });

            }
        });
    }

}
