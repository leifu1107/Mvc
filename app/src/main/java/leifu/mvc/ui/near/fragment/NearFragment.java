package leifu.mvc.ui.near.fragment;

import leifu.mvc.R;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/31 14:46
 * 描述:
 */

public class NearFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_near;
    }

    @Override
    protected void initEventAndData() {
        Logger.e("Near");
    }
}
