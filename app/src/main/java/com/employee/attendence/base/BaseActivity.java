package com.employee.attendence.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.employee.attendence.appconstant.Constants;


import smartx.helper.Retrofit.ApiClient;

import static com.employee.attendence.retrofit.ApiInterface.API_SERVER_URL;


public abstract class BaseActivity extends AppCompatActivity implements Constants {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(getLayoutResource());
        ApiClient.setBaseUrl(API_SERVER_URL);
    }

    protected abstract int getLayoutResource();

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
