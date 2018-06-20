package smartx.helper.Retrofit;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by User on 26/06/16.
 */
public interface ApiInterface<T> {

    // GET request

    @GET
    Call<ResponseBody> getRequest(@Url String url);

    @GET
    Call<ResponseBody> getRequest(@Url String url, @QueryMap Map<String, String> params);


    // POST request


    @POST
    Call<ResponseBody> postRequest(@Url String url);

    @POST
    Call<ResponseBody> postRequest(@Url String url, @Body Object post);

    @POST
    Call<ResponseBody> postRequest(@Url String url, @QueryMap Map<String, String> params);

// --Commented out by Inspection START (05/03/18, 12:36 PM):
//    @POST
//    Call<ResponseBody> postRequest(@Url String url, @QueryMap Map<String, String> params, @Body Object post);
// --Commented out by Inspection STOP (05/03/18, 12:36 PM)


    // PUT request


    @PUT
    Call<ResponseBody> putRequest(@Url String url);

    @PUT
    Call<ResponseBody> putRequest(@Url String url, @Body Object post);

    @PUT
    Call<ResponseBody> putRequest(@Url String url, @QueryMap Map<String, String> params);

    @PUT
    Call<ResponseBody> putRequest(@Url String url, @QueryMap Map<String, String> params, @Body Object post);


    // DELETE request


    @DELETE
    Call<ResponseBody> deleteRequest(@Url String url);

    @HTTP(method = "DELETE", hasBody = true)
    Call<ResponseBody> deleteRequest(@Url String url, @Body Object post);

    @DELETE
    Call<ResponseBody> deleteRequest(@Url String url, @QueryMap Map<String, String> params);

    @HTTP(method = "DELETE", hasBody = true)
    Call<ResponseBody> deleteRequest(@Url String url, @QueryMap Map<String, String> params, @Body Object post);


}
