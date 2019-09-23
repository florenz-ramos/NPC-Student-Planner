package com.npc.dev.app;
import android.support.v7.app.*;
import android.os.*;
import android.app.*;
import android.widget.*;
import java.util.*;
import android.view.View.*;
import android.view.*;
import android.support.v7.widget.Toolbar;
import android.graphics.*;
import android.content.*;

public class SubjectActivity extends AppCompatActivity
{
  EditText etSubCode,etSubName,etUnits,etRoom,etProf,timeFrom,
	etTimeTo,etDays;
	TimePickerDialog timePicker;
	Calendar cal;
	int currHour,currMinute;
	String amPM;
	Button btnAdd;
	SQLiteDBUtilities util;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_layout);
		util=new SQLiteDBUtilities(this);
		try{
			init();
		//	Toolbar toolbar;
			Toolbar toolbar =(Toolbar) findViewById(R.id.subToolbar);        
			setSupportActionBar(toolbar);
			toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		}
		catch(Exception ex){
			Util.Show(this,ex.getMessage());
			ex.printStackTrace();
		}

	}
	private void init(){
		etSubCode=(EditText)findViewById(R.id.etSubCode);
		etSubName=(EditText)findViewById(R.id.etSubname);
		etUnits=(EditText)findViewById(R.id.etUnits);
		etRoom=(EditText)findViewById(R.id.etRoom);
		etProf=(EditText)findViewById(R.id.etProfessor);
		timeFrom=(EditText)findViewById(R.id.etTimeFrom);
		etTimeTo=(EditText)findViewById(R.id.etTimeTo);
		etDays=(EditText)findViewById(R.id.etDays);
		
		
			
			btnAdd=(Button)findViewById(R.id.btnAdd);
		  btnAdd.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					EditText[] et={etSubCode,etSubName,etUnits,etRoom,etDays,etProf};
					if(!Util.CheckString(getApplicationContext(),et)){
						SaveData();
					}
				}

				
			});
	}
	private void SaveData(){
		
		String[] columns={
			"SUBCODE","SUBNAME",
			"UNITS","ROOM","PROFESSOR",
			"TIMEFROM","TIMETO","DAY"

		};
		String[] values={
			Util.getString(etSubCode),
			Util.getString(etSubName),
			Util.getString(etUnits),
			Util.getString(etRoom),
			Util.getString(etProf),
			Util.getString(timeFrom),
			Util.getString(etTimeTo),
			Util.getString(etDays)
		};
		util.insertQuery("tblsubjects",columns,values);
		Util.Show(getApplicationContext(),"Saved!");
		Intent intent=new Intent(getApplicationContext(),NavActivity.class);
		startActivity(intent);
		finish();
		
	}
	
	
	
	
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		Intent intent=new Intent(this,NavActivity.class);
		startActivity(intent);
		finish();
	}
	
	
}
