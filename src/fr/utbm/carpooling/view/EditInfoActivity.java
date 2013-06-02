package fr.utbm.carpooling.view;


import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import fr.utbm.carpooling.R;


public class EditInfoActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_info, menu);
        return true;
    }
}
