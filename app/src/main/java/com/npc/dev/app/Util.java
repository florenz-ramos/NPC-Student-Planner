package com.npc.dev.app;
import android.content.*;
import android.widget.*;
import java.util.*;
import java.text.*;

public class Util
{
	public static void Show(Context c,String m){
		Toast.makeText(c,m,Toast.LENGTH_LONG).show();
	}
	public static String getCurrentDateTime(){
		Calendar c = Calendar.getInstance();
    SimpleDateFormat dateformat =
			new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
    return dateformat.format(c.getTime());
	}
	public static String getString(EditText text){
		return text.getText().toString();
	}
	
	public Boolean isExists(String key,List<String> values){
		for(int i=0;i<values.size();i++){
			return(key.equals(values.get(i))?true:false);
		}
		return false;
	}
	public static int formatMonth(int m){
		switch(m){
		  case 0:return 1;
			case 1:return 2;	
			case 2:return 3;		
			case 3:return 4;
			case 4:return 5;
			case 5:return 6;	
			case 6:return 7;		
			case 7:return 8;
			case 8:return 9;
			case 9:return 10;	
			case 10:return 11;		
			case 11:return 12;
			default:return 0;
		}
	}
  public static boolean CheckString(Context c,EditText[] ETn){
		boolean retVal=false;
		for(EditText et:ETn){
			if(et.getText().toString().equals("")){
			Show(c,"Please fill up the fields");
			retVal=true;
			}
			
		}
		return retVal;
	}
	
	
	
	
}
