package leifu.mvc;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import leifu.mvc.app.App;
import leifu.mvc.base.BaseActivity;
import leifu.mvc.ui.classify.fragment.ClassifyFragment;
import leifu.mvc.ui.home.fragment.HomeFragment;
import leifu.mvc.ui.my.fragment.MyFragment;
import leifu.mvc.ui.near.fragment.NearFragment;
import leifu.mvc.utils.Logger;
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
        bnv.enableShiftingMode(false);
        bnv.enableItemShiftingMode(false);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ClassifyFragment());
        fragmentList.add(new NearFragment());
        fragmentList.add(new MyFragment());
        vp.setOffscreenPageLimit(4);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), fragmentList);
        vp.setAdapter(vpAdapter);

        Logger.e("aaa" + fragmentList.size());
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                SoundUtils.playSound(R.raw.didi);
                Logger.e("aaa" + item.getTitle().toString());
                return true;
            }
        });
        bnv.setupWithViewPager(vp, true);
    }

    private class VpAdapter extends FragmentPagerAdapter {

        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            Logger.e("aaa----" + position);
            return data.get(position);
        }

        @Override
        public int getCount() {
            return data.size();
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
