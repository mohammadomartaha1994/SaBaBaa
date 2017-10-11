package com.tampasst.tampass.tampass;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by tampass on 3/11/2017.
 */

public class ProgressBuilder {
    private Context context;
    private Dialog dialog;

    public ProgressBuilder(Context context) {
        this.context = context;

    }

    public void showProgressDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_progress_background));
        dialog.setContentView(R.layout.dialog_spinner);
        dialog.setCancelable(false);
        dialog.show();


    }


    public TextView getTextView()
    {
        return (TextView)dialog.findViewById(R.id.textView1);
    }

    public Dialog getDialog()
    {
        return dialog;
    }

    public void dismiss() {
        dialog.dismiss();
    }

}
