package com.npc.dev.app;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;
public class EventActivity extends AppCompatActivity
{
	
	
	EditText etStart,etEnd;
	SQLiteDBUtilities util;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_layout);
		util=new SQLiteDBUtilities(this);
		init();
	}
	private void init(){
		Toolbar toolbar =(Toolbar) findViewById(R.id.subToolbar);        
		setSupportActionBar(toolbar);
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		ActionBar actionbar = getSupportActionBar();        
		
		Button btnStart=(Button)findViewById(R.id.btnStartDate);
		Button btnEnd=(Button)findViewById(R.id.btnEndDate);
		etStart=(EditText)findViewById(R.id.etStartDate);
		etEnd=(EditText)findViewById(R.id.etEndDate);
		btnStart.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
			   setDate("Start Date",etStart);
					
				}

			
		});
		btnEnd.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					setDate("End Date",etEnd);

				}


			});
			final EditText etTitle=(EditText)findViewById(R.id.etEventTitle);
		final EditText etEventDesc=(EditText)findViewById(R.id.etEventDesc);
		
			Button btnSaveEvent=(Button)findViewById(R.id.btnSaveEvent);
		btnSaveEvent.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					String[] cols={	"EVENTTITLE","EVENTSTART","EVENTEND","EVENTDESC"};
					String[] values={
						etTitle.getText().toString(),
						etStart.getText().toString(),
						etEnd.getText().toString(),
						etEventDesc.getText().toString()
					};
					util.insertQuery("tblevents",cols,values);
				  //MessageBox.Show(getApplicationContext(),"Saved","NPC Student Planner");
					Intent i=new Intent(getApplicationContext(),NavActivity.class);
					startActivity(i);
					finish();
				}

				
			});
			
			
	
	}
	private void setDate(String date,final EditText et){
		try{
			AlertDialog.Builder builder=new AlertDialog.Builder(EventActivity.this);
			builder.setTitle(date);
			final DatePicker picker = new DatePicker(EventActivity.this);
			builder.setView(picker);
			builder.setPositiveButton("OK", new AlertDialog.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						// TODO: Implement this method
						String formatted=Util.formatMonth(picker.getMonth())+"-"+picker.getDayOfMonth()+"-"+picker.getYear();
						Util.Show(getApplicationContext(),formatted);
						et.setText(formatted);
					}

				
			});
			builder.show();
			
		}
		catch(Exception ex){
			Util.Show(this,ex.getMessage());
			ex.printStackTrace();
		}
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
