package com.npc.dev.app;
import android.app.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.widget.*;
import android.support.v7.app.*;
import android.content.*;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.*;
import android.view.*;


public class PersonalActivity extends AppCompatActivity 
{
	private EditText title;
	private EditText content;
	private Button btnAdd;
	SQLiteDBUtilities util;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
				try{
				
        setContentView(R.layout.personal_layout);
			Toolbar toolbar =(Toolbar) findViewById(R.id.subToolbar);        
			setSupportActionBar(toolbar);
			toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
			util=new SQLiteDBUtilities(this);
			init();
			}
			catch(Exception ex){
				Util.Show(this,ex.getMessage());
			}
			
    }
		private void init(){
			title=(EditText)findViewById(R.id.etPTitle);
			content=(EditText)findViewById(R.id.etPContent);
			btnAdd=(Button)findViewById(R.id.btnPAddNotes);
			btnAdd.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						
						Save();
						
					}

				
			});
			
		}
		private void Save(){
			util.insertQuery("tblpnote",
											 new String[]{"NOTETITLE","NOTECONTENT","NOTEDATE"},
											 new String[]{
												 title.getText().toString(),
												 content.getText().toString(),
												 Util.getCurrentDateTime()
											 });
			Util.Show(getApplicationContext(),"Saved Successfully");
			Intent i=new Intent(getApplicationContext(),NavActivity.class);
			startActivity(i);
			finish();
			
			
		}
		
		
		
		@Override
		public void onBackPressed()
		{
			super.onBackPressed();
			Intent i=new Intent(this,NavActivity.class);
			startActivity(i);
			finish();
		}
		
		
}
