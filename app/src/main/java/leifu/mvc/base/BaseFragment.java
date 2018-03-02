package leifu.mvc.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.classic.common.MultipleStatusView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import leifu.mvc.R;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/29 17:12
 * 描述:
 */

public abstract class BaseFragment extends SupportFragment {
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInited = false;
    private View loadingView;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initEventAndData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();

    public void showLoading(MultipleStatusView msv_statusview) {
        if (loadingView == null) {
            loadingView = LayoutInflater.from(mContext).inflate(R.layout.custom_loading_view, null);
        }
        msv_statusview.showLoading(loadingView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


    public void mStartActivity(Class<?> intentActivity, Bundle bundle) {
        Intent intent = new Intent(mContext, intentActivity);
        intent.putExtras(bundle);
        super.startActivity(intent);
    }

    public void mStartActivity(Class<?> intentActivity) {
        Intent intent = new Intent(mContext, intentActivity);
        super.startActivity(intent);
    }
}
