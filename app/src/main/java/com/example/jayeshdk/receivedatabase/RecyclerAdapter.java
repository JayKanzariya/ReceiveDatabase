package com.example.jayeshdk.receivedatabase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private static final int TYPE_HEAD = 0 ;
    private static final int TYPE_LIST = 1 ;
    ArrayList<Data> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Data> arrayList)
    {
        this.arrayList = arrayList ;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType==TYPE_HEAD)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_layout,viewGroup,false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,viewType);
            return recyclerViewHolder;
        }
        else if (viewType==TYPE_LIST) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,viewType);
            return recyclerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int viewType) {
        if (holder.viewType == TYPE_LIST) {
            Data data = arrayList.get(viewType - 1);
            holder.Name.setText(data.getName());
            holder.Calories.setText(Integer.toString(data.getCalories()));
            holder.Fat.setText(String.valueOf(data.getFat()));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name,Calories,Fat;
        int viewType ;
        public RecyclerViewHolder(View view,int viewType)
        {
            super(view);
            if(viewType==TYPE_LIST)
            {
                Name = (TextView) view.findViewById(R.id.name);
                Calories = (TextView) view.findViewById(R.id.calories);
                Fat = (TextView) view.findViewById(R.id.fat);
                this.viewType = TYPE_LIST ;
            }
            else if(viewType == TYPE_HEAD)
            {
                this.viewType = TYPE_HEAD ;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return TYPE_HEAD;
            return TYPE_LIST;
    }
}
