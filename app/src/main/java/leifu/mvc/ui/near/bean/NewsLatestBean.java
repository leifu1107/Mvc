package leifu.mvc.ui.near.bean;

import java.util.List;

/**
 * 创建人: 雷富
 * 创建时间: 2018/2/2 13:39
 * 描述:
 */

public class NewsLatestBean {

    /**
     * date : 20180202
     * stories : [{"images":["https://pic4.zhimg.com/v2-fe0a33711e9c9fbcd3315bb5e605dbbf.jpg"],"type":0,"id":9668740,"ga_prefix":"020210","title":"作为一个有修养的人，你能怎样表达自己的愤怒？"},{"images":["https://pic2.zhimg.com/v2-26244332daf13af4aaef57d57bc36229.jpg"],"type":0,"id":9669015,"ga_prefix":"020209","title":"阿里腾讯争夺线下零售，第一回合是结束了，接下来呢？"},{"images":["https://pic4.zhimg.com/v2-e3ca1950485b665f6ca8565f9a771bdb.jpg"],"type":0,"id":9668998,"ga_prefix":"020208","title":"关于「抨击父母」，我们还能说点什么"},{"images":["https://pic2.zhimg.com/v2-e95d2e68043adb69b0f0d4783daac135.jpg"],"type":0,"id":9668819,"ga_prefix":"020207","title":"这是你的身体，用不着为取悦它感到羞愧"},{"images":["https://pic3.zhimg.com/v2-26d2965d6e71cb1fd6aafc56d7eca8ea.jpg"],"type":0,"id":9669311,"ga_prefix":"020207","title":"请古天乐、张家辉等港台明星代言页游，要花一大笔钱吗？"},{"images":["https://pic3.zhimg.com/v2-3cf2fbca913e7447b681e8c80e16da36.jpg"],"type":0,"id":9668909,"ga_prefix":"020207","title":"焦虑：中国大多数博士的生存常态"},{"images":["https://pic2.zhimg.com/v2-757a34511bdcb9c4db9b0da1180339fd.jpg"],"type":0,"id":9669318,"ga_prefix":"020206","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-dd42b2d40cfed0ba21e5fa2bc6e0d3f1.jpg","type":0,"id":9669311,"ga_prefix":"020207","title":"请古天乐、张家辉等港台明星代言页游，要花一大笔钱吗？"},{"image":"https://pic2.zhimg.com/v2-33172690e23d61c610fe284f4e6f8cc9.jpg","type":0,"id":9668740,"ga_prefix":"020210","title":"作为一个有修养的人，你能怎样表达自己的愤怒？"},{"image":"https://pic1.zhimg.com/v2-20f16da6af017c3c3201159a90a9e530.jpg","type":0,"id":9669015,"ga_prefix":"020209","title":"阿里腾讯争夺线下零售，第一回合是结束了，接下来呢？"},{"image":"https://pic3.zhimg.com/v2-debc053b8322e1ff5c12d5043eb9cd4a.jpg","type":0,"id":9668998,"ga_prefix":"020208","title":"关于「抨击父母」，我们还能说点什么"},{"image":"https://pic3.zhimg.com/v2-379123abc4f0b05bd48671f0865f4dbe.jpg","type":0,"id":9668909,"ga_prefix":"020207","title":"焦虑：中国大多数博士的生存常态"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-fe0a33711e9c9fbcd3315bb5e605dbbf.jpg"]
         * type : 0
         * id : 9668740
         * ga_prefix : 020210
         * title : 作为一个有修养的人，你能怎样表达自己的愤怒？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-dd42b2d40cfed0ba21e5fa2bc6e0d3f1.jpg
         * type : 0
         * id : 9669311
         * ga_prefix : 020207
         * title : 请古天乐、张家辉等港台明星代言页游，要花一大笔钱吗？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
