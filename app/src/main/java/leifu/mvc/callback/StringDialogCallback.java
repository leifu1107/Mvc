package leifu.mvc.callback;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;

import leifu.mvc.utils.SystemUtils;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/6 13:52
 * 描述:
 */

public abstract class StringDialogCallback extends StringCallback {

    private MultipleStatusView msvStatusview;

    public StringDialogCallback(MultipleStatusView msvStatusview) {
        this.msvStatusview = msvStatusview;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
            msvStatusview.showLoading();
    }


    @Override
    public void onFinish() {
        if (!SystemUtils.isNetworkConnected()) {
            msvStatusview.showNoNetwork();
        } else {
            //不是空视图择显示内容
            if (msvStatusview.getViewStatus() != MultipleStatusView.STATUS_EMPTY) {
                msvStatusview.showContent();
            }
        }
    }
}
