package leifu.mvc.ui.classify.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import leifu.mvc.R;
import leifu.mvc.ui.classify.bean.ClassifyBean;
import leifu.mvc.ui.classify.bean.ClassifyImgBean;
import leifu.mvc.ui.classify.bean.MultipleItem;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/11 14:20
 * 描述:
 */

public class ClassifyRightMultipleItemAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ClassifyRightMultipleItemAdapter(Context context,List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.item_classify_left);
        addItemType(MultipleItem.IMG, R.layout.item_classify_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.tv_classify, ((ClassifyBean) item.getObject()).getName());
                break;
            case MultipleItem.IMG:
                helper.setText(R.id.tv_classify, ((ClassifyImgBean) item.getObject()).getName());
                break;
        }
    }
}
