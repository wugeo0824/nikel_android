package com.media2359.nickel.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media2359.nickel.R;
import com.media2359.nickel.model.DummyRecipient;
import com.media2359.nickel.ui.ViewHolder.RecipientViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xijun on 21/3/16.
 */
public class RecipientAdapter extends RecyclerView.Adapter<RecipientViewHolder> implements RecipientViewHolder.ItemExpandCollapseListener{

    Context mContext;
    List<DummyRecipient> dataList = new ArrayList<>();
    int lastExpandedPosition = -1;

    public RecipientAdapter(Context mContext, List<DummyRecipient> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public RecipientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View recipientView = inflater.inflate(R.layout.item_recipient_row_expanded,parent,false);
        RecipientViewHolder viewHolder = new RecipientViewHolder(recipientView);
        viewHolder.setListener(this);
        return viewHolder;
    }

    @Override
    public void onViewAttachedToWindow(RecipientViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onBindViewHolder(RecipientViewHolder holder, int position) {
        DummyRecipient dummyRecipient = dataList.get(position);
        //holder.setListener(this);
        holder.bindItem(dummyRecipient);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onItemExpanded(int position) {
        // if there was an already expanded item, close it
        if (lastExpandedPosition>=0){
            dataList.get(lastExpandedPosition).setExpanded(false);
            notifyItemChanged(lastExpandedPosition);
        }

        //note down the last expanded item
        lastExpandedPosition = position;
    }

    @Override
    public void onItemCollapsed(int position) {
        // if the collapsing item is the only expanded item, reset the last expanded position
        if (position == lastExpandedPosition)
            lastExpandedPosition = -1;
    }
}