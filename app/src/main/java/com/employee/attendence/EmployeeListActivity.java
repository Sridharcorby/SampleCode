package com.employee.attendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.employee.attendence.model.EmpParcel;
import com.employee.attendence.model.EmpProfile;
import com.employee.attendence.model.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EmployeeListActivity extends AppCompatActivity {

    ArrayList<EmpParcel> empParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        try {
            empParcel = getIntent().getParcelableArrayListExtra("employee");

            Log.v("EmployeeListActivity",empParcel.get(0).getEmployeeName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
