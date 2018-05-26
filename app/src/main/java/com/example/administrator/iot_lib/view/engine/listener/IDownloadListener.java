package com.example.administrator.iot_lib.view.engine.listener;

public interface IDownloadListener {
    void onPrepare();

    void onDownloadProgress(long downSize,long totalSize);

    void onFailed(int error);

    void onCompleted();

    void onCancel();
}
