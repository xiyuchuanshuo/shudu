package com.zky.shudu;

public class GameData {

	private String data = "000000001080060007009084030"
			+"910400205000308000208001064" + "020930100700040090600000000";
	private char[] stringArr = data.toCharArray();

	public String getNumber(int x, int y) {
		int i = y * 9 + x;
		String string = stringArr[i] + "";
		if (string.equals("0")) {
			return "";
		} else {
			return string;
		}
	}

	public void setStringArr(int x, int y, char number) {
		stringArr[y * 9 + x] = number;
	}
	
	public char[] getUsedNumber(int x,int y){
		char[] c = new char[9] ;
		for (int i = 0; i < 9; i++) {
			c[i] = '0';
		}

		for (int i = 0; i < 9; i++) {
			if(y == i){
				continue;
			}
			if(stringArr[i*9+x] != '0'){
				String s = String.valueOf(stringArr[i*9+x]);
				//再转换成Int类型：
				int a = Integer.parseInt(s);
				c[a-1] = stringArr[i*9+x];
			}
		}
		for (int i = 0; i < 9; i++) {
			if(x == i){
				continue;
			}
			if(stringArr[y*9+i] != '0'){
				String s = String.valueOf(stringArr[y*9+i]);
				//再转换成Int类型：
				int a = Integer.parseInt(s);
				c[a-1] = stringArr[y*9+i];
			}
		}
		int rectX = x/3;
		int rextY = y/3;
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				int a = 3*rectX+rextY*27+i*9+j;
				if(a == (x+y*27)){
					continue;
				}
				if(stringArr[a] != '0'){
					String s = String.valueOf(stringArr[a]);
					//再转换成Int类型：
					int t = Integer.parseInt(s);
					c[t-1] = stringArr[a];
				}
			}
		}
		return c;
		
	}

}
