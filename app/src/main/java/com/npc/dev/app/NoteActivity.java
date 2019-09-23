package com.npc.dev.app;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import android.support.v7.widget.Toolbar;
public class NoteActivity extends AppCompatActivity
{
	
	public static Boolean flag=false;
	public static Boolean isOpen=false;
	public static String title="";
  SQLiteDBUtilities util;
	EditText etTitle,etContent;
	Button btnAddNotes;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_layout);
		util=new SQLiteDBUtilities(this);

		
		
		init();
		
		
		
		
	}
	private void init(){
		//Intent i=getIntent();
		//String title=i.getStringExtra("title");
		//etTitle.setText(title);
	
		
		Toolbar toolbar =(Toolbar) findViewById(R.id.subToolbar);        
		setSupportActionBar(toolbar);
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		etTitle=(EditText)findViewById(R.id.etTitle);
		
		
		etContent=(EditText)findViewById(R.id.etContent);
		btnAddNotes=(Button)findViewById(R.id.btnAddNotes);
		btnAddNotes.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
		    if(btnAddNotes.getText().toString().equals("ADD NOTE")){
					Save();
				  }
					if(btnAddNotes.getText().toString().equals("EDIT NOTE")){
						Update();
					}
				}

			});
			if(!isOpen){
			if(MyAdapter.titles.size()>0){
			String text=MyAdapter.titles.get(Tab1.location);
		  String content=MyAdapter.content.get(Tab1.location);
			if(text!=""){
				etTitle.setText(text);
				etContent.setText(content);
				title=text;
				btnAddNotes.setText("EDIT NOTE");
			}
			else{
				etTitle.setText("");
				etContent.setText("");
				btnAddNotes.setText("ADD NOTE");
			}
  	}
		
		else{
			etTitle.setText("");
			etContent.setText("");
			btnAddNotes.setText("ADD NOTE");
		}
		}
		else{
			etTitle.setText("");
			etContent.setText("");
			btnAddNotes.setText("ADD NOTE");
		}
		
		
	}
	private void Save(){
			util.insertQuery("tblNotes",
													 new String[]{"NOTETITLE","NOTECONTENT","NOTEDATE"},
													 new String[]{
														 etTitle.getText().toString(),
														 etContent.getText().toString(),
														 Util.getCurrentDateTime()
													 });
					Util.Show(getApplicationContext(),"Saved Successfully");
					isOpen=false;
					Intent i=new Intent(getApplicationContext(),NavActivity.class);
					startActivity(i);
					finish();
		
		
	}

	private void Update()
	{
		boolean res =util.UpdateQuery2("tblNotes",
		                 title,
										 new String[]{"NOTETITLE","NOTECONTENT","NOTEDATE"},
										 new String[]{ etTitle.getText().toString(),etContent.getText().toString(),Util.getCurrentDateTime()}
										 );
										 
	  if(res){						 
		Util.Show(getApplicationContext(),"Updated Successfully");
		isOpen=false;
		Intent i=new Intent(getApplicationContext(),NavActivity.class);
		startActivity(i);
		finish();
		}
		
		
	}	
	
	@Override
	public void onBackPressed(){
		isOpen=false;
		etTitle.setText("");
		etContent.setText("");
		Intent i=new Intent(getApplicationContext(),NavActivity.class);
		startActivity(i);
		finish();
		super.onBackPressed();
	}
}
