package com.example.tek.first.servant.todolist.customview.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.tek.first.servant.R;

public class ToDoItemTitleCustomView extends TextView {
    private Paint linePaint;
    private int paperColor;

    public ToDoItemTitleCustomView(Context context) {
        super(context);
        init();
    }

    public ToDoItemTitleCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToDoItemTitleCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(getResources().getColor(R.color.notepad_lines));

        paperColor = getResources().getColor(R.color.notepad_paper);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // Color as paper
        canvas.drawColor(paperColor);

        // Draw ruled lines
        canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);

        canvas.save();

        // Use the TextView to render the text
        super.onDraw(canvas);
        canvas.restore();
    }
}
