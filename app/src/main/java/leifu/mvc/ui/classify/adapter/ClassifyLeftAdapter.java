package leifu.mvc.ui.classify.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import leifu.mvc.R;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/11 13:29
 * 描述:
 */

public class ClassifyLeftAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ClassifyLeftAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_classify, item);
    }
}
