package vlimv.colorquad;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HP on 06.09.2017.
 */
public class AppRater extends Game{
    private final static String APP_TITLE = "ColorQuad";// App Name
    private final static String APP_PNAME = "vlimv.colorquad";// Package Name

    private final static int DAYS_UNTIL_PROMPT = 2;//Min number of days
    private final static int LAUNCHES_UNTIL_PROMPT = 5;//Min number of launches

    public static void app_launched(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false)) { return ; }

        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch +
                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(mContext, editor);
            }
        }

        editor.commit();
    }

    public static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {
        final Dialog dialog = new Dialog(mContext);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_rate_app);


        Typeface Marske = Typeface.createFromAsset(mContext.getAssets(), "fonts/Marske.ttf");
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setTypeface(Marske);

        Button b1 = (Button) dialog.findViewById(R.id.button_yes);
        b1.setTypeface(Marske);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });

        Button b2 = (Button) dialog.findViewById(R.id.button_later);
        b2.setTypeface(Marske);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button b3 = (Button) dialog.findViewById(R.id.button_no);
        b3.setTypeface(Marske);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}