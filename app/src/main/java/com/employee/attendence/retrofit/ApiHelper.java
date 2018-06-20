package com.employee.attendence.retrofit;

import android.app.Activity;


import com.employee.attendence.interfaces.OnResponseCallBack;
import com.employee.attendence.model.CommonResponse;
import com.employee.attendence.model.LoginResponse;
import com.employee.attendence.model.RegisterImeiResponse;

import java.util.Map;

import smartx.helper.Retrofit.APICall;
import smartx.helper.Retrofit.APIListener;

import static com.employee.attendence.retrofit.ApiInterface.API_SERVER_URL;
//import smar


/**
 * Created by user on 27/12/17.
 */

public class ApiHelper implements ApiInterface,APIListener {

    private OnResponseCallBack onResponseCallBack;

    public ApiHelper(OnResponseCallBack onResponseCallBack) {
        this.onResponseCallBack = onResponseCallBack;
    }

    //Register IMEI Api
    public void registerIMEI(Activity activity, Map<String, String> queryParams) {
        new APICall(activity, this).APIRequest(APICall.Method.GET, API_SERVER_URL + "CheckIMEI", RegisterImeiResponse.class, queryParams, 1);
    }

    //Register IMEI Api
    public void registerUser(Activity activity, Map<String, String> queryParams) {
        new APICall(activity, this).APIRequest(APICall.Method.GET, API_SERVER_URL + "RegIMEI", CommonResponse.class, queryParams, 2);
    }

    //Register IMEI Api
    public void login(Activity activity, Map<String, String> queryParams) {
        new APICall(activity, this).APIRequest(APICall.Method.GET, API_SERVER_URL + "CheckUser", LoginResponse.class, queryParams, 3);
    }


    //Attendance with Location
    public void attendance(Activity activity, Map<String, String> queryParams) {
        new APICall(activity, this).APIRequest(APICall.Method.GET, API_SERVER_URL + "EmployeeAttEntry", LoginResponse.class, queryParams, 4);
    }

    //employee with Shift
    public void employeeShift(Activity activity, Map<String, String> queryParams) {
        new APICall(activity, this).APIRequest(APICall.Method.GET, API_SERVER_URL + "EmployeeAttEntry", LoginResponse.class, queryParams, 4);
    }

//    @Override
//    public void onSuccess(int from, Response response, Object res) {
//        if (null != res) {
//            onResponseCallBack.onSuccess(from, res, "");
//        }
//    }

    @Override
    public void onSuccess(int from,  Object o, Object res) {
        if (null != res) {
        onResponseCallBack.onSuccess(from, res, "");
        }
    }

    @Override
    public void onFailure(int from, Throwable t) {
        onResponseCallBack.onFailure("Server is not yet ready.Please try after sometime");
    }

    @Override
    public void onNetworkFailure(int from) {
        onResponseCallBack.onFailure("Network Error");
    }

}
