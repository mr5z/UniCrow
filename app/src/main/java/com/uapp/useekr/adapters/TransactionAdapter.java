package com.uapp.useekr.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.uapp.useekr.R;
import com.uapp.useekr.models.Transaction;

import java.util.List;

/**
 * Created by root on 12/2/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> implements View.OnClickListener {

    private static final int ANIMATION_DURATION = 800;

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
        holder.txtDateCreated.setText(transaction.getFormattedDateCreated());
        Transaction.TransactionStatus status = transaction.getTransactionStatus();
        holder.imgStatus.setVisibility(status == Transaction.TransactionStatus.COMPLETED ?
                View.VISIBLE : View.GONE);
        setScaleAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public List<Transaction> getDataSet() {
        return dataSet;
    }

    private void setScaleAnimation(View view, int position) {
        int size = dataSet.size();
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f * (size / (position + 1)),
                Animation.RELATIVE_TO_SELF, 0.5f * (size / (position + 1)));
        anim.setDuration(ANIMATION_DURATION);
        view.startAnimation(anim);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtDateCreated;
        ImageView imgStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.transaction_title);
            txtDateCreated = itemView.findViewById(R.id.transaction_date_created);
            imgStatus = itemView.findViewById(R.id.transaction_image_status);
        }
    }
}
