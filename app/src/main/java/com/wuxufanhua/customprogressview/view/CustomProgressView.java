package com.wuxufanhua.customprogressview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.wuxufanhua.customprogressview.R;

/**
 * Created by wuxufanhua on 2017/10/9.
 */

public class CustomProgressView extends ImageView {
    private final RectF mProgressRect = new RectF();

    private Paint mProgressPaint;
    private boolean mShowProgress;
    private double mMaxProgress;
    private int mLayerColor;
    private int mProgress;


    public CustomProgressView(Context context) {
        this(context, null);
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressView);
            mLayerColor = array.getColor(R.styleable.CustomProgressView_cpv_layer_color, Color.parseColor("#70000000"));
            mMaxProgress = array.getInteger(R.styleable.CustomProgressView_cpv_max, 100) * 1.0;
            mProgress = array.getInteger(R.styleable.CustomProgressView_cpv_progress, 0);
            mShowProgress = array.getBoolean(R.styleable.CustomProgressView_cpv_show_progress, false);
            array.recycle();
        }
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        mProgressRect.set(-(w / 2), -(h / 2), w + w / 2, h + h / 2);
    }

    /**
     * 初始化画笔
     */
    private void init() {
        mProgressPaint = new Paint(1);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setColor(mLayerColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShowProgress) {
            float startAngle = (float) (360.0 * (mProgress / mMaxProgress)) - 90;
            float sweepAngle = 270 - startAngle;
            canvas.drawArc(mProgressRect, startAngle, sweepAngle, true, mProgressPaint);
        }
    }

    public void setShowProgress(boolean mShowProgress) {
        this.mShowProgress = mShowProgress;
        invalidate();
    }

    public void setProgress(int mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }
}
