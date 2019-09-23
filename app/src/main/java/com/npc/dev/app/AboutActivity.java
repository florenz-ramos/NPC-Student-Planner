package com.npc.dev.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;

public class AboutActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

		@Override
		public void onBackPressed()
		{
			// TODO: Implement this method
			super.onBackPressed();
			Intent i=new Intent(this,NavActivity.class);
			startActivity(i);
			finish();
		}
		
		
}
