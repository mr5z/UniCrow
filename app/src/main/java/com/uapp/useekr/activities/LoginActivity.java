package com.uapp.useekr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

import com.uapp.useekr.R;
import com.uapp.useekr.models.User;
import com.uapp.useekr.services.PagedResult;
import com.uapp.useekr.services.UserService;
import com.uapp.useekr.utils.TaskWrapper;

import butterknife.BindView;

/**
 * Created by root on 12/2/17.
 */

public class LoginActivity extends BaseActivity
        implements View.OnClickListener, TaskWrapper.Task<PagedResult<User>> {

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.edit_email)
    TextInputEditText editEmail;

    @BindView(R.id.edit_password)
    TextInputEditText editPassword;

    private TaskWrapper<PagedResult<User>> loginTask() {
        return new TaskWrapper<>(this);
    }

    @Override
    int contentLayout() {
        return R.layout.activity_login;
    }

    @Override
    void initialize(@Nullable Bundle savedInstanceState) {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        loginTask().execute();
    }

    @Override
    public void onTaskBegin() {
        showLoader(true);
    }

    @Override
    public PagedResult<User> onTaskExecute() {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        return UserService.instance().login(email, password);
    }

    @Override
    public void onTaskEnd(PagedResult<User> result) {
        showLoader(false);
        if (result.isSuccess()) {
            showMainActivity(result.data);
        }
        else {
            showSnackbar(result.errorMessage);
        }
    }

    private void showMainActivity(User user) {
        startSession(user.getId());
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
