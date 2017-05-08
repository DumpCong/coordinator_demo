package com.xu.workwork.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xu.workwork.R;

import java.util.List;

/**
 * Created by xu on 2017/3/28.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    private Context context;
    private List<String> content;
    private OnItemClickListener listener;


    public TestAdapter(Context context, List<String> content) {
        this.context = context;
        this.content = content;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //第三个参数一定要写false
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_content, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tv_content.setText(content.get(position));
        // 添加点击监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    //因为recyclerview的条目数会发生变化 所以使用layoutPosition而不是positon
                    listener.onClick(holder.itemView, holder.getLayoutPosition());
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener != null) {
                    //因为recyclerview的条目数会发生变化 所以使用layoutPosition而不是positon
                    listener.onLongClick(holder.itemView, holder.getLayoutPosition());
                }
                return false;
            }
        });
        holder.tv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTextViewClick(holder.tv_content, holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_content;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    public void addItem(int position) {
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        notifyItemRemoved(position);
    }

    //自定义事件处理器接口
    interface OnItemClickListener {
        //条目点击事件
        void onClick(View itemView, int position);

        //长摁事件
        void onLongClick(View itemView, int position);

        //内部组件点击事件
        void onTextViewClick(View textView, int position);
    }

}
