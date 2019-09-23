package com.npc.dev.app;
import android.widget.*;
import java.util.*;
import android.content.*;
import android.view.*;

public class PNoteAdapter extends ArrayAdapter
{
	public static List<String> titles,content,date;
	public PNoteAdapter(Context  c,List<String> noteTitle,List<String> cont,List<String> d){
		super(c,R.layout.custom_note_layout,noteTitle);
		this.titles=noteTitle;
		this.content=cont;
		this.date=d;
		
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
		View row=inflater.inflate(R.layout.custom_pnote_layout,parent,false);
		TextView noteTitle=(TextView)row.findViewById(R.id.tvPNoteTitle);
		TextView tvContent=(TextView)row.findViewById(R.id.tvPContent);
		TextView tvDate=(TextView)row.findViewById(R.id.tvPDate);
		noteTitle.setText(titles.get(position));
		tvContent.setText(content.get(position));
		tvDate.setText(date.get(position));
		return row;
		
	}
	

}
