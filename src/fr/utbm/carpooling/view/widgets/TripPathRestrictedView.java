package fr.utbm.carpooling.view.widgets;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import fr.utbm.carpooling.model.Checkpoint;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class TripPathRestrictedView extends TripPathView {

    private Checkpoint mLimit1 = null;
    private Checkpoint mLimit2 = null;

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

    @Override
    public void setCheckpoints(List<Checkpoint> checkpoints) {

        LinkedList<Checkpoint> list = (LinkedList<Checkpoint>) checkpoints;

        if (mLimit1 != null && mLimit2 != null) {

            mLimit1IsBound = (list.getFirst() == mLimit1);
            mLimit2IsBound = (list.getLast() == mLimit2);

            try {

                while (list.getFirst() != mLimit1)
                    list.removeFirst();


                while (list.getLast() != mLimit2)
                    list.removeLast();

            } catch (NoSuchElementException e) {}
        }

        super.setCheckpoints(list);
    }

    public void setLimits(Checkpoint limit1, Checkpoint limit2) {

        mLimit1 = limit1;
        mLimit2 = limit2;
    }

}
