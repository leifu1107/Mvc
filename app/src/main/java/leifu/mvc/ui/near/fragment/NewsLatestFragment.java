package leifu.mvc.ui.near.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import leifu.mvc.R;
import leifu.mvc.app.Constants;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.ui.near.adapter.NewsLatestAdapter;
import leifu.mvc.ui.near.bean.NewsLatestBean;
import leifu.mvc.callback.Convert;
import leifu.mvc.utils.Logger;
import leifu.mvc.utils.SystemUtils;

import static leifu.mvc.R.id.msv_statusview;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/31 14:46
 * 描述:
 */

public class NewsLatestFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(msv_statusview)
    MultipleStatusView msvStatusview;
    private NewsLatestAdapter newsLatestAdapter;
    List<NewsLatestBean.StoriesBean> arrayList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_near_newslastest;
    }

    @Override
    protected void initEventAndData() {
        newsLatestAdapter = new NewsLatestAdapter(R.layout.item_newslatest, arrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(newsLatestAdapter);
        setLoadMore();
        loadDatas();
    }

    private void loadDatas() {
        OkGo.<String>get(Constants.HOST + "news/latest")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.e(response.body());
                        NewsLatestBean newsLatestBean = Convert.fromJson(response.body(), NewsLatestBean.class);

                        Logger.e("aaa-----formatJson"+Convert.formatJson(newsLatestBean));
                        Logger.e("aaa-----toJson"+Convert.toJson(newsLatestBean));
                        arrayList.addAll(newsLatestBean.getStories());
                        newsLatestAdapter.notifyDataSetChanged();
                        msvStatusview.showContent();
                        newsLatestAdapter.loadMoreComplete();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (!SystemUtils.isNetworkConnected()) {
                            msvStatusview.showNoNetwork();
                        } else {
                            msvStatusview.showContent();
                        }
                    }

                });
    }

    private void setLoadMore() {
//        newsLatestAdapter.disableLoadMoreIfNotFullPage();
        newsLatestAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadDatas();
            }
        });
    }

}
