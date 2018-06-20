package smartx.helper.Retrofit;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkCheck {

//			********************************************************************************************
//	 										IF NETWORK IS ACTIVE OR NOT
//			********************************************************************************************

    public static boolean isNetworkAvailable(Context context) {
        boolean networkAvailable = false;


        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            if (connectivityManager != null && activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                networkAvailable = true;
            } else {
                networkAvailable = false;
                if (APICall.ShowNetworkError)
                    Toast.makeText(context, APICall.NETWORK_CONNECTION_ERROR, Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return networkAvailable;
    }
}
