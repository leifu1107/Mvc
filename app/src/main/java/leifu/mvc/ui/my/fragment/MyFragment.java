package leifu.mvc.ui.my.fragment;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import leifu.mvc.R;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.ui.near.bean.NewsLatestBean;
import leifu.mvc.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/31 14:46
 * 描述:
 */

public class MyFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initEventAndData() {
        Logger.e("MyFragment");
        OkGo.<NewsLatestBean>get("")
                .execute(new Callback<NewsLatestBean>() {
                    @Override
                    public void onStart(Request<NewsLatestBean, ? extends Request> request) {

                    }

                    @Override
                    public void onSuccess(Response<NewsLatestBean> response) {

                    }

                    @Override
                    public void onCacheSuccess(Response<NewsLatestBean> response) {

                    }

                    @Override
                    public void onError(Response<NewsLatestBean> response) {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void uploadProgress(Progress progress) {

                    }

                    @Override
                    public void downloadProgress(Progress progress) {

                    }

                    @Override
                    public NewsLatestBean convertResponse(okhttp3.Response response) throws Throwable {
                        return null;
                    }
                });
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        ImmersionBar.with(this).statusBarColor(R.color.white).init();
    }
}
