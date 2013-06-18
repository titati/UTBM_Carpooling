package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.CheckBox;

public class ColorCheckBox extends CheckBox {

    private Paint mBodyPainter = new Paint();
    private Paint mFramePainter = new Paint();
    private Rect mBodyRect = new Rect();
    private Rect mFrameRect = new Rect();
    private static final int UNCHECKED_FRAME_WIDTH = 2;
    private static final int CHECKED_FRAME_WIDTH = 10;
    private static final float FRAME_COLOR_DARK_FACTOR = (float) 0.25;

    public ColorCheckBox(Context context) {
        super(context);
        init();
    }

    public ColorCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init() {
        // init styles of the painters
        mBodyPainter.setStyle(Paint.Style.FILL);
        mFramePainter.setStyle(Paint.Style.STROKE);
        mFramePainter.setAntiAlias(true);
    }

    public void setColor(String hex) {

        int bodyColor;
        int frameColor;

        // parsing color from hex
        try {
            bodyColor = Color.parseColor(hex);
        } catch (IllegalArgumentException e) {
            return;
        }

        mBodyPainter.setColor(bodyColor);

        // computing frame color (darker body color)
        float[] hsv = new float[3];
        Color.colorToHSV(bodyColor, hsv);

        if (hsv[2] < FRAME_COLOR_DARK_FACTOR) {
            hsv[2] = 0;
        } else {
            hsv[2] -= FRAME_COLOR_DARK_FACTOR;
        }

        frameColor = Color.HSVToColor(hsv);
        mFramePainter.setColor(frameColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // select width of the frame according to the state of the checkbox
        mFramePainter.setStrokeWidth(isChecked() ? CHECKED_FRAME_WIDTH : UNCHECKED_FRAME_WIDTH);

        // draw rectangles
        //canvas.drawRect(mBodyRect, mBodyPainter);
        canvas.drawCircle(mBodyRect.exactCenterX(), mBodyRect.exactCenterY(), mBodyRect.width()/2, mBodyPainter);
        canvas.drawCircle(mFrameRect.exactCenterX(), mFrameRect.exactCenterY(), mFrameRect.width()/2, mFramePainter);
        //canvas.drawRect(mFrameRect, mFramePainter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        // paddings
        int l = getPaddingLeft(),
            r = getPaddingRight(),
            t = getPaddingTop(),
            b = getPaddingBottom();

        // real dimensions of the container
        int rw = w - (l+r);
        int rh = h - (t+b);

        // computing side of the square
        int s = Math.min(rw, rh);

        // computing pos of the square (centered by default)
        int x = (rw - s) / 2;
        int y = (rh - s) / 2;

        if ((getGravity() & Gravity.LEFT) != 0) {
            x = l;
        } else if ((getGravity() & Gravity.RIGHT) != 0) {
            x = w - r - s;
        }

        if ((getGravity() & Gravity.TOP) != 0) {
            y = t;
        } else if ((getGravity() & Gravity.BOTTOM) != 0) {
            y = h - b - s;
        }

        // computing frame & body rects
        mFrameRect.set(UNCHECKED_FRAME_WIDTH + x,
                UNCHECKED_FRAME_WIDTH
                        + y, s + x, s + y);
        mBodyRect = mFrameRect;

        // reducing body rect if checkbox clickable
        if (isClickable())
            mBodyRect.inset(CHECKED_FRAME_WIDTH, CHECKED_FRAME_WIDTH);

    }

}
