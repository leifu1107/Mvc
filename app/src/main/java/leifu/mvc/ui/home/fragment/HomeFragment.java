package leifu.mvc.ui.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import leifu.mvc.R;
import leifu.mvc.app.Constants;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.bean.MessageEvent;
import leifu.mvc.callback.Convert;
import leifu.mvc.ui.classify.bean.MultipleItem;
import leifu.mvc.ui.home.activity.EventBus1Activity;
import leifu.mvc.ui.home.adapter.MultipleItemLayoutAdapter;
import leifu.mvc.ui.near.bean.NewsLatestBean;
import leifu.mvc.utils.Logger;
import leifu.mvc.utils.RegexUtils;
import leifu.mvc.utils.TextVerticalScrollMoreUtils;
import leifu.mvc.utils.TextVerticalScrollUtils;
import leifu.toastlibrary.CustomToast;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/31 14:46
 * 描述:
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_verticalscroll)
    TextVerticalScrollUtils tvVerticalscroll;
    @BindView(R.id.tv_verticalscrollmore)
    TextVerticalScrollMoreUtils tv_verticalscrollmore;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.iv_layoutManager)
    ImageView iv_layoutManager;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<String> titleList = new ArrayList<String>();
    List<View> views = new ArrayList<>();
    boolean isLinear = true;
    private NewsLatestBean newsLatestBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {
        titleList.add("你是天上最受宠的一架钢琴");
        titleList.add("我是丑人脸上的鼻涕");
        titleList.add("你发出完美的声音");
        tvVerticalscroll.setTextList(titleList);
        tvVerticalscroll.setText(15, 2, 0xff766156);//设置属性
        tvVerticalscroll.setTextStillTime(3000);//设置停留时长间隔
        tvVerticalscroll.setAnimTime(300);//设置进入和退出的时间间隔
        tvVerticalscroll.setOnItemClickListener(new TextVerticalScrollUtils.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CustomToast.warning("主页");
            }
        });
        Logger.e("主页");
        tvVerticalscroll.startAutoScroll();

        setUpMarqueeView();
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 11) {
                    if (RegexUtils.isMobileExact(phone.getText().toString())) {
                        CustomToast.success("是手机号码");
                    } else {
                        CustomToast.error("非法号码");
                    }
                }
            }
        });
       initRecycleView();
    }

    /**
     * 切换布局
     */
    private void initRecycleView() {
        final List<MultipleItem> multipleItemList = new ArrayList<>();

        final MultipleItemLayoutAdapter layoutAdapter = new MultipleItemLayoutAdapter(mContext, multipleItemList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(layoutAdapter);
        OkGo.<String>get(Constants.HOST + "news/latest")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        newsLatestBean = Convert.fromJson(response.body(), NewsLatestBean.class);
                        for (int i = 0; i < newsLatestBean.getStories().size(); i++) {
                            multipleItemList.add(new MultipleItem(MultipleItem.TEXT, 2, newsLatestBean.getStories().get(i)));
                        }
                        layoutAdapter.notifyDataSetChanged();
                    }
                });
        //切换布局
        iv_layoutManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipleItemList.clear();
                if (isLinear) {
                    mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                    isLinear = false;
                    iv_layoutManager.setImageResource(R.mipmap.ic_switch1);
                    for (int i = 0; i < newsLatestBean.getStories().size(); i++) {
                        multipleItemList.add(new MultipleItem(MultipleItem.IMG, 2, newsLatestBean.getStories().get(i)));
                    }
                } else {
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    isLinear = true;
                    iv_layoutManager.setImageResource(R.mipmap.ic_switch2);
                    for (int i = 0; i < newsLatestBean.getStories().size(); i++) {
                        multipleItemList.add(new MultipleItem(MultipleItem.TEXT, 2, newsLatestBean.getStories().get(i)));
                    }
                }
                layoutAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 仿淘宝通知
     */
    private void setUpMarqueeView() {
        View moreView = LayoutInflater.from(mContext).inflate(R.layout.view_taobaonotify, null);
        //初始化布局的控件
        TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);
        tv1.setText("五一欢乐与您共享，ＸＸ节能高清惊喜大促销。");
        tv2.setText("五一充值送机，你准备好了吗？");
        views.add(moreView);
        tv_verticalscrollmore.setViews(views);
    }

    /**
     * 先执行onSupportVisible方法,然后执行onLazyInitView并只执行一次
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (isInited) {
            tvVerticalscroll.startAutoScroll();
            Logger.e("已经isInited,主页可见");
        }
        ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true).init();

    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        tvVerticalscroll.stopAutoScroll();
        Logger.e("主页不可见");
    }

    @OnClick({R.id.tv_event})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_event:
                EventBus.getDefault().postSticky(new MessageEvent(3, "这是HomeFragment传给1"));
                mStartActivity(EventBus1Activity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onEvent(MessageEvent messageEvent) {
        super.onEvent(messageEvent);
        if (messageEvent.getFlag() == 4) {
            Logger.e("444" + messageEvent.toString());
        }

    }
}
