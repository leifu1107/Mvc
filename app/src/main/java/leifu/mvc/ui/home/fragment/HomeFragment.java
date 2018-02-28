package leifu.mvc.ui.home.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import leifu.mvc.R;
import leifu.mvc.base.BaseFragment;
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

    private ArrayList<String> titleList = new ArrayList<String>();
    List<View> views = new ArrayList<>();

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
}
