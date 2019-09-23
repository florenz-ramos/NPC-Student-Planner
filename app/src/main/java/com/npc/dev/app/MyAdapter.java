package com.npc.dev.app;
import android.widget.*;
import android.content.*;
import android.view.*;
import javax.sql.*;
import java.util.*;
public class MyAdapter extends ArrayAdapter
{
	public static List<String> titles,content,date;
	public MyAdapter(Context c,List<String> noteTitle,List<String> cont,List<String> d){
		super(c,R.layout.custom_note_layout,noteTitle);
		this.titles=noteTitle;
		this.content=cont;
		this.date=d;
		
		
	}
	@Override
	public Object getItem(int position){
		return super.getItem(position);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.custom_note_layout,parent,false);
		TextView noteTitle=(TextView)row.findViewById(R.id.tvNoteTitle);
		TextView tvContent=(TextView)row.findViewById(R.id.tvContent);
		TextView tvDate=(TextView)row.findViewById(R.id.tvDate);
		noteTitle.setText(titles.get(position));
		tvContent.setText(content.get(position));
		tvDate.setText(date.get(position));
		return row;
	}

}
