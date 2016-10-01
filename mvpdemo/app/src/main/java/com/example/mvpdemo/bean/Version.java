package com.example.mvpdemo.bean;

/**
 * Created by pengdongyuan491 on 16/10/1.
 */
public class Version {
    //是否有新版本
    private String hasNewVersion;
    //描述
    private String desc;
    //最新版本名
    private String latest_version;

    public String getHasNewVersion() {
        return hasNewVersion;
    }

    public String getDesc() {
        return desc;
    }

    public String getLatest_version() {
        return latest_version;
    }

    public void setHasNewVersion(String hasNewVersion) {
        this.hasNewVersion = hasNewVersion;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLatest_version(String latest_version) {
        this.latest_version = latest_version;
    }
}
