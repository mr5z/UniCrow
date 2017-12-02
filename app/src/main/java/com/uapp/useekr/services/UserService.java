package com.uapp.useekr.services;

import com.uapp.useekr.models.User;
import com.uapp.useekr.utils.HttpUtil.KeyValue;

/**
 * Created by root on 12/2/17.
 */

public class UserService extends BaseService<User> {

    private static UserService _instance;

    public static UserService instance() {
        if (_instance == null) {
            _instance = new UserService();
        }
        return _instance;
    }

    private UserService() {
        super("user");
    }

    public PagedResult<User> login(String email, String password) {
        return postObject(action("login"),
                KeyValue.make("email", email),
                KeyValue.make("password", password));
    }
}
