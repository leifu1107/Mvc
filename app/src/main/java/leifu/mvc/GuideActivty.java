package leifu.mvc;

import android.widget.ImageView;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import leifu.mvc.app.Constants;
import leifu.mvc.base.BaseActivity;
import leifu.mvc.utils.PreferencesUtils;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/29 14:37
 * 描述:引导页
 */

public class GuideActivty extends BaseActivity {


    @BindView(R.id.bgabanner)
    BGABanner bgabanner;

    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initEventAndData() {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        bgabanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP, R.mipmap.guide01, R.mipmap.guide02, R.mipmap.guide03);

        bgabanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                PreferencesUtils.setBoolean(Constants.sp_guide_first,false);
                mStartActivity(MainActivity.class);
                finish();
            }
        });
    }

}
