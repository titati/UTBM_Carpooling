package fr.utbm.carpooling.view;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_info_menuitem_cancel:
                this.setResult(0);
                finish();
                return true;

            case R.id.edit_info_menuitem_save:
                //TODO: updateUser
                this.setResult(1);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
