package com.uapp.useekr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.uapp.useekr.R;
import com.uapp.useekr.adapters.TimelineAdapter;
import com.uapp.useekr.models.Task;
import com.uapp.useekr.models.Transaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by root on 12/2/17.
 */

public class TransactionDetailActivity extends BaseActivity {

    @BindView(R.id.transaction_detail_list)
    RecyclerView detailList;

    @Override
    int contentLayout() {
        return R.layout.activity_transaction_detail;
    }

    @Override
    void initialize(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        Transaction transaction = (Transaction)
                intent.getSerializableExtra("transaction");

        List<Task> taskList = transaction.getTaskList();

//        taskList = new ArrayList<Task>() {{
//            add(new Task() {{ setTitle("Task 1"); }});
//            add(new Task() {{ setTitle("Task 2"); }});
//            add(new Task() {{ setTitle("Task 3"); }});
//            add(new Task() {{ setTitle("Task 4"); }});
//        }};

        if (taskList == null || taskList.isEmpty()) {
            return;
        }

        TimelineAdapter adapter = new TimelineAdapter(taskList, LinearLayoutManager.VERTICAL);

        detailList.setLayoutManager(new LinearLayoutManager(this));
        detailList.setAdapter(adapter);
    }
}
