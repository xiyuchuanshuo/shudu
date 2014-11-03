package com.zky.shudu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class MyDialog extends Dialog{

	private Button[] keys = new Button[9];
	private char[] c;
	private ShuduView shuduView;
	public MyDialog(Context context,char[] c,ShuduView shuduView) {
		super(context);
		this.c = c;
		this.shuduView = shuduView;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("请选择数字");
		setContentView(R.layout.dialog);
		init();
		
		for (int i = 0; i < c.length; i++) {
			if(c[i] != '0'){
				keys[i].setVisibility(View.INVISIBLE);
			}
		}
		
		listerner();
		
	}

	private void listerner() {
		for (int i = 0; i < keys.length; i++) {
			final int t = i+1;
			keys[i].setText(t+"");
			keys[i].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.i("shudu", "t="+t);
					String s = t+"";
					shuduView.setNumber(s);
					dismiss();
					
				}
			});
		}
		
	}

	private void init() {
		keys[0] = (Button) findViewById(R.id.button1);
		keys[1] = (Button)findViewById(R.id.button2);
		keys[2] = (Button)findViewById(R.id.button3);
		keys[3] = (Button)findViewById(R.id.button4);
		keys[4] = (Button)findViewById(R.id.button5);
		keys[5] = (Button)findViewById(R.id.button6);
		keys[6] = (Button)findViewById(R.id.button7);
		keys[7] = (Button)findViewById(R.id.button8);
		keys[8] = (Button)findViewById(R.id.button9);
	}

	
}
