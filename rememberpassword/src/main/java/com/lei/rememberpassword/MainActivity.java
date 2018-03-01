package com.lei.rememberpassword;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_username, et_password;
    private CheckBox cb_remember;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        cb_remember = findViewById(R.id.cb_remember);
        //是否记住密码
        boolean isChecked = preferences.getBoolean("isChecked",false);
        if(isChecked) {
            et_username.setText(preferences.getString("username",""));
            et_password.setText(preferences.getString("password",""));
        }
        cb_remember.setChecked(isChecked);
    }

    //点击事件
    public void doClick(View view) {
        SharedPreferences.Editor edit = preferences.edit();
        String userName = et_username.getText().toString();
        String passWord = et_password.getText().toString();
        if(userName.equals("123456") && passWord.equals("654321")) {
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
        }
        boolean isChecked = cb_remember.isChecked();
        //存储CheckBox的状态
        edit.putBoolean("isChecked", isChecked);
        if(isChecked) {
            edit.putString("username",userName);
            edit.putString("password",passWord);
        } else {
            edit.remove("username").remove("password");
        }
        //提交到本地
        edit.commit();
    }
}
