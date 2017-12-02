package com.uapp.useekr.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.uapp.useekr.fragments.BaseFragment;
import com.uapp.useekr.utils.Settings;
import com.uapp.useekr.utils.ViewUtils;

import butterknife.ButterKnife;

/**
 * Created by root on 12/2/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Settings settings() {
        return Settings.instance(this);
    }

    ViewUtils utils() {
        return ViewUtils.instance(this);
    }

    private Snackbar _snackbar;
    private Snackbar snackbar() {
        if (_snackbar == null) {
            _snackbar = Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG);
        }
        _snackbar.dismiss();
        return _snackbar;
    }

    private ProgressDialog _loader;
    private ProgressDialog loader() {
        if (_loader == null) {
            _loader = new ProgressDialog(this);
            _loader.setCancelable(false);
            _loader.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    _loader.dismiss();
                    finish();
                }
            });
        }
        return _loader;
    }

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentLayout());
        ButterKnife.bind(this);
        initialize(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    void async(Runnable task) {
        new Thread(task).start();
    }

    void startSession(long userId) {
        settings().setUserId(userId);
    }

    void endSession() {
        settings().clear();
    }

    boolean isLoggedIn() {
        return settings().isLoggedIn();
    }

    void initialize(@Nullable Bundle savedInstanceState) { }

    @LayoutRes
    abstract int contentLayout();
}