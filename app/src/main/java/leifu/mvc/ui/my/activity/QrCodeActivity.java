package leifu.mvc.ui.my.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.SettingService;
import com.yanzhenjie.permission.setting.PermissionSetting;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import leifu.mvc.R;
import leifu.mvc.base.BaseActivity;
import leifu.mvc.utils.Logger;
import leifu.toastlibrary.CustomToast;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/1 13:56
 * 描述:
 */

public class QrCodeActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.mQRCodeView)
    ZXingView mQRCodeView;
    @BindView(R.id.tvOpen)
    TextView tvOpen;
    @BindView(R.id.tvClose)
    TextView tvClose;
    private PermissionSetting mSetting;

    @Override
    protected int getLayout() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void initEventAndData() {
        mQRCodeView.setDelegate(this);

    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Logger.e("onScanQRCodeSuccess" + result);
        CustomToast.success(result);
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        CustomToast.error("打开相机出错");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Rationale mRationale = new Rationale() {
            @Override
            public void showRationale(Context context, List<String> permissions,
                                      final RequestExecutor executor) {
                // 这里使用一个Dialog询问用户是否继续授权。

                new AlertDialog.Builder(mContext)
                        .setCancelable(false)
                        .setTitle("提示")
                        .setMessage("同意以下权限")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 如果用户继续：
                                executor.execute();
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 如果用户中断：
                                executor.cancel();
                            }
                        }).show();
            }
        };
        AndPermission.with(this)
                .permission(Permission.CAMERA)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
                        mQRCodeView.showScanRect();
                        mQRCodeView.startSpot();
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                    final SettingService settingService = AndPermission.permissionSetting(mContext);
                    new AlertDialog.Builder(mContext)
                            .setCancelable(false)
                            .setTitle("注意")
                            .setMessage("需要您打开相机权限才可进行以下操作")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    settingService.execute();
                                }
                            })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    settingService.cancel();
                                }
                            })
                            .show();
                }
            }
        }).start();

        tvOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQRCodeView.openFlashlight();
            }
        });
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

}
