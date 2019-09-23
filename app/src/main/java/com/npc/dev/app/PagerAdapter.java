package com.npc.dev.app;
import android.support.v4.app.*;

public class PagerAdapter extends FragmentStatePagerAdapter
{

	@Override
	public Fragment getItem(int p1)
	{
		switch(p1){
			case 0:
				Tab1 t1=new Tab1();
				return t1;
				case 1:
					Tab2 t2=new Tab2();
					return t2;
					case 2:
						Tab3 t3=new Tab3();
						return t3;
						case 3:
							Tab4 t4=new Tab4();
							return t4;
			default:
			return null;
		}
	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return this.nOfTabs;
	}

	int nOfTabs;
	public PagerAdapter(FragmentManager fm,int numOfTabs){
		super(fm);
		this.nOfTabs=numOfTabs;
	}
	
}
