package com.example.lilei.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;
import com.bumptech.glide.Glide;
import com.example.lilei.gank.R;
import com.example.lilei.gank.component.OnStartWebViewListener;

import java.util.List;

/**
 * Created by lilei on 2017/9/12.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private Context mContext;
    private List<AVObject> mAVObjectList;
    OnStartWebViewListener onClickListener;

    public CollectionAdapter(Context mContext, List<AVObject> mAVObjectList){
        this.mContext = mContext;
        this.mAVObjectList = mAVObjectList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("TAG", "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_technology, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mAVObjectList != null){
            holder.tvAuthor.setText((CharSequence)mAVObjectList.get(position).get("who"));
            holder.tvDesc.setText((CharSequence)mAVObjectList.get(position).get("desc"));
            holder.tvTime.setText((CharSequence)mAVObjectList.get(position).get("publishedAt"));
            if (mAVObjectList.get(position).get("image") != null){
                Glide.with(mContext)
                        .load(mAVObjectList.get(position).get("image").toString())
                        .asGif()
                        .placeholder(R.mipmap.error_default)
                        .error(R.mipmap.error_default)
                        .into(holder.imageView);
            }
            Log.d("TAG", "onBindViewHolder: "+mAVObjectList.get(position).get("who").toString());
            Log.d("TAG", "onBindViewHolder: "+mAVObjectList.get(position).get("desc").toString());
            Log.d("TAG", "onBindViewHolder: "+mAVObjectList.get(position).get("publishedAt").toString());
            Log.d("TAG", "onBindViewHolder: "+mAVObjectList.get(position).get("image").toString());
        }
        Log.d("TAG", "onBindViewHolder: "+mAVObjectList);
    }

    @Override
    public int getItemCount() {
        return mAVObjectList == null ? 0 : mAVObjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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

//            if (onClickListener != null) {
//                itemView.setOnClickListener(l -> {
//                    AVObject avObject = mAVObjectList.get(getLayoutPosition());
//                    FirstLevelInterfaceItem item = new FirstLevelInterfaceItem();
//                    item.set_id((String)avObject.get(""));
//                    onClickListener.OnItemClicked(item);
//                });
//            }
        }
    }

    public void setOnCollectionClickListener(OnStartWebViewListener listener){
        this.onClickListener = listener;
    }

    public void replaceList(List<AVObject> mAVObjectList){
         this.mAVObjectList = mAVObjectList;
         notifyDataSetChanged();
    }


}
