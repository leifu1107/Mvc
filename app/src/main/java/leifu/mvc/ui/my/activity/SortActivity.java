package leifu.mvc.ui.my.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.example.zhouwei.library.CustomPopWindow;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import leifu.mvc.R;
import leifu.mvc.app.Constants;
import leifu.mvc.base.BaseActivity;
import leifu.mvc.callback.Convert;
import leifu.mvc.callback.StringDialogCallback;
import leifu.mvc.ui.near.adapter.NewsLatestAdapter;
import leifu.mvc.ui.near.bean.NewsLatestBean;
import leifu.mvc.utils.Logger;

import static leifu.mvc.R.id.msv_statusview;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/9 15:08
 * 描述:
 */

public class SortActivity extends BaseActivity {

    @BindView(R.id.menu1)
    LinearLayout menu1;
    @BindView(R.id.menu2)
    LinearLayout menu2;
    @BindView(R.id.menu3)
    LinearLayout menu3;
    private String menus[] = {"排序", "品牌", "价格"};
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(msv_statusview)
    MultipleStatusView msvStatusview;
    private NewsLatestAdapter newsLatestAdapter;
    List<NewsLatestBean.StoriesBean> arrayList = new ArrayList<>();


    @Override
    protected int getLayout() {

        return R.layout.activity_sort;
    }

    @Override
    protected void initEventAndData() {
        setTitleText("排序", "", R.color.white);
        newsLatestAdapter = new NewsLatestAdapter(R.layout.item_newslatest, arrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(newsLatestAdapter);
//        setLoadMore();
        loadDatas();
    }

    View view_menu1, view_menu2;
    CustomPopWindow popWindowMenu1;

    @OnClick({R.id.menu1, R.id.menu2, R.id.menu3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu1:
                if (view_menu1 == null) {
                    view_menu1 = LayoutInflater.from(mContext).inflate(R.layout.view_menu1, null);
                    //处理popWindow 显示内容
                    handleMenu1(view_menu1);

                    popWindowMenu1 = new CustomPopWindow.PopupWindowBuilder(this)
                            .setView(view_menu1)//显示的布局，还可以通过设置一个View
                            .size(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT) //设置显示的大小，不设置就默认包裹内容
                            .setFocusable(true)//是否获取焦点，默认为ture
                            .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                            .create()//创建PopupWindow
                            .showAsDropDown(menu1, 0, 0);//显示PopupWindow
                } else {
                    popWindowMenu1.showAsDropDown(menu1, 0, 0);//显示PopupWindow
                }
                break;
            case R.id.menu2:
                view_menu2 = LayoutInflater.from(mContext).inflate(R.layout.view_menu2, null);
                HoldMenu2 holdMenu2 = new HoldMenu2(view_menu2);

                break;
            case R.id.menu3:
                break;
            default:
                break;
        }
    }

    private void handleMenu1(View view_menu1) {
        final HoldMenu1 holdMenu1 = new HoldMenu1(view_menu1);
        holdMenu1.ll_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowMenu1.dissmiss();
            }
        });
        for (int i = 0; i < holdMenu1.menu.getChildCount(); i++) {
            final TextView view = (TextView) holdMenu1.menu.getChildAt(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < holdMenu1.menu.getChildCount(); j++) {
                        ((TextView) holdMenu1.menu.getChildAt(j)).setTextColor(getResources().getColor(R.color.text6));
                    }
                    view.setTextColor(getResources().getColor(R.color.red2));
                }
            });
        }
    }

    private void loadDatas() {
        OkGo.<String>get(Constants.HOST + "news/latest")
                .tag(this)
                .execute(new StringDialogCallback(msvStatusview) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        NewsLatestBean newsLatestBean = Convert.fromJson(response.body(), NewsLatestBean.class);
                        Logger.e("aaa-----formatJson" + Convert.formatJson(newsLatestBean));
                        arrayList.addAll(newsLatestBean.getStories());
                        newsLatestAdapter.notifyDataSetChanged();
                        newsLatestAdapter.loadMoreComplete();
                    }

                });
    }


    class HoldMenu1 {
        @BindView(R.id.menu)
        LinearLayout menu;
        @BindView(R.id.ll_menu)
        LinearLayout ll_menu;

        public HoldMenu1(View view_menu1) {
            ButterKnife.bind(this, view_menu1);
        }
    }

    class HoldMenu2 {
        @BindView(R.id.rv_left)
        RecyclerView rv_left;
        @BindView(R.id.rv_right)
        RecyclerView rv_right;

        public HoldMenu2(View view_menu1) {
            ButterKnife.bind(this, view_menu2);
        }
    }
}
