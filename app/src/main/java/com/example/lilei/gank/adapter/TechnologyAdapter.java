package com.example.lilei.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lilei.gank.R;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.example.lilei.gank.modoules.OnMyItemClickListener;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/5.
 */

public class TechnologyAdapter extends RecyclerView.Adapter<TechnologyAdapter.ViewHolder> {

    ArrayList<FirstLevelInterfaceItem> technologyArrayList;
    Context mContext;
    OnMyItemClickListener onMyItemClickListener;

    public TechnologyAdapter(ArrayList<FirstLevelInterfaceItem> TechnologyArrayList, Context mContext){
        this.technologyArrayList = TechnologyArrayList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_technology, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FirstLevelInterfaceItem technologyItem = technologyArrayList.get(position);
        if (technologyItem.getImages() != null){
            Glide.with(mContext)
                    .load(technologyItem.getImages())
                    .error(R.mipmap.error_default)
                    .into(holder.imageView);
        }
        holder.tvDesc.setText(technologyItem.getDesc());
        if (technologyItem.getWho() != null){
            holder.tvAuthor.setText(technologyItem.getWho());
        }
        holder.tvTime.setText(technologyItem.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return technologyArrayList == null ? 0 : technologyArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvDesc;
        TextView tvAuthor;
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.technology_imageView);
            tvDesc = (TextView)itemView.findViewById(R.id.technology_desc);
            tvAuthor = (TextView)itemView.findViewById(R.id.technology_author);
            tvTime = (TextView)itemView.findViewById(R.id.technology_time);

            if (onMyItemClickListener != null){
                itemView.setOnClickListener(l ->
                        onMyItemClickListener.OnItemClicked(technologyArrayList.get(getLayoutPosition()).getUrl()));
            }
        }
    }
}
