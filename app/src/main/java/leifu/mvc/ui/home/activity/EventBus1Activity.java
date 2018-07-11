package leifu.mvc.ui.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import leifu.mvc.R;
import leifu.mvc.base.BaseActivity;
import leifu.mvc.bean.MessageEvent;
import leifu.mvc.utils.GlideImgManager;
import leifu.mvc.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/5 9:35
 * 描述:
 */

public class EventBus1Activity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.tv_click)
    TextView tv_Click;
    @BindView(R.id.tv_pic)
    ImageView tv_pic;
    @BindView(R.id.tv_camera)
    ImageView tvCamera;
    private TakePhoto takePhoto;
    InvokeParam invokeParam;

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
        Logger.e("333" + messageEvent.toString());
        if (messageEvent.getFlag() == 3) {
            Logger.e("222" + messageEvent.toString());
            tv_Click.setText(messageEvent.getMessage());
        }
    }

    @OnClick({R.id.tv_click, R.id.tv_pic, R.id.tv_camera})
    public void onClick(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        switch (view.getId()) {
            case R.id.tv_click:
                //后面还未注册需要粘性post
                EventBus.getDefault().postSticky(new MessageEvent(1, "这是1传给2"));
                EventBus.getDefault().post(new MessageEvent(4, "这是1传给fragment"));
                finish();
//                mStartActivity(EventBus2Activity.class);
                break;
            case R.id.tv_pic://相册
//                takePhoto.onPickFromGallery();
                CropOptions cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
                CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(10 * 1024).setMaxPixel(50).create();
                takePhoto.onEnableCompress(compressConfig, true);
                getTakePhoto().onPickFromDocumentsWithCrop(imageUri, cropOptions);
                break;
            case R.id.tv_camera://相机
                takePhoto.onPickFromCapture(imageUri);
                Logger.e("aaa" + imageUri.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public void takeSuccess(TResult result) {
        Logger.e("aaa" + "takeSuccess：" + result.getImage().getOriginalPath());
        GlideImgManager.loadImage(mContext, new File(result.getImage().getCompressPath()), tv_pic);
        //上传图片
        OkGo.<String>post("https://www.ebhtec.com/test/metting/upload")
                .tag(this)
                .params("file", new File(result.getImage().getCompressPath()))
                .params("meeting_id", "1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.e("上传成功" + response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logger.e("上传失败" + response.body());
                    }
                });
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Logger.e("aaa" + "takeFail：" + msg);
    }

    @Override
    public void takeCancel() {
        Logger.e("aaa" + "takeCancel：" + getResources().getString(R.string.msg_operation_canceled));
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }
}
