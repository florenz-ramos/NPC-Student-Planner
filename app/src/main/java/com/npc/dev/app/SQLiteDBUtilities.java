package com.npc.dev.app;
import android.database.sqlite.*;
import android.content.*;
import android.database.*;
import java.util.*;
import android.graphics.*;
import java.io.*;


public class SQLiteDBUtilities  extends SQLiteOpenHelper
{
	public SQLiteDBUtilities(Context c){
		super(c,SQLiteParams.dbName,null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		for(int i=0;i<SQLiteParams.Queries.length;i++)
			p1.execSQL(SQLiteParams.Queries[i]);
	}
	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		for(int i=0;i<SQLiteParams.tables.length;i++){ 
			p1.execSQL("DROP TABLE IF EXISTS "+SQLiteParams.tables[i]);  
		}
		onCreate(p1);
	}
	
	//SQLITE CRUD
	public Boolean insertQuery(String tblName,String[] col,String[] val){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		for(int i=0;i<col.length;i++){
			cv.put(col[i],val[i]);
		}
		long res=db.insert(tblName,null,cv);
		db.close();
		return (res==-1?false:true);
	}
	public Cursor SelectQuery(String query){
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor res=db.rawQuery(query,null);
		return res;
	}	
	public int DeleteQuery(String tblName,String col,String val){
		SQLiteDatabase db=this.getWritableDatabase();
		int i=db.delete(tblName,col+"=?",new String[]{val});
		return i;
	}
	public boolean UpdateQuery(String tbl,String where,String[] col, String[] val){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		for(int i=0;i<col.length;i++){
			cv.put(col[i],val[i]);
		}
		int res=db.update(tbl,cv,where+"=?",new String[]{val[0]});
		return (res>0?true:false);
	}
	public boolean UpdateQuery2(String tbl,String value,String[] col, String[] val){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		for(int i=0;i<col.length;i++){
			cv.put(col[i],val[i]);
		}
		int res=db.update(tbl,cv,"NOTETITLE=?",new String[]{value});
		return (res>0?true:false);
	}
	
	
	
	public String getCourse(){
		Cursor c=SelectQuery("Select COURSE from tblprofile");
		c.moveToFirst();
		switch(c.getString(0)){
			case "BSCS": return "Bachelor of Science in Computer Science";
			case "BSED": return "Bachelor of Secondary Education";
			case "BEED": return "Bachelor of Elementary Education";
			case "BSBA": return "Bachelor of Science in Bussiness Administration";
			case "OFF-AD":return "Office Administrstion";
		}
		return "";
	}

	public Bitmap getImage(){
	  Cursor c=SelectQuery("Select PICTURE from tblprofile");
		c.moveToNext();
		return BitmapFactory.decodeFile(c.getString(0));
	}
	public String getFullname(){
		Cursor c=SelectQuery("Select LASTNAME,FIRSTNAME from tblprofile");
		c.moveToFirst();
		return c.getString(0)+","+c.getString(1);
	}
	public Boolean insertImage(String tblName,String path,String[] col,String[] val){
		SQLiteDatabase db=this.getWritableDatabase();
		try{
			FileInputStream fs=new FileInputStream(path);
			byte[] imgByte=new byte[fs.available()];
			ContentValues cv=new ContentValues();
			for(int i=0;i<col.length;i++){
				cv.put(col[i],val[i]);
			}
			cv.put("PICTURE",imgByte);
			db.insert(tblName,null,cv);
			fs.close();
			return true;
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	public Bitmap getImages(){
		byte[] blob=null;
	  Cursor c=SelectQuery("Select PICTURE from tblprofile WHERE ID=1");
		while(c.moveToNext()){
			blob=c.getBlob(c.getColumnIndex("PICTURE"));

		}
		return BitmapFactory.decodeByteArray(blob,0,blob.length);

	}
	
	
	

	


}
