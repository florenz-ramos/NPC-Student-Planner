package com.npc.dev.app;
import android.support.v4.app.Fragment;
import android.os.*;
import android.view.*;
import android.net.*;
import android.content.*;
import android.widget.*;
import java.util.*;
import android.database.*;
import android.widget.AdapterView.*;
import android.support.v7.app.*;

public class Tab1 extends Fragment
{
	private static final String a_p1="p1";
	private static final String a_p2="p2";
	private String p1,p2;

	SQLiteDBUtilities util;
	
	private OnFragmentInteractionListener listener;
	public Tab1(){
	
	}
	public static Tab1 newInstance(String p1,String p2){
		Tab1 frag=new Tab1();
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
	  
			View view=inflater.inflate(R.layout.fragment_tab1,container,false);
			
			
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
	
	public List<String> PopulateNotes(String query){
	  List<String> lst=new ArrayList<String>();
		Cursor c=util.SelectQuery(query);
		if(c!=null && c.getCount()>0){
			while(c.moveToNext()){
				lst.add(c.getString(0));
			}
		}
		/*else{
			Toast.makeText(getActivity(),"No Data",Toast.LENGTH_LONG).show();
		}*/
		return lst;
	}
	public static int location;
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onActivityCreated(savedInstanceState);
		util=new SQLiteDBUtilities(getActivity());
		View view=this.getView();
		ListView lvl=(ListView)view.findViewById(R.id.schoolListView);
		final ArrayAdapter ad=new MyAdapter(getActivity(),
		           PopulateNotes(SQLiteParams.SelectQueries[0]),
							 PopulateNotes(SQLiteParams.SelectQueries[1]),
							 PopulateNotes(SQLiteParams.SelectQueries[2])
							 );
		lvl.setAdapter(ad);

		lvl.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					// TODO: Implement this method
					Intent i=new Intent(getActivity(),NoteActivity.class);
				//	i.putExtra("title",MyAdapter.titles.get(p3));
					location=p3;
					startActivity(i);
					getActivity().finish();
					
				}

			
		});
		lvl.setOnItemLongClickListener(new OnItemLongClickListener(){

				@Override
				public boolean onItemLongClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					// TODO: Implement this method
					AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
					adb.setTitle("Message");
					adb.setMessage("Delete Note Successfully");
					//adb.setNegativeButton("Cancel",null);
					final int pos=p3;
					adb.setPositiveButton("Ok", new AlertDialog.OnClickListener(){
							@Override
							public void onClick(DialogInterface p1, int p2){
								util.DeleteQuery("tblNotes","NOTETITLE",MyAdapter.titles.get(pos));
								ad.remove(ad.getItem(pos));
								ad.notifyDataSetChanged();
							}		
						});
					adb.show();
				return true;
				}

			
		});
		
		//Util.Show(getActivity(),""+lvl.getId());

	}
	public boolean hasData(){
		Cursor c=util.SelectQuery("SELECT COUNT(*) FROM tblnotes");
		return (c.getCount()>0?true:false);
	}
	
	
	
	
	
}
