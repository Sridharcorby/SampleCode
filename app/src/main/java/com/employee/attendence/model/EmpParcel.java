package com.employee.attendence.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EmpParcel implements Parcelable {

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

    public EmpParcel(){

    }


    public EmpParcel(Parcel in) {
        employeeId = in.readByte() == 0x00 ? null : in.readInt();
        employeeName = in.readString();
        designation = in.readString();
        department = in.readString();
        contactno = (Object) in.readValue(Object.class.getClassLoader());
        email = (Object) in.readValue(Object.class.getClassLoader());
        imagePath = (Object) in.readValue(Object.class.getClassLoader());
        address = in.readString();
        attLocation = in.readString();
        if (in.readByte() == 0x01) {
            roleList = new ArrayList<RoleList>();
            in.readList(roleList, RoleList.class.getClassLoader());
        } else {
            roleList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (employeeId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(employeeId);
        }
        dest.writeString(employeeName);
        dest.writeString(designation);
        dest.writeString(department);
        dest.writeValue(contactno);
        dest.writeValue(email);
        dest.writeValue(imagePath);
        dest.writeString(address);
        dest.writeString(attLocation);
        if (roleList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(roleList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EmpParcel> CREATOR = new Parcelable.Creator<EmpParcel>() {
        @Override
        public EmpParcel createFromParcel(Parcel in) {
            return new EmpParcel(in);
        }

        @Override
        public EmpParcel[] newArray(int size) {
            return new EmpParcel[size];
        }
    };
}