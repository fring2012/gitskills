package com.example.administrator.iot_lib.view.engine.listener;

import android.icu.util.VersionInfo;

public interface ICheckVersionCallback {
    /**
     * check version success
     *
     * @see OtaAgentPolicy
     */
    void onCheckSuccess(VersionInfo versionInfo);

    /**
     * check version fail
     *
     * @param status   is result tag
     */
    void onCheckFail(int status);
}
