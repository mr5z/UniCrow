package com.uapp.useekr.fragments.create_transaction_pages;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.uapp.useekr.R;
import com.uapp.useekr.adapters.TaskAdapter;
import com.uapp.useekr.fragments.BaseFragment;
import com.uapp.useekr.models.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by root on 12/2/17.
 */

public class TransactionDetailsPage extends BaseFragment {

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
}
