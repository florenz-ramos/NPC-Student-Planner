package com.npc.dev.app;
import android.support.v7.app.*;
import android.os.*;
import android.widget.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.net.*;
import android.support.design.widget.TabLayout.*;
import android.view.*;
import java.util.*;
import android.database.*;
import android.support.v4.widget.*;
import android.content.*;
import android.graphics.*;
import android.support.v7.widget.Toolbar;
import android.text.*;

public class NavActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener,
                                                              Tab2.OnFragmentInteractionListener,
												
										
																															Tab3.OnFragmentInteractionListener,
																															Tab4.OnFragmentInteractionListener
{
	SQLiteDBUtilities util;
	//FloatingActionButton fabAddNote;
	private DrawerLayout mDrawerLayout;
	private void close(MenuItem item){
		item.setChecked(true);                                          
		mDrawerLayout.closeDrawers();
	}
	private void Open(Class obj){
		Intent i=new Intent(this,obj);
		startActivity(i);
		finish();
	}
	@Override
	public void OnFragmentInteraction(Uri uri)
	{
		// TODO: Implement this method
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		try{
			
			util=new SQLiteDBUtilities(this);
			if(SelectSetup()){
				AlertDialog.Builder ab= new AlertDialog.Builder(this);
				ab.setTitle("App Locked!");
				ab.setMessage("Enter the code to access the app");
				final EditText text=new EditText(this);
				ab.setView(text);
				
				ab.setPositiveButton("Ok", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							if(text.getText().toString().equals("")){
								finish();
							}
							else if(text.getText().toString().equals("npc-admin")){
									util.UpdateQuery(
									  "tblsetup",
									  "ID",
									  new String[]{"ID","VALUE"},
										new String[]{"1","1"}
									);
									init();
								}
								else{
									finish();
								}
							
						}
					});
				ab.setNegativeButton("Close", new AlertDialog.OnClickListener(){

						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							finish();
						}

					
				});
					
				ab.show();		
			}
			init();
			
			if(!SelectSetup()){
				AlertDialog.Builder adb=new AlertDialog.Builder(this);
				adb.setTitle("Confirmation...");
				adb.setMessage("Please enter your student number");
				final EditText txt=new EditText(this);
				txt.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				adb.setView(txt);
				adb.setPositiveButton("Confirm", new AlertDialog.OnClickListener(){

						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							if(SelectStudentID(txt.getText().toString())){
								init();
							}
							else{
								finish();
							}
						}
						

					
					
				});
				adb.show();
			}
			
			
		}
		
		catch(Exception ex){
			ex.printStackTrace();
			Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
			
		}
	}
	public void init(){
		setContentView(R.layout.nav_layout);
		Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);        
		setSupportActionBar(toolbar);
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		ActionBar actionbar = getSupportActionBar();        
		actionbar.setDisplayHomeAsUpEnabled(true);        
		actionbar.setHomeAsUpIndicator(R.drawable.nav_icon);
		mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);        
		NavigationView navigationView =(NavigationView)findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
				@Override                    
				public boolean onNavigationItemSelected(MenuItem menuItem) {       
					switch(menuItem.getItemId()){
						case R.id.nav_note:
							close(menuItem);
							NoteActivity.isOpen=true;
							Open(NoteActivity.class);
							return true;
						case R.id.nav_subject:
						  close(menuItem);
						  Open(SubjectActivity.class);
							return true;
					  case R.id.nav_event:
						  close(menuItem);
							Open(EventActivity.class);
							return true;
						case R.id.nav_exit:
							finish();
						  return true;
						case R.id.nav_pnote:
							close(menuItem);
							Open(PersonalActivity.class);
							return true;
						 case R.id.nav_about:
							 close(menuItem);
							 Open(AboutActivity.class);
							 return true;
					}                        
					return false;            
				}                
			}); 
		/*fabAddNote=(FloatingActionButton)findViewById(R.id.fabAddNote);
		fabAddNote.setBackgroundColor(Color.parseColor("#000000"));
		fabAddNote.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					NoteActivity.isOpen=true;
					Open(NoteActivity.class);
				}	
			});*/
		TabLayout tl=(TabLayout)findViewById(R.id.tablayout);
		tl.addTab(tl.newTab().setText("SCHOOL NOTES"));
		tl.addTab(tl.newTab().setText("PERSONAL NOTES"));
		tl.addTab(tl.newTab().setText("SUBJECTS"));
		tl.addTab(tl.newTab().setText("EVENTS"));
		tl.setTabGravity(TabLayout.GRAVITY_FILL);
		final ViewPager vp=(ViewPager)findViewById(R.id.pager);
		final PagerAdapter ad=new PagerAdapter(getSupportFragmentManager(),tl.getTabCount());
		//vp.setPageTransformer(true,new CubeTransformer());
		vp.setAdapter(ad);
		vp.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
		tl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

				@Override
				public void onTabSelected(TabLayout.Tab p1)
				{
					// TODO: Implement this method
					vp.setCurrentItem(p1.getPosition());
				
				}

				@Override
				public void onTabUnselected(TabLayout.Tab p1)
				{
					// TODO: Implement this method
				}

				@Override
				public void onTabReselected(TabLayout.Tab p1)
				{
					// TODO: Implement this method
				}

			
		});
		View view=navigationView.getHeaderView(0);
	  ImageView imageProfile=(ImageView)view.findViewById(R.id.navheaderImageView1);
		TextView txtFullname=(TextView)view.findViewById(R.id.txtFullname);
		TextView txtCourse=(TextView)view.findViewById(R.id.txtCourse);
		//imageProfile.setImageBitmap(util.getImage());
		txtFullname.setText(util.getFullname());
		txtCourse.setText(util.getCourse());	
		imageProfile.setImageBitmap(util.getImage());

		
	}
	@Override    
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:                
				mDrawerLayout.openDrawer(GravityCompat.START);                
				return true;      
		}        
		return super.onOptionsItemSelected(item);    
	}
	
	
	public List<String> PopulateNotes(String query){
	  List<String> lst=new ArrayList<String>();
		Cursor c=util.SelectQuery(query);
		while(c.moveToNext()) lst.add(c.getString(0));
		return lst;
	}
	
	
	
	public boolean SelectSetup(){
		Cursor c=util.SelectQuery("Select *from tblsetup");
		c.moveToFirst();
		String value=c.getString(1);
		return (value.equals("0")?true:false);
	}
	
	public boolean SelectStudentID(String val){
		Cursor c=util.SelectQuery("SELECT STUDENT_ID from tblprofile");
		c.moveToFirst();
		String value=c.getString(0);
		return (value.equals(val)?true:false);
	}
	
	
}
