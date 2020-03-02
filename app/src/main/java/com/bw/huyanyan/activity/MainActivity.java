package com.bw.huyanyan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.huyanyan.R;
import com.bw.huyanyan.adapter.MyAdapter1;
import com.bw.huyanyan.adapter.MyAdapter2;
import com.bw.huyanyan.adapter.MyAdapter3;
import com.bw.huyanyan.base.BaseActivity;
import com.bw.huyanyan.bean.Bean;
import com.bw.huyanyan.contract.IHomeContract;
import com.bw.huyanyan.presenter.IHomePagePreshenter;
import com.bw.huyanyan.utlie.VolleyUtile;
import com.google.gson.Gson;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:14:56
 *@Description:
 * */
public class MainActivity extends BaseActivity implements IHomeContract.IView {
    IHomePagePreshenter mPreshenter;

    private RecyclerView rv1;
    private RecyclerView rv2;
    private RecyclerView rv3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //找控件
        rv1 = findViewById(R.id.rv1);
        rv2 = findViewById(R.id.rv2);
        rv3 = findViewById(R.id.rv3);
    }

    @Override
    protected void getData() {

        String url="http://blog.zhaoliang5156.cn/api/shop/jingdong.json";
        ((IHomePagePreshenter)mPreshenter).getList(url);
    }

    @Override
    public void onSuccess(String str) {
        Log.i("xxx",str);
        //判断是否有网
        boolean iswork = VolleyUtile.getInstance().iswork(this);
        if (iswork){
            //解析
            Gson gson = new Gson();
            Bean bean = gson.fromJson(str, Bean.class);
            Bean.DataBean data = bean.getData();
            List<Bean.DataBean.HorizontalListDataBean> horizontalListData = data.getHorizontalListData();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            rv1.setLayoutManager(gridLayoutManager);
            //创建适配器
            MyAdapter1 myAdapter1 = new MyAdapter1(MainActivity.this, horizontalListData);
            rv1.setAdapter(myAdapter1);


            List<Bean.DataBean.VerticalListDataBean> verticalListData = data.getVerticalListData();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rv2.setLayoutManager(linearLayoutManager);
            //创建适配器
            MyAdapter2 myAdapter2 = new MyAdapter2(MainActivity.this, verticalListData);

            rv2.setAdapter(myAdapter2);


            List<Bean.DataBean.GridDataBean> gridData = data.getGridData();
            GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 2);
            rv3.setLayoutManager(gridLayoutManager1);
            //创建适配器
            MyAdapter3 myAdapter3 = new MyAdapter3(MainActivity.this, gridData);

            rv3.setAdapter(myAdapter3);

        }else{
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onFrailure(String str) {
        Log.i("xxx",str);
    }
}
