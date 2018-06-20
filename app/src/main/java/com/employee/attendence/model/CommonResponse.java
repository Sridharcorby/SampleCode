package com.employee.attendence.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CommonResponse {

    @SerializedName("Flag")
    @Expose
    private Integer flag;
    @SerializedName("Msg")
    @Expose
    private String msg;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
