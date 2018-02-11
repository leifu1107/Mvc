package leifu.mvc.ui.classify.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/11 14:20
 * 描述:
 */

public class MultipleItem implements MultiItemEntity {


    public static final int TEXT = 1;
    public static final int IMG = 2;
    private int itemType;
    private int spanSize;
    Object object;
    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


    public MultipleItem(int itemType, int spanSize, Object object) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.object = object;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
