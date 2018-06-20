package smartx.helper.Retrofit;

import android.app.Activity;



public class ProgressDialogLoader {

    public static ProgressUtils progressUtils;

    // --Commented out by Inspection (05/03/18, 12:36 PM):private Context context;
    // --Commented out by Inspection (05/03/18, 12:36 PM):private Activity activity;

// --Commented out by Inspection START (05/03/18, 12:36 PM):
//    public ProgressDialogLoader(Context context) {
//        this.context = context;
//        this.activity = (Activity) context;
//    }
// --Commented out by Inspection STOP (05/03/18, 12:36 PM)

    public static void progressDialogCreation(Activity activity, String title) {
        try {
            if (progressUtils == null) {
                progressUtils = new ProgressUtils(activity);
                progressUtils.showDialog(false);
            }
        } catch (Exception e) {
        }
    }

    public static void progressDialogDismiss() {
        if (progressUtils != null) {
            progressUtils.dismiss_dialog();
        }
        progressUtils = null;
    }
}
