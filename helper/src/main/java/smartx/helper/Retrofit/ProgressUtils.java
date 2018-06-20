package smartx.helper.Retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ProgressBar;

import smartx.helper.R;



public class ProgressUtils {

    Context context;
    private ProgressDialog mProgressDialog;

    public ProgressUtils(Context context) {
        this.context = context;
    }

    public void showDialog(boolean isCancelable) {
        mProgressDialog = ProgressDialog.show(context, null, null, true, isCancelable);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.progressdialog);
        ProgressBar progressbar = (ProgressBar) mProgressDialog.findViewById(R.id.progressBar1);
        progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#2D9CDB"), android.graphics.PorterDuff.Mode.SRC_IN);
        mProgressDialog.setCancelable(isCancelable);
        Log.d("util", "show dialog");

    }

    public void dismiss_dialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();

        Log.d("util", "dismiss dialog");
    }

}
