package leifu.mvc.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

import leifu.mvc.app.App;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/30 13:32
 * 描述:
 */

public class GlideModelConfig implements GlideModule {
    /*<meta-data
    android:name="leifu.mvc.utils.GlideModelConfig"
    android:value="GlideModule"/>*/
    //    int diskSize = 1024 * 1024 * 100;
//    int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 8;  // 取1/8最大内存作为最大缓存
// 图片缓存最大容量，150M，根据自己的需求进行修改
    public static final int GLIDE_CATCH_SIZE = 150 * 1000 * 1000;
    // 图片缓存子目录
    public static final String GLIDE_CARCH_DIR = "image_cache";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//        // 定义缓存大小和位置
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskSize));  //内存中
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "cache", diskSize)); //sd卡中
//
//        // 默认内存和图片池大小
//        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//        int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); // 默认内存大小
//        int defaultBitmapPoolSize = calculator.getBitmapPoolSize(); // 默认图片池大小
//        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize)); // 该两句无需设置，是默认的
//        builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));
//
//        // 自定义内存和图片池大小
//        builder.setMemoryCache(new LruResourceCache(memorySize));
//        builder.setBitmapPool(new LruBitmapPool(memorySize));
//
//        // 定义图片格式
////        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565); // 默认

        //自定义缓存目录
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
               GLIDE_CARCH_DIR,
                GLIDE_CATCH_SIZE));
        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置磁盘缓存目录（和创建的缓存目录相同）
        File storageDirectory = App.getInstance().getCacheDir();
        String downloadDirectoryPath = storageDirectory + "/" + GLIDE_CARCH_DIR;
        if (!storageDirectory.exists()) {
            storageDirectory.mkdirs();
        }
        //设置缓存的大小为100M
        int cacheSize = 100 * 1000 * 1000;
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
