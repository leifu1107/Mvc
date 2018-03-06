package leifu.mvc.ui.home.activity;

import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import leifu.mvc.R;
import leifu.mvc.base.BaseActivity;
import leifu.mvc.bean.MessageEvent;
import leifu.mvc.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/5 9:35
 * 描述:
 */

public class EventBus1Activity extends BaseActivity {
    @BindView(R.id.tv_click)
    TextView tv_Click;

    @Override
    protected int getLayout() {
        return R.layout.activty_eventbus1;
    }

    @Override
    protected void initEventAndData() {
        setTitleText("EventBus传值", "", R.color.white);
    }

//    @Override
//    public void onEvent(MessageEvent messageEvent) {
//        super.onEvent(messageEvent);
//        if (messageEvent.getFlag()==2) {
//            Logger.e("111"+messageEvent.toString());
//            tv_Click.setText(messageEvent.getMessage());
//        }
//    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MessageEvent messageEvent) {
        Logger.e("333"+messageEvent.toString());
        if (messageEvent.getFlag() == 3) {
            Logger.e("222" + messageEvent.toString());
            tv_Click.setText(messageEvent.getMessage());
        }
    }
    @OnClick({R.id.tv_click})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_click:
                //后面还未注册需要粘性post
               EventBus.getDefault().postSticky(new MessageEvent(1, "这是1传给2"));
                EventBus.getDefault().post(new MessageEvent(4, "这是1传给fragment"));
                finish();
//                mStartActivity(EventBus2Activity.class);
                break;
            default:
                break;
        }
    }

}
