package com.uapp.useekr.adapters;

import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.repsly.library.timelineview.LineType;
import com.repsly.library.timelineview.TimelineView;
import com.uapp.useekr.R;
import com.uapp.useekr.models.Task;

import java.util.List;

/**
 * Created by root on 12/2/17.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private final int orientation;

    private final List<Task> dataSet;

    public TimelineAdapter(List<Task> dataSet, int orientation) {
        this.dataSet = dataSet;
        this.orientation = orientation;
    }
    @Override
    public int getItemViewType(int position) {
        if (orientation == LinearLayoutManager.VERTICAL) {
            return R.layout.task_detail_row;
        } else {
            return R.layout.item_horizontal;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = dataSet.get(position);
        holder.txtTitle.setText(task.getTitle());
        holder.timelineView.setLineType(getLineType(position));
        holder.timelineView.setNumber(position);
        // Make first and last markers stroked, others filled
        if (position == 0 || position + 1 == getItemCount()) {
            holder.timelineView.setFillMarker(false);
        } else {
            holder.timelineView.setFillMarker(true);
        }

        if (position == 4) {
            holder.timelineView.setDrawable(AppCompatResources
                    .getDrawable(holder.timelineView.getContext(),
                            R.drawable.ic_checked));
        } else {
            holder.timelineView.setDrawable(null);
        }

        // Set every third item active
        holder.timelineView.setActive(position % 3 == 2);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    private LineType getLineType(int position) {
        if (getItemCount() == 1) {
            return LineType.ONLYONE;

        } else {
            if (position == 0) {
                return LineType.BEGIN;

            } else if (position == getItemCount() - 1) {
                return LineType.END;

            } else {
                return LineType.NORMAL;
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TimelineView timelineView;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.transaction_detail_title);
            timelineView = itemView.findViewById(R.id.timeline);
        }
    }
}
