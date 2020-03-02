package com.bw.huyanyan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.huyanyan.R;

import com.bw.huyanyan.bean.Bean;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:14:56
 *@Description:
 * */
public class MyAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Bean.DataBean.HorizontalListDataBean> list;

    public MyAdapter1(Context context, List<Bean.DataBean.HorizontalListDataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item1, null);
       RecyclerView.ViewHolder viewHolder=new ViewHodel(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      Glide.with(context).load(list.get(position).getImageurl()).into(((ViewHodel)holder).iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHodel extends RecyclerView.ViewHolder{
        //找控件
       public ImageView iv;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
        }
    }
}
