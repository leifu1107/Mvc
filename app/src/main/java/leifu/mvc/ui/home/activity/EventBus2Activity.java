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

public class EventBus2Activity extends BaseActivity {
    @BindView(R.id.tv_click)
    TextView tv_Click;

    @Override
    protected int getLayout() {
        return R.layout.activty_eventbus2;
    }

    @Override
    protected void initEventAndData() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MessageEvent messageEvent) {
        if (messageEvent.getFlag() == 1) {
            Logger.e("222" + messageEvent.toString());
            tv_Click.setText(messageEvent.getMessage());
        }
    }

    @OnClick({R.id.tv_click})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_click:
                EventBus.getDefault().post(new MessageEvent(2, "这是2传给1"));
                finish();
                break;
            default:
                break;
        }
    }

}
