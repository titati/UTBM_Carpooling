package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import fr.utbm.carpooling.model.CheckpointShort;

import java.util.ArrayList;

public class TripPathRestrictedView extends TripPathView {
    
    private boolean mLimit1IsBound;
    private boolean mLimit2IsBound;
    
    public TripPathRestrictedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPath(canvas);

        if (mSurroundBounds) {
            if (mLimit1IsBound)
                drawBound1(canvas);

            if (mLimit2IsBound)
                drawBound2(canvas);
        }
    }

    public void setCheckpoints(ArrayList<? extends CheckpointShort> arrayList, boolean secondIsBound) {
    	mLimit1IsBound = (arrayList.get(0).getNumCheckpoint() == 0);
    	mLimit1IsBound = secondIsBound;
    	
    	Log.e("Checkpoint num", "" + arrayList.get(0).getNumCheckpoint());
    	
        setCheckpoints(arrayList);
    }
}
