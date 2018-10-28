package com.baidu.translate;

import io.rong.imlib.model.Message;

/**
 * Created by Administrator on 2016/6/6 0006.
 */

public interface HttpCallBack {
    Message onSuccess(String result);
    void onFailure(String exception);
}
