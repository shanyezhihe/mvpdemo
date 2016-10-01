package com.example.mvpdemo.mvp.view.impl;

import com.example.mvpdemo.bean.Version;
import com.example.mvpdemo.mvp.view.IView;

/**
 * Created by pengdongyuan491 on 16/10/1.
 */
public interface VersionView extends IView {
   void showVersionNameAndDesc(Version version);
}
