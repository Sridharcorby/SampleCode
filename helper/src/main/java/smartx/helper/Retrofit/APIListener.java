package smartx.helper.Retrofit;

import retrofit2.Response;


public interface APIListener {
    void onSuccess(int from, Object object, Object res);

    void onFailure(int from, Throwable t);

    void onNetworkFailure(int from);
}
