package com.npc.dev.app;
import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.*;
import android.graphics.*;

public class ProfessorActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.professor_layout);
		Toolbar toolbar =(Toolbar) findViewById(R.id.subToolbar);        
		setSupportActionBar(toolbar);
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
	}
	
	
}
