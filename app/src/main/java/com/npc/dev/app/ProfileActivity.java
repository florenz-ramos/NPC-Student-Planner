package com.npc.dev.app;
import android.content.*;
import android.database.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;
public class ProfileActivity extends AppCompatActivity
{
	Button btn;
	Spinner spin;
	ImageView image;
	EditText txtStdID,txtFname,txtLname;
	SQLiteDBUtilities util;
	public String path;
	private static final int PICK_IMAGE=100;
	String[] courses={"BSCS","BSBA","BSED","BEED","OFF-AD"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_layout);
		util=new SQLiteDBUtilities(this);
		init();
	}
	public void init(){
			Toolbar toolbar =(Toolbar) findViewById(R.id.subToolbar);        
		setSupportActionBar(toolbar);
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		
		
		spin=(Spinner)findViewById(R.id.spinCourse);
		ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,courses);
	  spin.setAdapter(ad);
		txtStdID=(EditText)findViewById(R.id.txtSID);
		txtFname=(EditText)findViewById(R.id.txtFname);
		txtLname=(EditText)findViewById(R.id.txtLname);
		btn=(Button)findViewById(R.id.btnSave);
		btn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					String[] cols={"STUDENT_ID","FIRSTNAME","LASTNAME","COURSE","PICTURE"};
					String[] values={
						txtStdID.getText().toString(),
						txtFname.getText().toString(),
						txtLname.getText().toString(),
						String.valueOf(spin.getSelectedItem()),
						path
					};
					//util.insertImage("tblprofile",path,cols,values);
					util.insertQuery(SQLiteParams.tables[1],
															 new String[]{"VALUE"},new String[]{"0"});
					
															 
					Util.Show(getApplicationContext(),path);
					util.insertQuery("tblprofile",cols,values);
					Intent i=new Intent(getApplicationContext(),NavActivity.class);
					startActivity(i);
					finish();
					/*	}
					 else{
					 Util.Show(getApplicationContext(),"Not inserted");
					 }
					 */
				}		

			});
		image=(ImageView)findViewById(R.id.image_profile);
		image.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					Intent i=new Intent(Intent.ACTION_PICK,Uri.parse("content://media/internal/images/media"));
					startActivityForResult(i,PICK_IMAGE);
				}	
			});	
	}
	private String getPath(Uri uri){
		if(uri==null) return null;
		String[] projection={MediaStore.Images.Media.DATA};
		Cursor c=managedQuery(uri,projection,null,null,null);
		if(c!=null){
			int col_idx=c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			c.moveToFirst();
			return c.getString(col_idx);
		}
		return uri.getPath();
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK&&requestCode==PICK_IMAGE){
			Uri uri=data.getData();
			image.setImageURI(uri);
			path=getPath(uri);
		}	
		
	}
	
	
}
