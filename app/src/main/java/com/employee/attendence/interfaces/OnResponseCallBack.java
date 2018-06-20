package com.employee.attendence.interfaces;


/**
 * Created by user on 27/11/17.
 */

public interface OnResponseCallBack {

    void onSuccess(int from, Object object, String message);

    void onFailure(String message);

}
