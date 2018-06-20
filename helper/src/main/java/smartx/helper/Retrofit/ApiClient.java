package smartx.helper.Retrofit;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import smartx.helper.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static String BASE_URL = "";

    private static Map<String, String> default_headers = new HashMap<>();


    public static ApiInterface getClient(final Map<String, String> defaultHeaders, final Map<String, String> customHeaders) {
        setCommonHeaders(defaultHeaders);
        return getClient(true, customHeaders);
    }

    // Add custom headers with/wo default headers
    public static ApiInterface getClient(boolean with_header, final Map<String, String> customHeaders) {

        if (TextUtils.isEmpty(BASE_URL))
            BASE_URL = "";

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new HttpLoggingInterceptor());

        okHttpBuilder.connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = okHttpBuilder
                .build();

        Retrofit retrofit;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create());

        String TAG = "APIClient";
        if (with_header && default_headers != null && !default_headers.isEmpty()) {
            Map<String, String> combined_headers = new HashMap<>();
            combined_headers.putAll(default_headers);
            if (customHeaders != null)
                combined_headers.putAll(customHeaders);
            builder.client(get_HTTPClient(combined_headers));
        } else if (customHeaders != null && !customHeaders.isEmpty())
            builder.client(get_HTTPClient(customHeaders));
        else
            Log.d(TAG, APICall.HEADER_EMPTY);

        retrofit = builder.build();

        return retrofit.create(ApiInterface.class);
    }


    // Get HTTPCLient with headers
    private static OkHttpClient get_HTTPClient(final Map<String, String> headers) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers

                Request.Builder requestBuilder = original.newBuilder(); // <-- this is the important line

                for (Map.Entry<String, String> pairs : headers.entrySet()) {
                    if (pairs.getValue() != null) {
                        requestBuilder.header(pairs.getKey(), pairs.getValue());
                    }
                }

                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();

                return chain.proceed(request);

            }
        });

        return httpClient.build();

    }

    // Set Common Headers
    public static void setCommonHeaders(final Map<String, String> commonHeaders) {
        if (commonHeaders != null && !commonHeaders.isEmpty())
            default_headers = commonHeaders;

    }

    // Get common headers
    public static Map<String, String> getCommonHeaders() {
        return default_headers;
    }

    // Set Base URL
    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    // Set Network Error Message
    public static void setNetworkErrorMessage(String errorMessage) {
        APICall.NETWORK_CONNECTION_ERROR = errorMessage;
    }

    // Set visibility of Network Error Message

    public static void showNetworkErrorMessage(boolean isShow) {
        APICall.ShowNetworkError = isShow;
    }
}
