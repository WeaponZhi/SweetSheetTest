package com.weaponzhi.sweetsheettest;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Button button;
    private LinearLayout linearLayout;
    private View mask;
    private RelativeLayout relativeLayout;
    private RecyclerAdapter adapter;
    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayout = (LinearLayout) findViewById(R.id.ll_sheet);
        mask = findViewById(R.id.view_mask);
        button = (Button) findViewById(R.id.btn_text);
        relativeLayout = (RelativeLayout) findViewById(R.id.first_rl);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("小之" + (i + 1));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isOpen) {
            ObjectAnimator translationY = ObjectAnimator.ofFloat(linearLayout, "translationY", 0, -500);
            translationY.setDuration(300);
            translationY.start();
            adapter.notifyDataSetChanged();
//            recyclerView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    recyclerView.scheduleLayoutAnimation();
//                }
//            },600);
//            recyclerView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            },600);
            button.setText("选择器展开");
            mask.setVisibility(View.VISIBLE);
        } else {
            ObjectAnimator translationY = ObjectAnimator.ofFloat(linearLayout, "translationY", -500, 0);
            translationY.setDuration(300);
            translationY.start();
            button.setText("选择器关闭");
            mask.setVisibility(View.GONE);
        }
        isOpen = !isOpen;
    }
}
