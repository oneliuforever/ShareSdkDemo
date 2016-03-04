package com.qianfeng.liu.sharesdkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

/**
 *
 *
 *sfmdhsjfs
 *
 *
 *afvushdfshifus
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void share(View view) {

        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权,某些平台，比如新浪微博，如果不关闭sso授权，会使用已经登陆的账户进行分享
        //如果关闭，则会要求你重新登陆
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        oks.setTitleUrl("http://user.qzone.qq.com/1007748736/infocenter?ptsig=BZfuyGbqloWGY2UzJGwvwBEMFk-S-ZZcs5kGzqRwxN4_");

        // text是分享文本，所有平台都需要这个字段
        oks.setText("我的分享");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/IMG_20151130_152614.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://user.qzone.qq.com/1007748736/infocenter?ptsig=BZfuyGbqloWGY2UzJGwvwBEMFk-S-ZZcs5kGzqRwxN4_");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);

    }


    public void onLogin(View view) {
            ShareSDK.initSDK(this);
            final Platform plat_qq = ShareSDK.getPlatform(MainActivity.this,
                    QQ.NAME);
            plat_qq.setPlatformActionListener(new PlatformActionListener() {
                @Override
                public void onError(Platform plat, int action, Throwable arg2) {

                    Log.d("zy", "error");
                }

                @Override
                public void onComplete(Platform arg0, int action,
                                       HashMap<String, Object> res) {

                    Log.d("zy", "finish");

                }

                @Override
                public void onCancel(Platform plat, int action) {

                    Log.d("zy","cancel");
                }
            });
            plat_qq.showUser(null);

    }
}
