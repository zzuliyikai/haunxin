package com.example.administrator.huanxin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView etUsername;
    private AutoCompleteTextView etPassword;
    private Button btnRegister;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        initListener();
    }

    private void initView() {
        etUsername = (AutoCompleteTextView) findViewById(R.id.et_username);
        etPassword = (AutoCompleteTextView) findViewById(R.id.et_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    private void initListener() {
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_register:
               new Thread(){

                   @Override
                   public void run() {
                       try {
                           EMClient.getInstance().createAccount(etUsername.getText().toString().trim(),etPassword.getText().toString().trim());

                       } catch (HyphenateException e) {
                           e.printStackTrace();
                       }

                   }
               }.start();
               break;
           case R.id.btn_login:

               EMClient.getInstance().login(etUsername.getText().toString().trim(), etPassword.getText().toString().trim(),new EMCallBack() {//回调
                   @Override
                   public void onSuccess() {
                       System.out.println("名字:"+etUsername.getText().toString().trim()+"   密码:"+etPassword.getText().toString().trim());

                       EMClient.getInstance().groupManager().loadAllGroups();
                       EMClient.getInstance().chatManager().loadAllConversations();
                       startActivity(new Intent(LoginActivity.this,MainActivity.class));
                   }
                   @Override
                   public void onProgress(int progress, String status) {
                       System.out.println("progress"+progress);
                   }
                   @Override
                   public void onError(int code, String message) {
              //       Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
               //        System.out.print("wwwwwlogin"+message);
                  //     System.out.println("我就是喜欢你了");

                   }
               });

               break;
       }
    }

//    public void register(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    EMClient.getInstance().createAccount(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
//                }catch (final  HyphenateException e){
//
//                    System.out.println("错误信息"+e.getMessage());
//                }
//            }
//        }).start();
//
//    }
}
