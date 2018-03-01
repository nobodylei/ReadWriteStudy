package com.lei.studysharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        name : 配置文件的文件名
        mode : 配置文件的模式（权限）
        MODE_PRIVATE 私有的，只能被这个app使用
         */
        preferences = getSharedPreferences("config",MODE_PRIVATE);
    }

    private void write() {
        //得到编辑器
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("language", "English");
        edit.putBoolean("s",true);
        //提交内存的配置信息到本地
        edit.commit();
    }

    private void read() {
        //defValue 默认值,没有key返回默认值
        String lang = preferences.getString("language","默认值");
        boolean s = preferences.getBoolean("s",true);
        Log.i("Tag",lang + s);
    }
}
