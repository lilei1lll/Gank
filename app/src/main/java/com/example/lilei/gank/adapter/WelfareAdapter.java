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
import com.example.lilei.gank.component.util.CommonUtil;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.example.lilei.gank.modoules.OnMyClickListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lilei on 2017/9/7.
 */

public class WelfareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<FirstLevelInterfaceItem> mWelfareArrayList;
    private OnMyClickListener myClickListener;
    private static int Main_Item=1;
    private int Last_Item_state = 100;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 100;
    //正在加载中
    public static final int LOADING_MORE = 101;
    //没有更多了
    public static final int NO_DATA_MORE = 102;


    public WelfareAdapter(ArrayList<FirstLevelInterfaceItem> mWelfareArrayList, Context mContext){
        this.mWelfareArrayList = mWelfareArrayList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == Main_Item){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_welfare, parent, false);

            ImageView imageView = (ImageView) view.findViewById(R.id.welfare_item_imageView);
            //给imageview的高度设置动态高度,实现瀑布流,也可以用其它方法去动态的设置它的高度
            imageView.getLayoutParams().height = (int) (new Random().nextInt(150) + 480);

            return new ViewHolder(view);
        } else if (viewType == Last_Item_state){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_bottom,parent,false);
            return new LastViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            if (mWelfareArrayList != null){
                FirstLevelInterfaceItem welfareItem = mWelfareArrayList.get(position);
                Glide.with(mContext)
                        .load(welfareItem.getUrl())
//                    .placeholder(R.mipmap.error_default)
                        .error(R.drawable.error_default)
                        .into(((ViewHolder)holder).imageView);
                ((ViewHolder)holder).tvAuthor.setText(CommonUtil.safeText(welfareItem.getWho()));
            }
        } else if (holder instanceof TechnologyAdapter.LastViewHolder){
            TechnologyAdapter.LastViewHolder footViewHolder = (TechnologyAdapter.LastViewHolder) holder;
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
    public int getItemViewType(int position) {
        if (position+1 == getItemCount()){
            return Last_Item_state;
        }else {
            return Main_Item;
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

    public class LastViewHolder extends RecyclerView.ViewHolder {
        TextView service_bottom_tV;
        ProgressBar service_bottom_progressbar;
        public LastViewHolder(View itemView) {
            super(itemView);
            service_bottom_progressbar = (ProgressBar) itemView.findViewById(R.id.recyclerView_progress_bar);
            service_bottom_tV = (TextView) itemView.findViewById(R.id.recyclerView_footer_tv);
        }
    }

    public void setMyClickListener(OnMyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }

    public void changeMoreStatus(int status) {
        Last_Item_state= status;
    }

    public void addList(ArrayList<FirstLevelInterfaceItem> firstLevelInterfaceItemArrayList){
        mWelfareArrayList.addAll(firstLevelInterfaceItemArrayList);
        notifyDataSetChanged();
    }
}
