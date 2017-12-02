package com.uapp.useekr.adapters;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uapp.useekr.R;
import com.uapp.useekr.models.Task;

import java.util.List;

/**
 * Created by root on 12/2/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> dataSet;

    public TaskAdapter(List<Task> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_row, parent, false);
        return new ViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = dataSet.get(position);
        holder.txtNumber.setText(String.valueOf(position + 1));
        holder.editTitle.setText(task.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNumber;
        TextInputEditText editTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNumber = itemView.findViewById(R.id.edit_transaction_create_task_number);
            editTitle = itemView.findViewById(R.id.edit_transaction_create_task_title);
        }
    }
}
