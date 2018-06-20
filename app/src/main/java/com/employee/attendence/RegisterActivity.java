package com.employee.attendence;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.employee.attendence.appconstant.Constants;
import com.employee.attendence.base.BaseActivity;
import com.employee.attendence.interfaces.OnResponseCallBack;
import com.employee.attendence.model.CommonResponse;
import com.employee.attendence.model.RegisterImeiResponse;
import com.employee.attendence.retrofit.ApiHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.employee.attendence.utils.Utils.getUniqueIMEIId;

public class RegisterActivity extends BaseActivity implements OnResponseCallBack, Constants, EasyPermissions.PermissionCallbacks,
        EasyPermissions.RationaleCallbacks {


    EditText editTextRefCode;


    private static final int RC_READ_PHONE_STATE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editTextRefCode = findViewById(R.id.ref_code);
        readPhoneState();
        ((Button) findViewById(R.id.btn_register)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readPhoneState();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void checkImei() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("IMEI", getUniqueIMEIId(this));
        new ApiHelper(this).registerIMEI(this, queryParams);
    }

    private void registerIMEI() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("IMEI", getUniqueIMEIId(this));
        queryParams.put("RefCode", editTextRefCode.getText().toString().trim());
        new ApiHelper(this).registerUser(this, queryParams);
    }


    @Override
    public void onSuccess(int from, Object response, String message) {
        switch (from) {
            case 1:
                parseCheckImeiResponse(response);
                break;
            case 2:
                parseRegisterResponse(response);
                break;
        }
    }

    private void parseCheckImeiResponse(Object object) {
        if (null != object) {
            RegisterImeiResponse response = (RegisterImeiResponse) object;
            if (response.getFlag() == 1) {
                showLoginScreen(response.getDeviceId());
            } else if (response.getFlag() == -1) {
                registerIMEI();
            } else {
                showToast(response.getMsg());
            }
        }
    }

    private void parseRegisterResponse(Object object) {
        if (null != object) {
            CommonResponse response = (CommonResponse) object;
            if (response.getFlag() == 1) {
                showLoginScreen(0);
            } else {
                showToast(response.getMsg());
            }
        }
    }

    private void showLoginScreen(int deviceId) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("deviceId", deviceId);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    private boolean hasPhonePermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE);
    }

    @AfterPermissionGranted(RC_READ_PHONE_STATE)
    public void readPhoneState() {
        if (hasPhonePermission()) {
            // Have permission, do the thing!
            checkImei();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    "Read Phone State",
                    RC_READ_PHONE_STATE,
                    Manifest.permission.READ_PHONE_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> permissions) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
// (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {

    }

    @Override
    public void onRationaleDenied(int requestCode) {

    }
}
