package com.example.administrator.myapplication.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/13.
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = -1;
    public static final int TYPE_FOOTER = -3;
    public List<T> dataList = new ArrayList<>();
    private View mHeaderView;
    private View mFooterView;



    public interface OnHeaderClickListener {
        void onHeaderClick();
    }

    private OnItemClickListener mListener;
    private OnHeaderClickListener mHeaderListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener l) {
        mHeaderListener = l;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void removeHeaderView() {
        if (mHeaderView != null) {
            mHeaderView = null;
            notifyItemRemoved(0);
        }
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
    }

    public void setData(List<T> dataList) {
        this.dataList = dataList;
    }

    public List<T> getData() {
        return dataList;
    }

    public void refreshData(List<T> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;

        if (mHeaderView != null && position == 0)
            type = TYPE_HEADER;
        else if (mFooterView!=null&&position == dataList.size()+IsAddedHeader())
            type = TYPE_FOOTER;
        else
            type = getItemType(position);

        return type;
    }

    public abstract int getItemType(int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
        if (mFooterView != null && viewType == TYPE_FOOTER) return new Holder(mFooterView);
        return onCreate(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (IsHeadViewOrFootView(viewHolder, position)) return;
        final int pos = getRealPosition(viewHolder);

        final T data = dataList.get(pos);
        onBind(viewHolder, pos, data);
        if (mListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onItemClick(pos, data);
                }
            });
        }
    }

    private boolean IsHeadViewOrFootView(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            if (mHeaderListener != null)
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mHeaderListener.onHeaderClick();
                    }
                });

            return true;
        } else if (getItemViewType(position) == TYPE_FOOTER) {

            return true;
        }
        return false;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return dataList.size() + IsAddedHeader() + IsAddedFooter();
    }

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final int viewType);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, T data);

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }

    public int IsAddedHeader() {
        if (mHeaderView != null) {
            return 1;
        } else return 0;
    }

    public int IsAddedFooter() {
        if (mFooterView != null) {
            return 1;
        } else return 0;
    }
}
