package leifu.mvc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import leifu.mvc.app.App;
import leifu.mvc.base.BaseActivity;
import leifu.mvc.ui.home.fragment.HomeFragment;
import leifu.mvc.ui.my.fragment.MyFragment;
import leifu.mvc.ui.near.fragment.NearFragment;
import leifu.mvc.view.NoScrollViewPager;
import leifu.toastlibrary.CustomToast;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp)
    NoScrollViewPager vp;
    @BindView(R.id.bnv)
    BottomNavigationViewEx bnv;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NearFragment());
        fragmentList.add(new MyFragment());
        vp.setOffscreenPageLimit(3);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        vp.setAdapter(vpAdapter);
        bnv.setupWithViewPager(vp, false);
    }

    class VpAdapter extends FragmentPagerAdapter {

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    long mExitTime = 0;

    @Override
    public void onBackPressedSupport() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            CustomToast.normal("再按一次退出");
            mExitTime = System.currentTimeMillis();
        } else {
            App.getInstance().exitApp();
        }
    }
}
