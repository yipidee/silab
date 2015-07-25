package com.example.android.silab;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Simple object to handle to handle generic dialog displays
 * Prints message to screen, with an "ok" button to dismiss.
 *
 * Implemented as a Singelton. (This app has only one activity,
 * want to use that's activity's context)
 *
 * Rev.  Date        Author        Comment
 * 0     2015.7.23   A. Connolly   Initial implementation
 *
 */

public class DialogDisplayer {

    private Context mContext;

    public DialogDisplayer(Context c){
        mContext = c;
    }

    public void displayDialog(int errorMessage) {
        // Displays a error dialog, with an ok button to close
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(errorMessage);
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing, just let the dialog close and get garbage collected
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
