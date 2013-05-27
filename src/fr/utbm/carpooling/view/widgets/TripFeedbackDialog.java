package fr.utbm.carpooling.view.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import fr.utbm.carpooling.R;


public class TripFeedbackDialog extends AlertDialog {

    public TripFeedbackDialog(Context context) {
        super(context);

        LayoutInflater li = LayoutInflater.from(context);
        View content = li.inflate(R.layout.dialog_trip_feedback, null);

        setCancelable(true);
        setTitle(R.string.trip_details_feedback_dialog_title);
        setButton(AlertDialog.BUTTON_NEGATIVE, context.getText(R.string.action_cancel), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        setButton(AlertDialog.BUTTON_POSITIVE, context.getText(R.string.action_ok), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        setView(content);
    }
}
