package com.npc.dev.app;
import android.app.*;
import android.content.*;

public class MessageBox
{
	public static void Show(Context c,String message,String title){
		AlertDialog.Builder b=new AlertDialog.Builder(c);
		b.setTitle(title);
		b.setMessage(message);
  	b.setPositiveButton("Ok", new AlertDialog.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
				
				}
		});
		b.show();

	
	}
	public static void Show(Context c,String message,String title,MessageBoxIcon icon){
		AlertDialog.Builder b=new AlertDialog.Builder(c);
		b.setTitle(title);
		b.setMessage(message);
		switch(icon){
			case Default:
				
				break;
		}
		b.show();
	}
}

enum MessageBoxIcon{
	Default,
	Warning,
	Information,
	Error
}
