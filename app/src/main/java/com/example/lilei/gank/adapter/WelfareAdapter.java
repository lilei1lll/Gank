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
import com.example.lilei.gank.component.util.CommonUtil;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.example.lilei.gank.modoules.OnMyClickListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lilei on 2017/9/7.
 */

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<FirstLevelInterfaceItem> mWelfareArrayList;
    private OnMyClickListener myClickListener;

    public WelfareAdapter(ArrayList<FirstLevelInterfaceItem> mWelfareArrayList, Context mContext){
        this.mWelfareArrayList = mWelfareArrayList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welfare, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.welfare_item_imageView);
        //给imageview的高度设置动态高度,实现瀑布流,也可以用其它方法去动态的设置它的高度
        imageView.getLayoutParams().height = (int) (new Random().nextInt(150) + 480);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
//        layoutParams.height = (int)(300 + Math.random()*400);
//        holder.itemView.setLayoutParams(layoutParams);

        if (mWelfareArrayList != null){
            FirstLevelInterfaceItem welfareItem = mWelfareArrayList.get(position);
            Glide.with(mContext)
                    .load(welfareItem.getUrl())
//                    .placeholder(R.mipmap.error_default)
//                    .error(R.mipmap.error_default)
                    .into(holder.imageView);
             holder.tvAuthor.setText(CommonUtil.safeText(welfareItem.getWho()));
        }
    }

    @Override
    public int getItemCount() {
        return mWelfareArrayList == null ? 0 : mWelfareArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.welfare_item_imageView);
            tvAuthor = (TextView) itemView.findViewById(R.id.welfare_item_author);

            itemView.setOnClickListener(l ->
                    myClickListener.OnItemClicked(
                            mWelfareArrayList.get(getLayoutPosition()).getUrl(),
                            mWelfareArrayList.get(getLayoutPosition()).getWho()));
        }
    }

    public void setMyClickListener(OnMyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }
}
