package com.uapp.useekr.adapters;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

    public List<Task> getDataSet() {
        return dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_row, parent, false);
        return new ViewHolder(rowLayout, new TaskTitleTextWatcher());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = dataSet.get(position);
        holder.txtNumber.setText(String.valueOf(position + 1));
        holder.editTitle.setText(task.getTitle());
        holder.textWatcher.updatePosition(position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNumber;
        TextInputEditText editTitle;
        TaskTitleTextWatcher textWatcher;
        public ViewHolder(View itemView, TaskTitleTextWatcher textWatcher) {
            super(itemView);
            txtNumber = itemView.findViewById(R.id.edit_transaction_create_task_number);
            editTitle = itemView.findViewById(R.id.edit_transaction_create_task_title);
            editTitle.addTextChangedListener(textWatcher);
            this.textWatcher = textWatcher;
        }
    }

    class TaskTitleTextWatcher implements TextWatcher {

        private int position;

        void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            dataSet.get(position).setTitle(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}
