package com.pffair.newbanimation;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.pffair.newbanimation.AnimationUtil.OnActivityAnimationListener;
import com.pffair.newbanimation.animationlist.ArrayAdapter;
import com.pffair.newbanimation.animationlist.SwingRightInAnimationAdapter;

public class SecondActivity extends Activity {

	Button abutton;
	Button abutton2;
	Button abutton3;
	Button abutton4;
	Animation shake;
	SwingRightInAnimationAdapter mAnimateAdditionAdapter;
	ListView listView;
	
	
	public static ArrayList<Integer> getItems() {
		ArrayList<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			items.add(i);
		}
		return items;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		AnimationUtil.getInstance().doActivityTransitAnimation(findViewById(R.id.rootView));
		
		abutton = (Button) findViewById(R.id.abutton);
		abutton2 = (Button) findViewById(R.id.abutton2);
		abutton3 = (Button) findViewById(R.id.abutton3);
		abutton4 = (Button) findViewById(R.id.abutton4);
		
		listView = (ListView) findViewById(R.id.listView);
		mAnimateAdditionAdapter = new SwingRightInAnimationAdapter(new MyListAdapter(this, getItems()));
        mAnimateAdditionAdapter.setAbsListView(listView);

        listView.setAdapter(mAnimateAdditionAdapter);
        
        final List<View> animationList = new ArrayList<>();
        animationList.add(abutton);
        animationList.add(abutton2);
        animationList.add(abutton3);
        animationList.add(abutton4);
        animationList.add(listView);
		abutton.post(new Runnable() {
			
			@Override
			public void run() {
				AnimationUtil.getInstance().doActivityInFromRightAnimation(animationList,new OnActivityAnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						Log.e("ddddd", "动画开始");
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						Log.e("ddddd", "动画结束");
					}
				});
			}
		});
	}
	
	private static class MyListAdapter extends ArrayAdapter<Integer> {

		private final Context mContext;

		public MyListAdapter(final Context context, final ArrayList<Integer> items) {
			super(items);
			mContext = context;
		}

		@Override
		public long getItemId(final int position) {
			return getItem(position).hashCode();
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getView(final int position, final View convertView, final ViewGroup parent) {
			TextView tv = (TextView) convertView;
			if (tv == null) {
				tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.list_row, parent, false);
			}
			tv.setText("This is row number " + getItem(position));
			return tv;
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		AnimationUtil.getInstance().releaseAnimation();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		AnimationUtil.getInstance().releaseAnimation();
	}
}
