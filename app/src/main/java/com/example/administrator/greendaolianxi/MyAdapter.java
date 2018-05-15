package com.example.administrator.greendaolianxi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private List<User> list;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText("ID:"+list.get(position).getId()+"姓名:"+list.get(position).getName()+"年龄："+list.get(position).getPage());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


       private final TextView tv;


        public MyViewHolder(View itemView) {
           super(itemView);
            tv = itemView.findViewById(R.id.tv);


       }
   }
}
