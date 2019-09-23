package com.npc.dev.app;
import android.support.v4.app.Fragment;
import android.os.*;
import android.view.*;
import android.net.*;
import android.content.*;
import android.widget.*;
import android.database.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.support.v7.app.*;

public class Tab3 extends Fragment
{
	private static final String a_p1="p1";
	private static final String a_p2="p2";
	private String p1,p2;
	SQLiteDBUtilities util;
	//TextView tv;
	private OnFragmentInteractionListener listener;
	public Tab3(){

	}
	public static Tab3 newInstance(String p1,String p2){
		Tab3 frag=new Tab3();
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
	  View view =inflater.inflate(R.layout.fragment_tab3,container,false);
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
		//Util.Show(getActivity(),"Created!");
		init();
	}
	public boolean hasData(){
		Cursor c=util.SelectQuery("SELECT COUNT(*) FROM tblsubjects");
		return (c.getCount()>0?true:false);
	}
	
	
	
	public void init()
	{
		util=new SQLiteDBUtilities(getActivity());
		View view=this.getView();
		ListView lvl=(ListView)view.findViewById(R.id.subjectListView);
		final ArrayAdapter ad=new SubjectAdapter(getActivity(),GetSingleData(SQLiteParams.SelectQueries[3]),GetSingleData(SQLiteParams.SelectQueries[4]),						 
																			GetSingleData(SQLiteParams.SelectQueries[5]),GetData(SQLiteParams.SelectQueries[6]),
																			GetSingleData(SQLiteParams.SelectQueries[7]),GetSingleData(SQLiteParams.SelectQueries[8]));
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
								util.DeleteQuery("tblsubjects","SUBCODE",SubjectAdapter.subCode.get(pos));
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
		/*else{
			Util.Show(getActivity(),"No subjects");
		}*/
		return lst;
	}

	public List<String> GetData(String query){
	  List<String> lst=new ArrayList<String>();
		Cursor c=util.SelectQuery(query);
		if(c!=null&&c.getCount()>0){
			while(c.moveToNext()) 
			lst.add(c.getString(0)+"-"+c.getString(1));
		}
		/*else{
			Util.Show(getActivity(),"Nooooo");
		}*/
		return lst;
	}
}
