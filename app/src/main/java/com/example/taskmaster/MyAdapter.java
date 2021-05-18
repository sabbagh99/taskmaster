package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public List<TaskModel> listData;


public  MyAdapter(List<TaskModel> listData){
    this.listData=listData;
}

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, body, state;



        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titleFrag);
            body = (TextView) itemView.findViewById(R.id.bodyFrag);
            state = (TextView) itemView.findViewById(R.id.stateFrag);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

         TaskModel task = listData.get(position);
        holder.title.setText(listData.get(position).getTitle());
        holder.body.setText(listData.get(position).getBody());
        holder.state.setText(listData.get(position).getState());


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
