package com.uapp.useekr.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uapp.useekr.utils.Settings;

import butterknife.ButterKnife;

/**
 * Created by root on 12/2/17.
 */

public abstract class BaseFragment extends Fragment {

    Settings settings() {
        return Settings.instance(getContext());
    }

    private Snackbar _snackbar;
    private Snackbar snackbar() {
        if (_snackbar == null) {
            View view = getView();
            _snackbar = Snackbar.make(view.findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG);
        }
        _snackbar.dismiss();
        return _snackbar;
    }

    private ProgressDialog _loader;
    private ProgressDialog loader() {
        if (_loader == null) {
            _loader = new ProgressDialog(getContext());
            _loader.setCancelable(false);
            _loader.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    _loader.dismiss();
                }
            });
        }
        return _loader;
    }

    void showLoader(boolean show, int titleId) {
        loader().setTitle(titleId);
        showLoader(show);
    }

    void showLoader(boolean show) {
        if (show) {
            loader().show();
        }
        else {
            loader().dismiss();
        }
    }

    void showSnackbar(String message) {
        snackbar().setText(message).show();
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(contentLayout(), container, false);
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        getActivity().setTitle(contentTitle());
        initialize();
    }

    protected void initialize() { }

    @LayoutRes
    protected abstract int contentLayout();

    protected abstract int contentTitle();
}
