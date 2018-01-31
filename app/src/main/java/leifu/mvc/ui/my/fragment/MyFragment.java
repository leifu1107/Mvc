package leifu.mvc.ui.my.fragment;

import leifu.mvc.R;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/31 14:46
 * 描述:
 */

public class MyFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initEventAndData() {
        Logger.e("MyFragment");
    }
}
