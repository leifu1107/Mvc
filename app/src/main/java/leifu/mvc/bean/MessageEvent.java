package leifu.mvc.bean;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/30 16:32
 * 描述:eventbus传值
 */

public class MessageEvent {
    /*在EventBus 3.0中，声明一个订阅方法需要用到@Subscribe注解，因此在订阅者类中添加一个有着@Subscribe注解的方法即可，方法名字可自定义，而且必须是public权限，
    其方法参数有且只能有一个，另外类型必须为第一步定义好的事件类型(比如上面的MessageEvent)
    EventBus.getDefault().post(new MessageEvent("Hello !....."));
    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){}
    */

    private int flag;
    private String message;

    public MessageEvent(int flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "flag=" + flag +
                ", message='" + message + '\'' +
                '}';
    }
}
