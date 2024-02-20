package com.example.gyroscopeservice;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class MyView extends View{
    int xScor;
    int yScor;
    int x;
    int y;
    public MyView(Context context, int xScor, int yScor){
        super(context);
        this.xScor = xScor;
        this.yScor = yScor;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        x = canvas.getWidth()/2;
        y = canvas.getHeight()/2;
        Paint paintr = new Paint();
        paintr.setColor(Color.RED);
        paintr.setStyle(Paint.Style.FILL);
        x+=xScor;
        y+=yScor;
        canvas.drawCircle(x, y, 100, paintr);
        invalidate();
    }


}
