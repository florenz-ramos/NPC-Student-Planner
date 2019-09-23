package com.npc.dev.app;
import android.content.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class Tab2 extends Fragment
{
	private static final String a_p1="p1";
	private static final String a_p2="p2";
	private String p1,p2;
	SQLiteDBUtilities util;
	
	//TextView tv;
	private OnFragmentInteractionListener listener;
	public Tab2(){

	}
	public static Tab2 newInstance(String p1,String p2){
		Tab2 frag=new Tab2();
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
	  View view =inflater.inflate(R.layout.fragment_tab2,container,false);
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
		try{
		
		util=new SQLiteDBUtilities(getActivity());
		View view=this.getView();
		ListView lvl=(ListView)view.findViewById(R.id.pNoteListView);
		final ArrayAdapter ad=new PNoteAdapter(getActivity(),
		                    PopulateNotes("SELECT NOTETITLE from tblpnote"),
												PopulateNotes("SELECT NOTECONTENT from tblpnote"),
												PopulateNotes("SELECT NOTEDATE from tblpnote")
												);
		lvl.setAdapter(ad);
		}
		catch(Exception ex){Util.Show(getActivity(),ex.getMessage());}
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
	

}
