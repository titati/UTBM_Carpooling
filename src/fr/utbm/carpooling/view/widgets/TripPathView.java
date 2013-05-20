package fr.utbm.carpooling.view.widgets;


//import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Checkpoint;

import java.util.List;

public class TripPathView extends View {

    private Paint mBodyPainter = new Paint();
    private Paint mFramePainter = new Paint();
    private final int COLOR = getResources().getColor(android.R.color.holo_blue_light);
    private final int POINT_RADIUS = 12;
    private final int CIRCLE_RADIUS = 18;
    private final int STROKE_WIDTH = 3;

    private int mW = 0;
    private int mH = 0;
    private int mX = 0;
    private int mY1 = 0;
    private int mY2 = 0;
    private int mN = 0;

    protected boolean mFull = false;
    protected boolean mSurroundBounds = true;
    protected List<Checkpoint> mCheckpoints;

    public TripPathView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBodyPainter.setStyle(Paint.Style.FILL);
        mBodyPainter.setAntiAlias(true);
        mBodyPainter.setColor(COLOR);
        mFramePainter.setStyle(Paint.Style.STROKE);
        mFramePainter.setStrokeWidth(STROKE_WIDTH);
        mFramePainter.setAntiAlias(true);
        mFramePainter.setColor(COLOR);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TripPathView,
                0, 0);

        try {
            mFull = a.getBoolean(R.styleable.TripPathView_fullPath, false);
            mSurroundBounds = a.getBoolean(R.styleable.TripPathView_surroundBounds, true);
        } finally {
            a.recycle();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawPath(canvas);

        if (mSurroundBounds) {

            drawBound1(canvas);
            drawBound2(canvas);
        }
    }

    protected void drawPath(Canvas canvas) {

        // Draw line
        canvas.drawLine(mX, mY1, mX, mY2, mFramePainter);

        // Draw bounds
        canvas.drawCircle(mX, mY1, POINT_RADIUS, mBodyPainter);
        canvas.drawCircle(mX, mY2, POINT_RADIUS, mBodyPainter);

        float y = 0;

        if (mFull) {
            // Draw checkpoints
            for (int n = 1; n < mN - 1; ++n) {

                if (mN > 0) {
                    y = (1 + 2 * n) * mH / (2 * mN);
                }
                canvas.drawCircle(mX, y, POINT_RADIUS, mBodyPainter);
            }
        } else {
            // Draw checkpoint
            if (mCheckpoints.size() > 2)
                canvas.drawCircle(mX, mH / 2, POINT_RADIUS, mBodyPainter);
        }
    }

    protected void drawBound1(Canvas canvas) {
        canvas.drawCircle(mX, mY1, CIRCLE_RADIUS, mFramePainter);
    }

    protected void drawBound2(Canvas canvas) {
        canvas.drawCircle(mX, mY2, CIRCLE_RADIUS, mFramePainter);
    }

    private void computeCoords() {

        if (mN > 0) {
            mX = mW / 2;
            mY1 = mH / (2 * mN);
            mY2 = (1 + 2 * (mN - 1)) * mH / (2 * mN);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        mW = w;
        mH = h;
        computeCoords();
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {

        mCheckpoints = checkpoints;
        mN = (mFull ? mCheckpoints.size() : 2);
        computeCoords();
    }

}
