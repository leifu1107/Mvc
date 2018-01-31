package leifu.mvc;

import android.os.Handler;

import leifu.mvc.app.Constants;
import leifu.mvc.base.BaseActivity;
import leifu.mvc.utils.PreferencesUtils;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/29 14:12
 * 描述:闪屏页
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirst = PreferencesUtils.getBoolean(Constants.sp_guide_first, true);
                if (isFirst) {
                    //跳转页面
                    mStartActivity(GuideActivty.class);
                } else {
                    mStartActivity(MainActivity.class);
                }
                finish();

            }
        }, 1000);
    }
}
