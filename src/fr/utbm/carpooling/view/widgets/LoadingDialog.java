package fr.utbm.carpooling.view.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import fr.utbm.carpooling.R;


public class LoadingDialog extends AlertDialog {

    public LoadingDialog(Context context) {
        super(context);

        initDialog(context);
    }
    
    protected void initDialog(Context context) {
    	LayoutInflater li = LayoutInflater.from(context);
        View content = li.inflate(R.layout.dialog_progress_loading, null);
        
        setView(content);
        setCancelable(true);
    }
}
