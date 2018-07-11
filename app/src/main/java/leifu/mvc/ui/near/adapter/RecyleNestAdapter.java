package leifu.mvc.ui.near.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import leifu.mvc.R;
import leifu.mvc.ui.near.bean.NewsLatestBean;
import leifu.mvc.utils.GlideImgManager;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/2 14:18
 * 描述:
 */

public class RecyleNestAdapter extends BaseQuickAdapter<NewsLatestBean.StoriesBean, BaseViewHolder> {

    private RecyclerView mRvItem;
    private List<NewsLatestBean.StoriesBean> data;
    public RecyleNestAdapter(@LayoutRes int layoutResId, @Nullable List<NewsLatestBean.StoriesBean> data) {
        super(layoutResId, data);
        this.data = data;

    }

    @Override
    protected void convert(BaseViewHolder helper, NewsLatestBean.StoriesBean item) {
        helper.setText(R.id.tv_news, item.getTitle());
        GlideImgManager.loadImage(mContext, item.getImages().get(0), (ImageView) helper.getView(R.id.iv_news));
        mRvItem = helper.getView(R.id.rvItem);
        mRvItem.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mRvItem.setAdapter(new RecyNestItemAdapter(R.layout.item_img,data));

    }
}
