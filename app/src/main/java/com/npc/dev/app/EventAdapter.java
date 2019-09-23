package com.npc.dev.app;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.view.*;

public class EventAdapter extends ArrayAdapter
{
	public static List<String> eventTitle,date;
	public EventAdapter(Context c,List<String> eventTitle,List<String> date){
		super(c,R.layout.custom_event_layout,eventTitle);
		this.eventTitle=eventTitle;
		this.date=date;
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
		View row=inflater.inflate(R.layout.custom_event_layout,parent,false);
		TextView EventTitle=(TextView)row.findViewById(R.id.tvEventTitle);
		TextView eventDate=(TextView)row.findViewById(R.id.tvEventDate);
		
		EventTitle.setText(eventTitle.get(position));
		eventDate.setText(date.get(position));
	
		return row;
	}
	
	
}
