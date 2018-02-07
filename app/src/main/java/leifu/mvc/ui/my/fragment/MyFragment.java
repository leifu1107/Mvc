package leifu.mvc.ui.my.fragment;

import android.view.View;

import com.classic.common.MultipleStatusView;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import leifu.mvc.R;
import leifu.mvc.app.Constants;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.callback.Convert;
import leifu.mvc.callback.JsonCallback;
import leifu.mvc.ui.near.bean.NewsLatestBean;
import leifu.mvc.utils.Logger;
import leifu.toastlibrary.CustomToast;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/31 14:46
 * 描述:
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.msvStatusview)
    MultipleStatusView msvStatusview;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initEventAndData() {
        msvStatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.success("重试");
                Logger.e("重试");
                onSupportVisible();
            }
        });

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true).init();

//        OkGo.<String>get(Constants.HOST + "news/latest")
//                .execute(new StringDialogCallback(msvStatusview) {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Logger.e("MyFragment"+response.body());
//                        msvStatusview.showEmpty();
//                    }
//                });
        OkGo.<NewsLatestBean>get(Constants.HOST + "news/latest")
                .execute(new JsonCallback<NewsLatestBean>() {
                    @Override
                    public void onSuccess(Response<NewsLatestBean> response) {
                        NewsLatestBean body = response.body();
                        Logger.e("onSuccess-----==="+Convert.toJson(body));
                    }

                    @Override
                    public void onError(Response<NewsLatestBean> response) {
                        super.onError(response);
                        Logger.e("onError-----==="+Convert.toJson(response.body()));
                    }
                });
    }

}
