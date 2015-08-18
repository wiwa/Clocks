package com.winwang.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by wiwa on 8/17/15.
 */
public class DonutsVisualization extends View{

    final String TAG = "Donuts Visualization";
    final float INNER_RADIUS = 120;
    final float OUTER_RADIUS = 260;

    int width;
    int height;
    int centerx;
    int centery;
    int radius;

    Paint paintText;
    Paint paintArcBorder;
    Paint paintArcFill;


    public DonutsVisualization(Context context){
        super(context);
        init();
    }

    public DonutsVisualization(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public DonutsVisualization(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        paintText = new Paint();
        paintText.setTextSize(15);
        paintText.setColor(Color.WHITE);

        // used for arc filling
        paintArcFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintArcFill.setStyle(Paint.Style.FILL);
        paintArcFill.setColor(Color.YELLOW);

        // used for arc border
        paintArcBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintArcBorder.setStyle(Paint.Style.STROKE);
        paintArcBorder.setStrokeWidth(10);
        paintArcBorder.setColor(Color.GREEN);
        Log.i(TAG, "Donuts init()");

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

/*
        if (width == 0) {
            width = getWidth();
            height = getHeight();
        } else {
            // do not get size again
            setMeasuredDimension(getMeasuredWidth(), height);
            return;
        }
*/
        width = getMeasuredWidth();
        height = getMeasuredHeight();


        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        centerx = width / 2;
        centery = height / 2;

        radius = Math.min(width, height);

        //testing
        //canvas.drawCircle(centerx, centery, 100, paintArcBorder);
        DonutsArc myArc = new DonutsArc(centerx, centery, INNER_RADIUS, OUTER_RADIUS, 30, 180, Color
                .RED,
                Color.BLUE);
        canvas.drawArc(myArc.getInnerRect(), myArc.getStartAngle(), myArc.getSweepAngle(), false,
                paintArcBorder);
        canvas.drawArc(myArc.getOuterRect(), myArc.getStartAngle(), myArc.getSweepAngle(), false,
                paintArcBorder);
        /*
        RectF myRect = new RectF(150, 150, 500, 500);
        canvas.drawArc(100, 100, 400, 400, 30, 140, true,
                paintArcBorder);
        canvas.drawArc(myRect, 0, 90, false,
                paintArcBorder);
        */
        //canvas.drawArc();
    }
}
