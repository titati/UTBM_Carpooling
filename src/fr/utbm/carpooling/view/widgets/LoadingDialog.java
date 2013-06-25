package fr.utbm.carpooling.view.widgets;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.*;
import android.widget.TextView;
import fr.utbm.carpooling.R;


public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);

        initDialog(context);
    }
    
    protected void initDialog(Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    	LayoutInflater li = LayoutInflater.from(context);
        View content = li.inflate(R.layout.dialog_progress_loading, null);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setContentView(content);
        setCancelable(true);
    }
}
