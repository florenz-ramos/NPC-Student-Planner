package com.npc.dev.app;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.view.*;

public class SubjectAdapter extends ArrayAdapter
{
	public static List<String> subCode,subName,Room,Time,Day,Prof;
	
	public SubjectAdapter(Context c,
	                 List<String> subCode,
									 List<String> subName,
									 List<String> Room,
									 List<String> Time,
									 List<String> Day,
									 List<String>Prof
								
									 ){
		super(c,R.layout.custom_subject_layout,subCode);
		this.subCode=subCode;
		this.subName=subName;
		this.Room=Room;
		this.Time=Time;
		this.Day=Day;
		this.Prof=Prof;
		
		
	}
	@Override
	public Object getItem(int position)
	{
    // TODO: Implement this method
		return super.getItem(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row=inflater.inflate(R.layout.custom_subject_layout,parent,false);
		TextView sCode=(TextView)row.findViewById(R.id.tvSubCode);
		TextView sName=(TextView)row.findViewById(R.id.tvSubName);
		TextView room=(TextView)row.findViewById(R.id.tvRoom);
		TextView time=(TextView)row.findViewById(R.id.tvTime);
		TextView day=(TextView)row.findViewById(R.id.tvDay);
		TextView prof=(TextView)row.findViewById(R.id.tvProf);
		sCode.setText(subCode.get(position));
		sName.setText(subName.get(position));
		room.setText(Room.get(position));
		time.setText(Time.get(position));
		day.setText(Day.get(position));
		prof.setText(Prof.get(position));
		return row;
	}
	 
	
	
}
