package com.employee.attendence.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmpProfile {

    @SerializedName("EmployeeId")
    @Expose
    private Integer employeeId;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("Department")
    @Expose
    private String department;
    @SerializedName("Contactno")
    @Expose
    private Object contactno;
    @SerializedName("Email")
    @Expose
    private Object email;
    @SerializedName("ImagePath")
    @Expose
    private Object imagePath;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("AttLocation")
    @Expose
    private String attLocation;
    @SerializedName("RoleList")
    @Expose
    private List<RoleList> roleList = null;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Object getContactno() {
        return contactno;
    }

    public void setContactno(Object contactno) {
        this.contactno = contactno;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAttLocation() {
        return attLocation;
    }

    public void setAttLocation(String attLocation) {
        this.attLocation = attLocation;
    }

    public List<RoleList> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleList> roleList) {
        this.roleList = roleList;
    }

}
