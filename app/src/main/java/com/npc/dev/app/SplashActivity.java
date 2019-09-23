package com.npc.dev.app;
import android.support.v7.app.*;
import android.os.*;
import java.util.concurrent.*;
import android.content.*;
import android.database.*;

public class SplashActivity extends AppCompatActivity
{
	SQLiteDBUtilities dbutil;

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		dbutil=new SQLiteDBUtilities(this);
		initView();
	}
	
	private void initView(){
		new Handler().postDelayed(new Runnable(){

				@Override
				public void run()
				{
					// TODO: Implement this method
				  //dbutil.insertQuery("tbldummy",new String[]{"VALUE"},new String[]{"0"});
			
					if(!hasData("Select count(*)from tblsetup")){
						//dbutil.insertQuery(SQLiteParams.tables[1],
					//										 new String[]{"VALUE"},new String[]{"0"});
						Intent i=new Intent(getApplicationContext(),ProfileActivity.class);
						startActivity(i);
						finish();
					}
					else{
						if(hasData("SELECT COUNT(*) FROM tblprofile")){
							Intent i=new Intent(getApplicationContext(),NavActivity.class);
							startActivity(i);
							finish();
						}
						else{
							finish();
						}
					}

				}


			},2500);
	}
	private boolean hasData(String query){
		
		Cursor res=dbutil.SelectQuery(query);
    res.moveToFirst();
		int count=res.getInt(0);
		
	 return (((count>0)&&(res!=null))?true:false);
		
		
	}
	public void Select(){
		/*Cursor res=dbutil.SelectQuery("Select count(*)from tblsetup");
    res.moveToFirst();
		int count=res.getInt(0);
		if(count==0&&!isStudentInfoExists()){
			dbutil.insertQuery(SQLiteParams.tables[1],
												 new String[]{"VALUE"},new String[]{"0"});
			Intent i=new Intent(this,ProfileActivity.class);
			startActivity(i);
			finish();
		}*/
	}

	
	
	
}
