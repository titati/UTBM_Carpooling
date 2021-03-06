package fr.utbm.carpooling.view.widgets;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import fr.utbm.carpooling.model.Color;

import java.util.ArrayList;
import java.util.Collections;


public class ColorGridView extends LinearLayout {

    private Context mContext;
    private ArrayList<Color> mColors;
    private GridView mGridView;
    private TextView mTitle;
    private int mCurPos = -1;


    public ColorGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
    }


    private void init() {

        if (mColors != null) {

            mGridView = new GridView(mContext);
            addView(mGridView);
            setClickable(true);

            mGridView.setColumnWidth(65);
            mGridView.setNumColumns(GridView.AUTO_FIT);
            mGridView.setVerticalSpacing(5);
            mGridView.setHorizontalSpacing(5);
            mGridView.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);

            mGridView.setGravity(Gravity.CENTER);
            mGridView.setClickable(true);
            mGridView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            mGridView.setFocusable(true);
            mGridView.setFocusableInTouchMode(true);

            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mTitle.getError() != null)
                        mTitle.setError(null);

                    mCurPos = position;
                }
            });

            ColorAdapter adapter = new ColorAdapter(mContext, mGridView.getId());

            mGridView.setAdapter(adapter);

            LinearLayout parent = (LinearLayout) getParent();
            mTitle = (TextView) parent.getChildAt(parent.indexOfChild(this) - 1);
            mTitle.setFocusable(true);
            mTitle.setFocusableInTouchMode(true);

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.AT_MOST, 200);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void setColors(ArrayList<Color> colors) {

        Collections.sort(colors);
        mColors = colors;
        init();
    }


    public void setError(CharSequence errorMsg) {

        mTitle.requestFocus();
        mTitle.setError(errorMsg);
    }


    public Color getSelectedColor() {

        if (mCurPos == -1)
            return null;
        return mColors.get(mCurPos);
    }


    class ColorAdapter extends ArrayAdapter<Color> {

        public ColorAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        @Override
        public int getCount() {
            return mColors.size();
        }

        @Override
        public Color getItem(int position) {
            return mColors.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mColors.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ColorCheckBox ccb = (ColorCheckBox) convertView;

            if (ccb == null) {
                ccb = new ColorCheckBox(mContext);
                ccb.setPadding(5, 5, 5, 5);
                ccb.setColor(mColors.get(position).getHex());
                ccb.setClickable(false);
                ccb.setFocusable(false);
                ccb.setFocusableInTouchMode(false);
            }

            return ccb;
        }
    }

}
