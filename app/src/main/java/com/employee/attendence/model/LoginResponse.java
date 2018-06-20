package com.employee.attendence.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends CommonResponse {


    @SerializedName("EmpProfile")
    @Expose
    public EmpProfile empProfile;

    public EmpProfile getEmpProfile() {
        return empProfile;
    }

    public void setEmpProfile(EmpProfile empProfile) {
        this.empProfile = empProfile;
    }
}
