package com.example.gravitysensorbackground;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class XImageView extends androidx.appcompat.widget.AppCompatImageView {
    private double scaleX;
    private double scaleY;
    private float lenX;
    private float lenY;

    public XImageView(Context context) {
        super(context);
        init();
    }

    public XImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setScaleType(ScaleType.CENTER_CROP);
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        super.setScaleType(ScaleType.CENTER_CROP);
    }


    public void setGyroscopeManager(GyroscopeManager gyroscopeManager) {
        if (gyroscopeManager != null) {
            gyroscopeManager.addView(this);
        }
    }


    public void update(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

        if (getDrawable() != null) {
            int drawableWidth = getDrawable().getIntrinsicWidth();
            int drawableHeight = getDrawable().getIntrinsicHeight();

            lenX = Math.abs((drawableWidth - width) * 0.5f);
            lenY = Math.abs((drawableHeight - height) * 0.5f);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null || isInEditMode()) {
            super.onDraw(canvas);
            return;
        }

        float currentOffsetX = (float) (lenX * scaleX);
        float currentOffsetY = (float) (lenY * scaleY);
        canvas.save();
        canvas.translate(currentOffsetX, 0);
        canvas.translate(0, currentOffsetY);
        super.onDraw(canvas);
        canvas.restore();
    }
}
