package com.example.root.kotlin_eyepetizer.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.kotlin_eyepetizer.R;

import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/20
 * desc:
 * version:1.0
 */
public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   private Context mContext;
   private List<String> beanList;
   private OnClickListener mListener;

   public TestAdapter(Context context) {
      this.mContext = context;
   }

   public void addData(List<String> list) {
      this.beanList = list;
   }

   @NonNull
   @Override
   public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_test, parent, false));
   }

   @Override
   public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      MyViewHolder viewHolder = (MyViewHolder) holder;
      String title = beanList.get(position);
      viewHolder.tv_title.setText(title);
   }

   @Override
   public int getItemCount() {
      return beanList.size();
   }

   class MyViewHolder extends RecyclerView.ViewHolder {

      TextView tv_title;
      Button btn_start;
      public View.OnClickListener listener;

      MyViewHolder(View itemView) {
         super(itemView);
         tv_title = itemView.findViewById(R.id.tv_title);
      }

   }

   public interface OnClickListener {
      void onClick(View view);
   }

   public void setListener(OnClickListener mListener) {
      this.mListener = mListener;
   }
}
