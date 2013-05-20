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
	private int mBodyColor = 0;
	private int mFrameColor = 0;
	private final int UNCHECKED_FRAME_WIDTH = 1;
	private final int CHECKED_FRAME_WIDTH = 10;
	private final float FRAME_COLOR_DARK_FACTOR = (float) 0.5;

	public ColorCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);

		mBodyPainter.setStyle(Paint.Style.FILL);
		mFramePainter.setStyle(Paint.Style.STROKE);
	}

	public void setColor(String htmlColor) throws IllegalArgumentException {

		if (!htmlColor.startsWith("#")) {
			htmlColor = "#".concat(htmlColor);
		}

		try {
			mBodyColor = Color.parseColor(htmlColor);
		} catch (IllegalArgumentException e) {
			throw e;
		}

		mBodyPainter.setColor(mBodyColor);

		float[]	hsv = new float[3];
		Color.colorToHSV(mBodyColor, hsv);

		if (hsv[2] < FRAME_COLOR_DARK_FACTOR) {
			hsv[2] = 0;
		} else {
			hsv[2] -= FRAME_COLOR_DARK_FACTOR;
		}

		mFrameColor = Color.HSVToColor(hsv);
		mFramePainter.setColor(mFrameColor);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		mFramePainter.setStrokeWidth(isChecked() ? 20 : 1);

		canvas.drawRect(mBodyRect, mBodyPainter);
		canvas.drawRect(mFrameRect, mFramePainter);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		int s = Math.min(w, h);
		mFrameRect.set(UNCHECKED_FRAME_WIDTH + (w - s) / 2,
				UNCHECKED_FRAME_WIDTH
				+ (h - s) / 2, s, s);
		mBodyRect = mFrameRect;

		int p = (isClickable() ? CHECKED_FRAME_WIDTH : UNCHECKED_FRAME_WIDTH);
		mBodyRect.inset(p, p);
	}

}
