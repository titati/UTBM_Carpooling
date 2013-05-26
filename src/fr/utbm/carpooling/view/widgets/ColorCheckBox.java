package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class ColorCheckBox extends CheckBox {

    private Paint mBodyPainter = new Paint();
    private Paint mFramePainter = new Paint();
    private Rect mBodyRect = new Rect();
    private Rect mFrameRect = new Rect();
    private static final int UNCHECKED_FRAME_WIDTH = 1;
    private static final int CHECKED_FRAME_WIDTH = 10;
    private static final float FRAME_COLOR_DARK_FACTOR = (float) 0.5;

    public ColorCheckBox(Context context) {
        super(context);
        init();
    }

    public ColorCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init() {
        mBodyPainter.setStyle(Paint.Style.FILL);
        mFramePainter.setStyle(Paint.Style.STROKE);
    }

    public void setColor(String htmlColor) {

        int bodyColor;
        int frameColor;

        try {
            bodyColor = Color.parseColor(htmlColor);
        } catch (IllegalArgumentException e) {
            return;
        }

        mBodyPainter.setColor(bodyColor);

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

        mFramePainter.setStrokeWidth(isChecked() ? CHECKED_FRAME_WIDTH : UNCHECKED_FRAME_WIDTH);

        canvas.drawRect(mBodyRect, mBodyPainter);
        canvas.drawRect(mFrameRect, mFramePainter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        int s = Math.min(w, h);
        int x = (w - s) / 2;
        int y = (h - s) / 2;

        mFrameRect.set(UNCHECKED_FRAME_WIDTH + x,
                UNCHECKED_FRAME_WIDTH
                        + y, s + x, s + y);
        mBodyRect = mFrameRect;

        mBodyRect.inset(CHECKED_FRAME_WIDTH, CHECKED_FRAME_WIDTH);

    }

}
