package com.zky.shudu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class ShuduView extends View{

	private float width;
	private float height;
	private int selectX;
	private int selectY;
	GameData data = new GameData();
	public ShuduView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		this.width = w/9;
		this.height = h/9;
		super.onSizeChanged(w, h, oldw, oldh);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint bgPaint = new Paint();
		bgPaint.setColor(getResources().getColor(R.color.shudu_bg));
		canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);
		
		Paint darkPaint = new Paint();
		darkPaint.setColor(getResources().getColor(R.color.shudu_dark));

		Paint hilitePaint = new Paint();
		hilitePaint.setColor(getResources().getColor(R.color.shudu_hilite));

		Paint lightPaint = new Paint();
		lightPaint.setColor(getResources().getColor(R.color.shudu_light));
		
		for(int i =0;i < 9;i++  ){
			canvas.drawLine(0, i*height, getWidth(), i*height, lightPaint);
			canvas.drawLine(0, i*height+1, getWidth(), i*height+1, hilitePaint);
			canvas.drawLine(i*width, 0,i*width, getHeight(), lightPaint);
			canvas.drawLine(i*width+1, 0,i*width+1, getHeight(), hilitePaint);
		}
		
		for(int i =0;i < 3;i++  ){
			canvas.drawLine(0, i*height*3, getWidth(), i*height*3, darkPaint);
			canvas.drawLine(0, i*height*3+1, getWidth(), i*height*3+1, hilitePaint);
			canvas.drawLine(i*width*3, 0,i*width*3, getHeight(), darkPaint);
			canvas.drawLine(i*width*3+1, 0,i*width*3+1, getHeight(), hilitePaint);
		}
		
		
		Paint numberPaint = new Paint();
		numberPaint.setColor(Color.BLUE);
		numberPaint.setStyle(Paint.Style.STROKE);
		numberPaint.setTextSize(height*0.75f);
		numberPaint.setTextAlign(Paint.Align.CENTER);
		
	
		FontMetrics fontMetrics = numberPaint.getFontMetrics();
		float x = width/2;
		float y = height/2 -(fontMetrics.ascent+fontMetrics.descent)/2;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				canvas.drawText(data.getNumber(i, j), i*width+x,  j*height+y, numberPaint);
			}
		}
	
		super.onDraw(canvas);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return super.onTouchEvent(event);
		}
		
		selectX = (int) (event.getX()/width);
		selectY = (int) (event.getY()/height);
	
		Log.i("shudu", "selectX="+selectX+";selectY="+selectY);
		char[] c = data.getUsedNumber(selectX, selectY);
		for (int i = 0; i < 9; i++) {
			Log.i("shudu", "c["+i+"]="+c[i]);
		}
		
		MyDialog dialog = new MyDialog(this.getContext(), c,this);
		dialog.show();
		return true;
	}
	
	public void setNumber(String s){
		char c = s.charAt(0);
		data.setStringArr(selectX, selectY, c);
		invalidate();
	}

}
