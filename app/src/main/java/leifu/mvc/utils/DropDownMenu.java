package leifu.mvc.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import leifu.mvc.R;
import leifu.mvc.app.App;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/9 13:13
 * 描述:
 */

public class DropDownMenu extends LinearLayout {
    //下划线颜色
    int ddunderlineColor = 0xffd1d1d1;
    //分割线颜色
    private int dddividerColor = 0xffd1d1d1;
    //tab选中颜色
    private int ddtextSelectedColor = 0xff06C1AE;
    //tab未选中颜色
    int ddtextUnselectedColor = 0xffd1d1d1;


    //整体背景颜色
    private int ddmenuBackgroundColor = 0xffffffff;
    //遮罩颜色
    private int ddmaskColor = 0xfff2f2f2;
    // tab字体大小
    private int ddmenuTextSize = 14;

    // tab未选中图标
    private int ddmenuUnselectedIcon;
    // tab选中图标
    private int ddmenuSelectedIcon;
    private LinearLayout tabMenuView;//父布局

    public DropDownMenu(Context context) {
        super(context);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        setBackgroundColor(0xffd1d1d1);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DropDownMenu);
        ddunderlineColor = a.getColor(R.styleable.DropDownMenu_ddunderlineColor, ddunderlineColor);
        dddividerColor = a.getColor(R.styleable.DropDownMenu_dddividerColor, dddividerColor);
        ddtextSelectedColor = a.getColor(R.styleable.DropDownMenu_ddtextSelectedColor, ddtextSelectedColor);
        ddtextUnselectedColor = a.getColor(R.styleable.DropDownMenu_ddtextUnselectedColor, ddtextUnselectedColor);
        ddmenuBackgroundColor = a.getColor(R.styleable.DropDownMenu_ddmenuBackgroundColor, ddmenuBackgroundColor);
        ddmaskColor = a.getColor(R.styleable.DropDownMenu_ddmaskColor, ddmaskColor);

        ddmenuTextSize = a.getDimensionPixelSize(
                R.styleable.DropDownMenu_ddmenuTextSize, ddmenuTextSize);
        ddmenuSelectedIcon = a.getResourceId(
                R.styleable.DropDownMenu_ddmenuSelectedIcon, ddmenuSelectedIcon);
        ddmenuUnselectedIcon = a.getResourceId(
                R.styleable.DropDownMenu_ddmenuUnselectedIcon,
                ddmenuUnselectedIcon);
        a.recycle();
        // 初始化tabMenuView并添加到tabMenuView
        initTabMenuView(context);
    }

    private void initTabMenuView(Context context) {
        //tabMenuView并添加到LinearLayout中去
        tabMenuView = new LinearLayout(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabMenuView.setOrientation(HORIZONTAL);
        tabMenuView.setBackgroundColor(ddmenuBackgroundColor);
        tabMenuView.setLayoutParams(params);
        addView(tabMenuView, 0);
        //添加下划线到LinearLayout
        View underLine = new View(context);
        underLine.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1.0f)));
        underLine.setBackgroundColor(ddunderlineColor);
        addView(underLine, 1);
        // 初始化containerView并将其添加到LinearLayout
        FrameLayout containerView = new FrameLayout(context);
        containerView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        addView(containerView, 2);


    }
    /**
     * 最后一个要调用初始化
     * @param tabTexts 标题头文字
     * @param popupViews 点击筛选要显示的布局
     * @param containerView 内容布局
     */
    public void initDropDownMenu(List<String> tabTexts, List<View> popupViews, View containerView) {
        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts,i);
        }
    }
    public void initDropDownMenu(List<String> tabTexts) {
        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts,i);
        }
    }
    /**
     * 添加标题头文字
     *
     * @param tabTexts 标题头文字
     * @param position 标题头文字放置位置
     */
    private void addTab(List<String> tabTexts, int position) {
        final TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ddmenuTextSize);
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setTextColor(ddtextUnselectedColor);
        textView.setText(tabTexts.get(position));
        //textView右边放置图片
        Drawable drawable = getResources().getDrawable(ddmenuUnselectedIcon);
        //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null); //设置右图标
        tabMenuView.addView(textView);
        //添加textview直接的分割线
        if (position<tabTexts.size()-1) {
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(dp2px(0.5f),
                    ViewGroup.LayoutParams.MATCH_PARENT));
            view.setBackgroundColor(dddividerColor);
            tabMenuView.addView(view);
        }
        //tab点击事件
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMenu(textView);
            }
        });

    }

    /**
     * 切换菜单
     * @param textView
     */
    private void switchMenu(TextView textView) {

    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
