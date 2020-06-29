package com.example.wkl_android.main.shop.add_shop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.wkl_android.R;
import com.example.wkl_android.databinding.ActivityWaitBinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WaitActivity extends AppCompatActivity {
    ActivityWaitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this, R.layout.activity_wait);
       binding.result.setText(getIntent().getStringExtra("message"));
       findViewById(R.id.ivLeft).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }

    public static void showResult(Context context, String message) {
        Intent intent = new Intent(context, WaitActivity.class);
        intent.putExtra("message",message);
        context.startActivity(intent);

    }
}
