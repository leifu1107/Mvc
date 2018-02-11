package leifu.mvc.ui.classify.bean;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/11 13:56
 * 描述:
 */

public class ClassifyImgBean {
    String name;
    String img;

    public ClassifyImgBean(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
