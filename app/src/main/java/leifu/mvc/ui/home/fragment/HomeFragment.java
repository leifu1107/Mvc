package leifu.mvc.ui.home.fragment;

import java.util.ArrayList;

import butterknife.BindView;
import leifu.mvc.R;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.utils.Logger;
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
    private ArrayList<String> titleList = new ArrayList<String>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {
        titleList.add("你是天上最受宠的一架钢琴");
        titleList.add("我是丑人脸上的鼻涕");
        titleList.add("你发出完美的声音");
        titleList.add("我被默默揩去");
        titleList.add("你冷酷外表下藏着诗情画意");
        titleList.add("我已经够胖还吃东西");
        titleList.add("你踏着七彩祥云离去");
        titleList.add("我被留在这里");
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
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (isInited) {
            tvVerticalscroll.startAutoScroll();
            Logger.e("已经isInited,主页可见");
        }

    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        tvVerticalscroll.stopAutoScroll();
        Logger.e("主页不可见");
    }
}
