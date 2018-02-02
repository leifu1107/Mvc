package leifu.mvc.ui.near.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.classic.common.MultipleStatusView;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;
import leifu.mvc.R;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/31 14:46
 * 描述:
 */

public class NearFragment extends BaseFragment {
    @BindView(R.id.msv_statusview)
    MultipleStatusView msv_statusview;
    @BindView(R.id.stl_nearTab)
    SlidingTabLayout stlNearTab;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private final String[] mTitles = {"最新日报", "往期日报", "主题日报"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_near;
    }

    @Override
    protected void initEventAndData() {
        mFragments.add(new NewsLatestFragment());
        mFragments.add(new NewsLatestFragment());
        mFragments.add(new NewsLatestFragment());

        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);
        stlNearTab.setViewPager(vp, mTitles);


    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (isInited) {
//            initEventAndData();
            Logger.e("onSupportVisible");
        }


    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
