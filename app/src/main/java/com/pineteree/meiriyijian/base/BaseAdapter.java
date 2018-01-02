package com.pineteree.meiriyijian.base;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.pineteree.meiriyijian.R;
import com.pineteree.meiriyijian.common.Constant;
import com.pineteree.meiriyijian.common.Utils;
import com.pineteree.meiriyijian.db.DbManager;
import com.pineteree.meiriyijian.girl.model.PicModel;
import com.pineteree.meiriyijian.home.model.GankModel;
import com.pineteree.meiriyijian.image.ImageManager;


import java.util.List;

/**
 * 作者：tlf
 * 描述:
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    private Context mContext;
    private List<GankModel.ResultsEntity> mListData;
    private OnBaseClickListener mBaseClickListener;
    private int mItemType;//条目类型
    private boolean mIsStaggered = false;

    /**
     * 监听回调接口定义
     */
    public interface OnBaseClickListener {
        void onClick(int position, GankModel.ResultsEntity entity);

        void onCoverClick(int position, GankModel.ResultsEntity entity);
    }

    public BaseAdapter(Context mContext, List<GankModel.ResultsEntity> mListData, int itemType) {
        this.mContext = mContext;
        this.mListData = mListData;
        this.mItemType = itemType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (mItemType == Constant.ITEM_TYPE_TEXT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_homefragment, parent,
                    false);
        } else if (mItemType == Constant.ITEM_TYPE_IMAGE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_homefragment_girl, parent,
                    false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GankModel.ResultsEntity resultsEntity = mListData.get(position);
        if (mItemType == Constant.ITEM_TYPE_TEXT) {
            holder.tvTitle.setText(resultsEntity.getDesc());
            holder.tvTime.setText(TimeUtils.getFriendlyTimeSpanByNow(Utils.formatDateFromStr
                    (resultsEntity.getPublishedAt())));
            holder.tvAuthor.setText(resultsEntity.getWho());
            if (resultsEntity.getImages() != null && resultsEntity.getImages().size() > 0) {
                //如果存在图片，则展示缩率图
                holder.ivCover.setVisibility(View.VISIBLE);
                ImageManager.getInstance().loadImage(mContext, resultsEntity.getImages().get(0),
                        holder.ivCover);
            } else {
                //如果不存在图片，则不展示缩率图
                holder.ivCover.setVisibility(View.GONE);
            }


            //缩率图点击事件响应
            holder.ivCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (resultsEntity.getImages() != null && resultsEntity.getImages().size() > 0) {

                        mBaseClickListener.onCoverClick(position, resultsEntity);
                    } else {
                        ToastUtils.showShortSafe("木有发现图片哟");
                    }
                }
            });
        }

        if (mItemType == Constant.ITEM_TYPE_IMAGE) {
            //加载福利模式下图片
            ImageManager.getInstance()
                    .loadImage(mContext,
                            resultsEntity.getUrl(),
                            holder.imageGirl);
        }

        if (mItemType == Constant.ITEM_TYPE_IMAGE) {
            //判断是否是瀑布流模式
            if (mIsStaggered) {
                //动态加载高度
                PicModel picModel = (PicModel) DbManager.getInstence().queryModel(PicModel.class, resultsEntity.get_id());
                ViewGroup.LayoutParams lp = holder.cardView.getLayoutParams();
                if (null == picModel) {
                    picModel = new PicModel();
                    //生成高度并保存
                    lp.height = (int) (Math.random() * 300 + 500);
                    picModel.setHeight(lp.height);
                    picModel.set_id(resultsEntity.get_id());
                    DbManager.getInstence().save(picModel);
                }
                lp.height = picModel.getHeight();
                holder.cardView.setLayoutParams(lp);
            }
            holder.imageGirl.setTag(R.id.iv_girl, resultsEntity.getUrl());
            ImageManager.getInstance()
                    .loadImage(mContext,
                            resultsEntity.getUrl(),
                            holder.imageGirl);
        }

        //列表单个条目点击事件响应
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mBaseClickListener) {
                    mBaseClickListener.onClick(position, resultsEntity);
                }
            }
        });


    }

    public void setmIsStaggered(boolean mIsStaggered) {
        this.mIsStaggered = mIsStaggered;
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setmListData(List<GankModel.ResultsEntity> mListData) {
        this.mListData = mListData;
    }


    public void addOnClickListener(OnBaseClickListener baseClickListener) {
        this.mBaseClickListener = baseClickListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        //整体布局
        View rootView;
        //标题
        TextView tvTitle;
        //作者
        TextView tvAuthor;
        //时间
        TextView tvTime;
        //封面缩率图
        ImageView ivCover;
        //福利模式下的图片
        ImageView imageGirl;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            imageGirl = (ImageView) itemView.findViewById(R.id.iv_girl);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
