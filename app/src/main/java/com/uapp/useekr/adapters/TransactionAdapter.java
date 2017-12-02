package com.uapp.useekr.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uapp.useekr.R;
import com.uapp.useekr.models.Transaction;

import java.util.List;

/**
 * Created by root on 12/2/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> implements View.OnClickListener {

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    private List<Transaction> dataSet;

    private ItemClickListener itemClickListener;

    public TransactionAdapter(List<Transaction> dataSet) {
        this.dataSet = dataSet;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick((Integer) view.getTag());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_row, parent, false);
        rowLayout.setOnClickListener(this);
        return new ViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = dataSet.get(position);
        holder.itemView.setTag(position);
        holder.txtTitle.setText(transaction.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public List<Transaction> getDataSet() {
        return dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.transaction_title);
        }
    }
}
