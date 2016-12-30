package com.chenli.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Tngou> {


    RelativeLayout activitymain;
    private ListView lv_main;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        adapter = new MyAdapter(this, new ArrayList<Tngou.TngouEntity>());
        lv_main.setAdapter(adapter);
    }

    public void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IService    iService = retrofit.create(IService.class);
        Call<Tngou> call     = iService.getList("api",1, 20, 0);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Tngou> call, Response<Tngou> response) {
        List<Tngou.TngouEntity> list = response.body().getTngou();
        adapter.addAll(list);
    }

    @Override
    public void onFailure(Call<Tngou> call, Throwable t) {
            t.printStackTrace();
    }

    private void initView() {
        lv_main = (ListView) findViewById(R.id.lv_main);
        activitymain = (RelativeLayout) findViewById(R.id.activity_main);
    }
}
