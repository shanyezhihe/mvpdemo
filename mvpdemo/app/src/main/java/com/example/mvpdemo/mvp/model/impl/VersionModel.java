package com.example.mvpdemo.mvp.model.impl;

import android.content.Context;
import android.util.Log;

import com.example.mvpdemo.bean.Version;
import com.example.mvpdemo.mvp.model.IModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pengdongyuan491 on 16/10/1.
 */
public class VersionModel implements IModel {
    public interface VersionCallback {
        void onVersionCallback(Version version);
    }

    private HttpURLConnection httpURLConnection = null;
    private InputStream inputStream = null;
    private BufferedReader bufferedReader = null;

    public void getNewVersion(Context context,final String url, final VersionCallback versionCallback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                    httpURLConnection.connect();
                    inputStream = httpURLConnection.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String tempLine = null;
                    while ((tempLine = bufferedReader.readLine()) != null) {
                        stringBuilder.append(tempLine).append("\n");
                    }
                    String data = stringBuilder.toString();
                    Log.e("data", data);
                    JSONObject jsonObject=null;
                    jsonObject = new JSONObject(data);
                    Version version=new Version();
                    version.setLatest_version(jsonObject.optString("latest_version"));
                    version.setDesc(jsonObject.optString("desc"));
                    version.setHasNewVersion(jsonObject.optString("hasNewVersion"));
                    versionCallback.onVersionCallback(version);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bufferedReader.close();
                        inputStream.close();
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }
}
