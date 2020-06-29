package com.example.wkl_android.main.welcome;

import android.content.Intent;
import android.graphics.Rect;
import android.view.Window;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.login.ui.activity.LoginActivity;
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.utils.InternetUtil;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.utils.UserUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎界面
 */
public class WelcomeActivity extends BaseActivity {
    @Override
    public int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {


        Rect frame = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentTop - statusBarHeight;


        //解决app未被杀死但启动app仍会打开欢迎页面，造成欢迎页面卡顿的问题
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        startMainActivity();
    }

    private void startMainActivity() {
        TimerTask delayTask = new TimerTask() {
            @Override
            public void run() {

                if(UserUtil.isLogin()){
                    Intent mainIntent = new Intent(APP, MainActivity.class);
                    startActivity(mainIntent);
                }else {
                    Intent mainIntent = new Intent(APP, LoginActivity.class);
                    startActivity(mainIntent);
                }

//                Intent mainIntent = new Intent(APP, MainActivity.class);
//                startActivity(mainIntent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(delayTask, 1000);//延时两秒执行 run 里面的操作
    }
}
