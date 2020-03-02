package com.bw.huyanyan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.huyanyan.R;
import com.bw.huyanyan.activity.MainActivity;
import com.bw.huyanyan.bean.Bean;

import java.util.List;

/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:15:19
 *@Description:
 * */public class MyAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Bean.DataBean.VerticalListDataBean> list;

    public MyAdapter2(Context context, List<Bean.DataBean.VerticalListDataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item2,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getImageurl()).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).name.setText(list.get(position).getTitle());
        ((ViewHolder)holder).price.setText(list.get(position).getPrice());

        ((ViewHolder)holder).price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("price",list.get(position).getPrice());
                context.startActivity(intent);
            }
        });
        ((ViewHolder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monItemClickListener!=null){
                    monItemClickListener.Click(position);
                }
            }
        });
    }
    public OnItemClickListener monItemClickListener;

    public interface OnItemClickListener{
        void Click(int position);
    };

    public OnItemClickListener getMonItemClickListener() {
        return monItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView name;
        private TextView price;
        private LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            ll=itemView.findViewById(R.id.ll);
        }
    }
}
