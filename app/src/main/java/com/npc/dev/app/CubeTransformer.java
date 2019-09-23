package com.npc.dev.app;
import android.support.v4.view.*;
import android.view.*;

public class CubeTransformer implements ViewPager.PageTransformer
{

	@Override
	public void transformPage(View p1, float p2)
	{
		p1.setPivotX(p2<=0?p1.getWidth():0.0f);
		p1.setPivotY(p1.getHeight()*0.5f);
		p1.setRotation(90f*p2);
	}

	
}
