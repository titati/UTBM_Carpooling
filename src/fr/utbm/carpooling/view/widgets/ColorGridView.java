package fr.utbm.carpooling.view.widgets;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import fr.utbm.carpooling.model.Color;

import java.util.ArrayList;

public class ColorGridView extends LinearLayout {

    private int mCheckedPos = -1;
    private Context mContext;
    private ArrayList<Color> mColors;
    private ArrayList<ColorCheckBox> mCheckboxes;
    private GridView mGridView;


    public ColorGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
    }


    private void init() {

        if (mColors != null) {

            mCheckboxes = new ArrayList<ColorCheckBox>();

            mGridView = new GridView(mContext);
            addView(mGridView);

            setClickable(true);
            mGridView.setColumnWidth(65);
            mGridView.setNumColumns(GridView.AUTO_FIT);
            mGridView.setVerticalSpacing(5);
            mGridView.setHorizontalSpacing(5);
            mGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            mGridView.setGravity(Gravity.CENTER);
            mGridView.setClickable(true);

            final ColorAdapter adapter = new ColorAdapter();

            mGridView.setAdapter(adapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    android.util.Log.e("CLICKED", "CLICKED");
                    if (mCheckedPos >= 0) {
                        mCheckboxes.get(mCheckedPos).setChecked(false);
                    }

                    mCheckboxes.get(position).setChecked(true);
                    mCheckedPos = position;

                    adapter.notifyDataSetChanged();
                }
            });
        }

    }


    public void setColors(ArrayList<Color> colors) {
        mColors = colors;
        init();
    }


    public Color getSelectedColor() {

        return mColors.get(mCheckedPos);
    }

    public void setSelectedColor(Color color) {
        int pos = mColors.indexOf(color);
        ColorCheckBox checkbox = mCheckboxes.get(pos);

        checkbox.setChecked(true);
        mGridView.performItemClick(checkbox, pos, checkbox.getId());
    }



    class ColorAdapter extends BaseAdapter {

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public int getCount() {
            return mColors.size();
        }

        @Override
        public Object getItem(int position) {
            return mColors.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mColors.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = new ColorCheckBox(mContext);
            }

            ColorCheckBox ccb = (ColorCheckBox) convertView;

            mCheckboxes.add(position, ccb);
            ccb.setColor(mColors.get(position).getHex());

            ccb.setClickable(false);
            ccb.setFocusable(false);
            ccb.setFocusableInTouchMode(false);

            return ccb;

        }
    }

}
