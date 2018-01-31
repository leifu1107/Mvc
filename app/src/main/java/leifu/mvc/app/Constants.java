package leifu.mvc.app;

import android.os.Environment;

import java.io.File;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/29 16:13
 * 描述:放置常量类
 */

public class Constants {

    //================= KEY ====================
    //================= PATH ====================
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";

    //================= PREFERENCE ====================
    public static final String sp_guide_first = "sp_guide_first";

}
