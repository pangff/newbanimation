package com.pffair.newbanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	public void myClick(View view){
		Intent itent = new Intent();
		itent.setClass(this, SecondActivity.class);
		startActivity(itent);
	}
}
