package leifu.mvc.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import leifu.mvc.R;
import leifu.mvc.ui.classify.bean.MultipleItem;
import leifu.mvc.ui.near.bean.NewsLatestBean;
import leifu.mvc.utils.GlideImgManager;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/11 14:20
 * 描述:
 */

public class MultipleItemLayoutAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemLayoutAdapter(Context context, List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.item_newslatest);
        addItemType(MultipleItem.IMG, R.layout.item_gridlayout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.tv_news, ((NewsLatestBean.StoriesBean) item.getObject()).getTitle());
                GlideImgManager.loadImage(mContext, ((NewsLatestBean.StoriesBean) item.getObject()).getImages().get(0), (ImageView) helper.getView(R.id.iv_news));
                break;
            case MultipleItem.IMG:
                helper.setText(R.id.tv_news, ((NewsLatestBean.StoriesBean) item.getObject()).getTitle());
                GlideImgManager.loadImage(mContext, ((NewsLatestBean.StoriesBean) item.getObject()).getImages().get(0), (ImageView) helper.getView(R.id.iv_news));
                break;
        }
    }
}
