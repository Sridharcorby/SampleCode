package com.employee.attendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.employee.attendence.appconstant.Constants;
import com.employee.attendence.interfaces.OnResponseCallBack;
import com.employee.attendence.model.EmpParcel;
import com.employee.attendence.model.EmpProfile;
import com.employee.attendence.model.LoginResponse;
import com.employee.attendence.retrofit.ApiHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements OnResponseCallBack, Constants {


    EditText userNameEditText, passwordEditText;
    int deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = findViewById(R.id.editText1);
        passwordEditText = findViewById(R.id.editText2);

        deviceId = getIntent().getExtras().getInt("deviceId");

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                callLoginApi(userName, password);
            }
        });
    }

    private void callLoginApi(String username, String password) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("DeviceId", String.valueOf(deviceId));
        queryParams.put("UserName", username);
        queryParams.put("password", password);
        new ApiHelper(this).login(this, queryParams);
    }

    @Override
    public void onSuccess(int from, Object response, String message) {
        switch (from) {
            case 3:
                parseResponse(response);
                break;
        }
    }

    private void parseResponse(Object object) {
        if (null != object) {
            LoginResponse response = (LoginResponse) object;
            EmpProfile empProfile = response.empProfile;
            Log.v("LoginActivity",empProfile.getEmployeeName());

            ArrayList<EmpParcel> p = new ArrayList<>();
            EmpParcel parcel = new EmpParcel();
            parcel.setAddress(empProfile.getAddress());
            parcel.setAttLocation(empProfile.getAttLocation());
            parcel.setContactno(empProfile.getContactno());
            parcel.setDepartment(empProfile.getDepartment());
            parcel.setDesignation(empProfile.getDesignation());
            parcel.setEmail(empProfile.getEmail());
            parcel.setEmployeeId(empProfile.getEmployeeId());
            parcel.setEmployeeName(empProfile.getEmployeeName());
            parcel.setImagePath(empProfile.getImagePath());

            p.add(parcel);
//            parcel.setAddress(empProfile.getAddress());

            if (response.getFlag() == 1) {
                Intent intent = new Intent(getApplicationContext(), EmployeeListActivity.class);
                intent.putParcelableArrayListExtra("employee",p);
                startActivity(intent);
                finish();
            }
        }
    }


    @Override
    public void onFailure(String message) {
    }
}
