package com.pgmail.martsulg.bachelordegreeproject.extras;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


import com.pgmail.martsulg.bachelordegreeproject.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by lenovo on 02.08.2017.
 */

@TargetApi(Build.VERSION_CODES.N)
public class TimerView extends View {

    Date date = new Date();
    Calendar calendar = new GregorianCalendar();

    public static int seconds;
    int minutes;
    int hours;
    float radius;
    float dotRadius;
    private Thread thread;

    private Paint myPan = new Paint();

    public TimerView(Context context) {
        super(context);

        initialize();
    }

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();

    }

    public TimerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TimerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        myPan.setAntiAlias(true);
        myPan.setStyle(Paint.Style.STROKE);

        Resources r = getResources();
        float strokeWidthInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, r.getDisplayMetrics());
        myPan.setStrokeWidth(strokeWidthInPx);


        seconds = 0;
//        seconds = calendar.getTime().getSeconds();
        minutes = calendar.getTime().getMinutes();
        hours = calendar.getTime().getHours();
        radius = getWidth() / 3;
        dotRadius = 6;

    }

        @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.println(Log.INFO,"AAAAA",calendar.getTime().toString());

        myPan.setTextSize(24);
        myPan.setColor(ContextCompat.getColor(getContext(), R.color.red));
        myPan.setStyle(Paint.Style.FILL);
        canvas.drawText(calendar.getTime().toString(),-210 + getWidth()/2,5*getHeight()/6,myPan);


        myPan.setColor(ContextCompat.getColor(getContext(), R.color.white));
        for(int i=0;i<60;i++) {
            canvas.drawCircle(getWidth()/2,getHeight()/2 - getWidth()/3,dotRadius , myPan);
            canvas.rotate(6,getWidth()/2,getHeight()/2);
        }

        myPan.setColor(ContextCompat.getColor(getContext(), R.color.green));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2 - getWidth() / 3, 2*dotRadius, myPan);

        myPan.setColor(ContextCompat.getColor(getContext(), R.color.red));

        int count = canvas.save();
        for(int i=0;i<=seconds;i++){
            if(i!=0) {
                //myPan.setStrokeWidth(10);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2 - getWidth() / 3, dotRadius, myPan);
            }
            canvas.rotate(6,getWidth()/2,getHeight()/2);
        }

        canvas.restoreToCount(count);
        canvas.rotate(180,getWidth()/2,getHeight()/2);
//        for(int i=0;i<60;i++){
//            if(i==minutes) {
//                myPan.setStrokeWidth(10);
//                canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2,(getHeight() / 2 - radius + 290), myPan);
//            }
//
//            if(i==seconds) {
//                myPan.setStrokeWidth(5);
//                canvas.drawLine(getWidth() / 2, (getHeight() / 2 - radius + 310), getWidth() / 2,getHeight() / 2, myPan);
//            }
//            canvas.rotate(6,getWidth()/2,getHeight()/2);
//        }
//        for(int i=0;i<=hours;i++){
//            if(i==hours) {
//                myPan.setStrokeWidth(14);
//                canvas.drawLine(getWidth() / 2, (getHeight() / 2 - radius + 250), getWidth() / 2,getHeight() / 2, myPan);
//            }
//            canvas.rotate(30,getWidth()/2,getHeight()/2);
//
//        }
        invalidate();
    }



}
