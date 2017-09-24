package com.example.lilei.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lilei.gank.R;
import com.example.lilei.gank.component.OnStartWebViewListener;
import com.example.lilei.gank.component.util.CommonUtil;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/5.
 *
 * 空指针检错的集中处理 onBindViewHolder中处理
 */

public class TechnologyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<FirstLevelInterfaceItem> technologyArrayList;
    Context mContext;
    OnStartWebViewListener onClickListener;
    private static int Main_Item=1;
    private int Last_Item_state = 100;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 100;
    //正在加载中
    public static final int LOADING_MORE = 101;
    //没有更多了
    public static final int NO_DATA_MORE = 102;

    public TechnologyAdapter(ArrayList<FirstLevelInterfaceItem> TechnologyArrayList, Context mContext){
        this.technologyArrayList = TechnologyArrayList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Main_Item){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_technology, parent, false);
            return new ViewHolder(view);
        }
        else if (viewType == Last_Item_state){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_bottom,parent,false);
            return new LastViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            if (technologyArrayList != null){
                FirstLevelInterfaceItem technologyItem = technologyArrayList.get(position);
                ((ViewHolder)holder).imageView.setVisibility(View.VISIBLE);
                if (technologyItem.getImages() != null){
                    // 第一次优化：两个视频35M -> 加载策略
                    // 第二次优化：三个视频 10M
                    Glide.with(mContext)
                            .load(technologyItem.getImages().get(0))
//                        .override(100, 100)
//                        .thumbnail( 0.1F )
                            .asGif()
                            .placeholder(R.mipmap.error_default)
                            .error(R.mipmap.error_default)
                            .into(((ViewHolder)holder).imageView);
                } else {
                    ((ViewHolder)holder).imageView.setVisibility(View.GONE);
                }
                ((ViewHolder)holder).tvDesc.setText(technologyItem.getDesc());
//            if (technologyItem.getWho() != null){
//                holder.tvAuthor.setText(technologyItem.getWho());
//            }
                ((ViewHolder)holder).tvAuthor.setText(CommonUtil.safeText(technologyItem.getWho()));
                ((ViewHolder)holder).tvTime.setText(CommonUtil.safeAddText(technologyItem.getPublishedAt()));
            }
        } else if (holder instanceof LastViewHolder){
            LastViewHolder footViewHolder = (LastViewHolder) holder;
            switch (Last_Item_state){
                case PULLUP_LOAD_MORE:
                    footViewHolder.service_bottom_tV.setText("上拉加载更多...");
                    footViewHolder.service_bottom_progressbar.setVisibility(View.VISIBLE);
                    break;
                case LOADING_MORE:
                    footViewHolder.service_bottom_tV.setText("一大波数据在赶来...");
                    footViewHolder.service_bottom_progressbar.setVisibility(View.VISIBLE);
                    break;
                case NO_DATA_MORE:
                    footViewHolder.service_bottom_tV.setText("----end----");
                    footViewHolder.service_bottom_progressbar.setVisibility(View.GONE);
                    break;
            }
        }
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

            if (onClickListener != null){
                itemView.setOnClickListener(l -> onClickListener.OnItemClicked(technologyArrayList.get(getLayoutPosition())));
            }
        }
    }

    public class LastViewHolder extends RecyclerView.ViewHolder {
        TextView service_bottom_tV;
        ProgressBar service_bottom_progressbar;
        public LastViewHolder(View itemView) {
            super(itemView);
            service_bottom_progressbar = (ProgressBar) itemView.findViewById(R.id.recyclerView_progress_bar);
            service_bottom_tV = (TextView) itemView.findViewById(R.id.recyclerView_footer_tv);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()){
            return Last_Item_state;
        }else {
            return Main_Item;
        }
    }

    public void setOnTechnologyClickListener(OnStartWebViewListener listener){
        this.onClickListener = listener;
    }

    public void changeMoreStatus(int status) {
        Last_Item_state= status;
    }

    public void addList(ArrayList<FirstLevelInterfaceItem> firstLevelInterfaceItemArrayList){
        technologyArrayList.addAll(firstLevelInterfaceItemArrayList);
        notifyDataSetChanged();
    }
}
