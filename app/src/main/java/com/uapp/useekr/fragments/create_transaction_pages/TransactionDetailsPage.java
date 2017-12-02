package com.uapp.useekr.fragments.create_transaction_pages;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uapp.useekr.R;
import com.uapp.useekr.adapters.TaskAdapter;
import com.uapp.useekr.fragments.BaseFragment;
import com.uapp.useekr.models.Task;
import com.uapp.useekr.models.Transaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by root on 12/2/17.
 */

public class TransactionDetailsPage extends BasePage {

    private List<Task> dataSet = new ArrayList<Task>() {{
        add(new Task());
    }};

    @BindView(R.id.task_list)
    RecyclerView taskList;

    @OnClick(R.id.add_task)
    void addTask() {
        dataSet.add(new Task());
        taskList.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void initialize() {
        taskList.setLayoutManager(new LinearLayoutManager(getContext()));
        TaskAdapter adapter = new TaskAdapter(dataSet);
        taskList.setAdapter(adapter);
    }

    @Override
    protected int contentLayout() {
        return R.layout.fragment_page_transaction_details;
    }

    @Override
    protected int contentTitle() {
        return R.string.transaction_details;
    }

    @Override
    void updateTransaction(View view, Transaction transaction) {
        TextInputEditText editTitle = view.findViewById(R.id.edit_transaction_create_title);
        TextInputEditText editDetails = view.findViewById(R.id.edit_transaction_create_details);
        TaskAdapter adapter = (TaskAdapter) taskList.getAdapter();

        String title = editTitle.getText().toString();
        String details = editDetails.getText().toString();
        List<Task> tasks = adapter.getDataSet();

        transaction.setTitle(title);
        transaction.setDescription(details);
        transaction.setTaskList(tasks);
    }
}
