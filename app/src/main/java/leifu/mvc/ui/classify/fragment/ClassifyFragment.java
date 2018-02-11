package leifu.mvc.ui.classify.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import leifu.mvc.R;
import leifu.mvc.base.BaseFragment;
import leifu.mvc.ui.classify.adapter.ClassifyLeftAdapter;
import leifu.mvc.ui.classify.adapter.ClassifyRightMultipleItemAdapter;
import leifu.mvc.ui.classify.bean.ClassifyBean;
import leifu.mvc.ui.classify.bean.ClassifyImgBean;
import leifu.mvc.ui.classify.bean.MultipleItem;
import leifu.mvc.utils.Logger;

import static leifu.mvc.ui.classify.bean.MultipleItem.IMG;
import static leifu.mvc.ui.classify.bean.MultipleItem.TEXT;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/11 10:25
 * 描述:
 */

public class ClassifyFragment extends BaseFragment {
    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    Unbinder unbinder;
    String[] data = {"推荐", "男装", "女装", "数码", "推荐", "男装", "女装", "数码"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initEventAndData() {

        ClassifyLeftAdapter adapter = new ClassifyLeftAdapter(R.layout.item_classify_left, Arrays.asList(data));
        rvLeft.setLayoutManager(new LinearLayoutManager(mContext));
        rvLeft.setAdapter(adapter);
        rvLeft.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext).build());
        addDatas();
    }

    private void addDatas() {
        final List<MultipleItem> multipleItemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ClassifyBean classifyBean = new ClassifyBean("分类" + i);
            multipleItemList.add(new MultipleItem(TEXT, 3, classifyBean));
            for (int j = 0; j < 8; j++) {
                ClassifyImgBean classifyImgBean = new ClassifyImgBean("图片" + j, "img");
                multipleItemList.add(new MultipleItem(IMG, 1, classifyImgBean));
            }
        }

        ClassifyRightMultipleItemAdapter classifyRightMultipleItemAdapter = new ClassifyRightMultipleItemAdapter(mContext, multipleItemList);
        final GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        rvRight.setLayoutManager(manager);
        classifyRightMultipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                Logger.e(position + "-----" + multipleItemList.get(position).getSpanSize());
                return multipleItemList.get(position).getSpanSize();
            }
        });
        rvRight.setAdapter(classifyRightMultipleItemAdapter);

    }
}
