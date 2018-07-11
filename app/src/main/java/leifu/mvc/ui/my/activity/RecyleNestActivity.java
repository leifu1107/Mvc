package leifu.mvc.ui.my.activity;

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
import leifu.mvc.base.BaseActivity;
import leifu.mvc.callback.Convert;
import leifu.mvc.ui.near.adapter.RecyleNestAdapter;
import leifu.mvc.ui.near.bean.NewsLatestBean;
import leifu.mvc.utils.SystemUtils;

import static leifu.mvc.R.id.msv_statusview;

/**
 * 创建人: 雷富
 * 创建时间: 2018/7/11 16:23
 * 描述:
 */

public class RecyleNestActivity extends BaseActivity {


    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(msv_statusview)
    MultipleStatusView msvStatusview;
    private RecyleNestAdapter newsLatestAdapter;
    List<NewsLatestBean.StoriesBean> arrayList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_recyclenest;
    }

    @Override
    protected void initEventAndData() {
        newsLatestAdapter = new RecyleNestAdapter(R.layout.item_newslatest, arrayList);
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
                        NewsLatestBean newsLatestBean = Convert.fromJson(response.body(), NewsLatestBean.class);
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
