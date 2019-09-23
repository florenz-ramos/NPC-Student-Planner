package com.npc.dev.app;
import android.support.v4.app.Fragment;
import android.os.*;
import android.view.*;
import android.net.*;
import android.content.*;
import android.widget.*;
import java.util.*;
import android.database.*;
import android.support.v7.app.*;
import android.widget.AdapterView.*;

public class Tab4 extends Fragment
{
	private static final String a_p1="p1";
	private static final String a_p2="p2";
	private String p1,p2;
	SQLiteDBUtilities util;
	private OnFragmentInteractionListener listener;
	public Tab4(){

	}
	public static Tab4 newInstance(String p1,String p2){
		Tab4 frag=new Tab4();
		Bundle args=new Bundle();
		args.putString(a_p1,p1);
		args.putString(a_p2,p2);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		if(getArguments()!=null){
			p1=getArguments().getString(a_p1);
			p2=getArguments().getString(a_p2);

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
	  View view =inflater.inflate(R.layout.fragment_tab4,container,false);
		//tv=(TextView)view.findViewById(R.id.sample);
		//tv.setText("HOME");
		return view;
	}
	public void onButtonPressed(Uri uri){
		if(listener!=null){
			listener.OnFragmentInteraction(uri);
		}
	}


	@Override
	public void onAttach(Context context)
	{
		// TODO: Implement this method
		super.onAttach(context);
		if(context instanceof OnFragmentInteractionListener){
			listener=(OnFragmentInteractionListener)context;
		}
		else{
			throw new RuntimeException(context.toString()+" must implement OnFragmentInterActionListener");
		}
	}

	@Override
	public void onDetach()
	{
		// TODO: Implement this method
		super.onDetach();
		listener=null;
	}
	public interface OnFragmentInteractionListener{
		void OnFragmentInteraction(Uri uri);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onActivityCreated(savedInstanceState);
		init();
	}
	public void init()
	{
		util=new SQLiteDBUtilities(getActivity());
		View view=this.getView();
		ListView lvl=(ListView)view.findViewById(R.id.eventsListView);
		final ArrayAdapter ad=new EventAdapter(getActivity(),
						GetSingleData("SELECT EVENTTITLE FROM tblevents"),
						GetData("SELECT EVENTSTART,EVENTEND FROM tblevents")
			);
		lvl.setAdapter(ad);
		lvl.setOnItemLongClickListener(new OnItemLongClickListener(){

				@Override
				public boolean onItemLongClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
					adb.setTitle("Message");
					adb.setMessage("Delete Subject Successfully");
					//adb.setNegativeButton("Cancel",null);
					final int pos=p3;
					adb.setPositiveButton("Ok", new AlertDialog.OnClickListener(){
							@Override
							public void onClick(DialogInterface p1, int p2){
								util.DeleteQuery("tblevents","EVENTTITLE",EventAdapter.eventTitle.get(pos));
								ad.remove(ad.getItem(pos));
								ad.notifyDataSetChanged();
							}		
						});
					adb.show();
					return true;
				}


			});
		
	}
	
	public List<String> GetSingleData(String query){
	  List<String> lst=new ArrayList<String>();
		Cursor c=util.SelectQuery(query);
		if(c!=null && c.getCount()>0){
			while(c.moveToNext()) lst.add(c.getString(0));
		}
		else{
			Util.Show(getActivity(),"No events");
		}
		return lst;
	}

	public List<String> GetData(String query){
	  List<String> lst=new ArrayList<String>();
		Cursor c=util.SelectQuery(query);
		if(c!=null&&c.getCount()>0){
			while(c.moveToNext()) 
				lst.add("Date Start:"+c.getString(0)+"\nDate End:"+c.getString(1));
		}
		/*else{
			Util.Show(getActivity(),"Nooooo");
		}*/
		return lst;
	}
}
