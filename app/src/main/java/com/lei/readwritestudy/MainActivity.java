package com.lei.readwritestudy;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 两种最常见的数据存储方式
 * <p>
 * 1.内存
 * 2.本地
 * (手机内部存储，手机外部存储sd卡)
 *
 * 正斜杠‘/’代表根目录
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //read();
    }

    private void write() {
        //获取外部存储的目录
        File file = Environment.getExternalStorageDirectory();

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file.getPath() + "hello.txt");
            out.write("12345".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void read() {
        FileInputStream in = null;
        try {
            in = new FileInputStream("/data/data/com.lei.readwritestudy/hello.txt");
            byte[] bytes = new byte[128];
            int len = in.read(bytes);
            String str = new String(bytes,0,len);
            Log.i("tag",str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 检查sdcard是否被挂载
     */
    private void existSDcard() {
        //获取sd卡的状态
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            Log.i("tag","手机有sd卡");
        } else {
            Log.i("tag","手机没有sd卡");
        }
    }

    /**
     * 通过api获取路径
     */
    private void listPath() {
        //获取sd卡路径
        File file = Environment.getExternalStorageDirectory();
        Log.i("tag","SD卡---" + file.getPath());

        //获取手机内部存储空间的file目录
        File file2 = getFilesDir();
        Log.i("tag","内存" + file2.getPath());

        //获取内部存储空间的缓存
        File file3 = getCacheDir();
        Log.i("tag","缓存目录" + file3.getPath());
    }
}
