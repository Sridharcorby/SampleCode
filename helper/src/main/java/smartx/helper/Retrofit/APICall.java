package smartx.helper.Retrofit;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class APICall<T> {


    private Call call;
    private APIListener responseListener;
    private Activity activity;
    private Gson gson = new Gson();
    private ApiInterface apiService;
    private Context context;

    public static boolean ShowNetworkError = true;
    public static String NETWORK_CONNECTION_ERROR = "Network connection failure.";
    // --Commented out by Inspection (05/03/18, 12:36 PM):public static String FAIL_ERROR = "Something went wrong! Please try again later.";
    public static String URL_EMPTY = "Url is empty";
    public static String LISTENER_EMPTY = "APIListener is null";
    public static String HEADER_EMPTY = "Common header is empty";
    public static String GET_WITH_PARAMS = "Get with parms is invalid,so param values are neglected";


    // Standard Object

    public APICall(Activity activity, APIListener listener) {
        init(activity, ApiClient.getCommonHeaders(), null, listener);
    }

    public void init(Activity activity, Map<String, String> defaultHeaders, Map<String, String> customHeaders) {
        apiService = ApiClient.getClient(defaultHeaders, customHeaders);

        if (activity != null) {
            this.activity = activity;
            if (activity != null)
                this.context = activity;
            try {
                responseListener = (APIListener) activity;
            } catch (Exception e) {
                responseListener = null;
            }
        } else
            responseListener = null;


    }

    public void init(Activity activity, Map<String, String> defaultHeaders, Map<String, String> customHeaders, APIListener listener) {
        apiService = ApiClient.getClient(defaultHeaders, customHeaders);
        this.activity = activity;
        responseListener = listener;

        if (activity != null)
            this.context = activity;
    }

    public void APIRequest(Method type, String url, final Class<T> responseModel, Object body,
                           final int from) {
        APIRequest(type, url, responseModel, body, null, from, false, "", responseListener);
    }

    public void APIRequest(Method type, String url, final Class<T> responseModel, Map<String, String> params,
                           final int from) {
        APIRequest(type, url, responseModel, null, params, from, false, "", responseListener);
    }

    public void APIRequest(Method type, String url, final Class<T> responseModel, Object body, Map<String, String> params,
                           final int from, boolean is_PDshow, String message,
                           final APIListener responseListener) {
        call = null;

        String TAG = "APICall";

        if (TextUtils.isEmpty(url))
            Log.d(TAG, URL_EMPTY);
        else if (responseListener == null)
            Log.d(TAG, LISTENER_EMPTY);
        else if (NetworkCheck.isNetworkAvailable(context)) {

            if (is_PDshow && activity != null) {
                if (TextUtils.isEmpty(message))
                    ProgressDialogLoader.progressDialogCreation(activity, "Loading");
                else
                    ProgressDialogLoader.progressDialogCreation(activity, message);
            }

            if (params != null && !params.isEmpty() && body != null) {
                if (type == Method.GET) {
                    Log.d(TAG, GET_WITH_PARAMS);
                    call = apiService.getRequest(url, params);
                } else if (type == Method.POST)
                    call = apiService.postRequest(url, body);
                else if (type == Method.PUT)
                    call = apiService.putRequest(url, params, body);
                else if (type == Method.DELETE)
                    call = apiService.deleteRequest(url, params, body);
            } else if (params != null && !params.isEmpty()) {
                if (type == Method.GET)
                    call = apiService.getRequest(url, params);
                else if (type == Method.POST)
                    call = apiService.postRequest(url, params);
                else if (type == Method.PUT)
                    call = apiService.putRequest(url, params);
                else if (type == Method.DELETE)
                    call = apiService.deleteRequest(url, params);
            } else if (body != null) {
                if (type == Method.GET) {
                    Log.d(TAG, GET_WITH_PARAMS);
                    call = apiService.getRequest(url);
                } else if (type == Method.POST)
                    call = apiService.postRequest(url, body);
                else if (type == Method.PUT)
                    call = apiService.putRequest(url, body);
                else if (type == Method.DELETE)
                    call = apiService.deleteRequest(url, body);
            } else {
                if (type == Method.GET) {
                    call = apiService.getRequest(url);
                } else if (type == Method.POST)
                    call = apiService.postRequest(url);
                else if (type == Method.PUT)
                    call = apiService.putRequest(url);
                else if (type == Method.DELETE)
                    call = apiService.deleteRequest(url);
            }

            assert call != null;
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        //Log.d("Call URL", "" + call.request().url().toString());
                        //Log.d("API Call", "Success");
                        String json = response.body().string();
                        //Log.d("API Call", "onSuccess: " + json);
                        T res = gson.fromJson(json, responseModel);
                        Response<ResponseBody> resp = response;
                        String json1 = resp.body().string();
                        //Log.d("API Call", "onSuccess: " + json1);
                        responseListener.onSuccess(from, resp, res);
                        ProgressDialogLoader.progressDialogDismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ProgressDialogLoader.progressDialogDismiss();
                        responseListener.onFailure(from, new Throwable(e.getMessage()));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    // Log error here since request failed
                    //Log.e("API Call","Failure"+t.toString());
                    responseListener.onFailure(from, t);
                    ProgressDialogLoader.progressDialogDismiss();
                }
            });

        } else {
            responseListener.onNetworkFailure(from);
            Log.d(TAG, NETWORK_CONNECTION_ERROR);
        }
    }

    public enum Method {
        GET, POST, PUT, DELETE;
    }
}
