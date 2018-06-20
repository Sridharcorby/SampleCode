package com.employee.attendence.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoleList {

    @SerializedName("ActionName")
    @Expose
    private String actionName;
    @SerializedName("ActionId")
    @Expose
    private Integer actionId;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }
}
