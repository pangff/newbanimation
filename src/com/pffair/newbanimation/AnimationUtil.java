package com.pffair.newbanimation;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;

public class AnimationUtil {
	
	private  static AnimationUtil animationUtil;
	
	public static int DEFAULT_STEP = 30;
	
	private AnimationUtil() {
		
	}
	
	public static AnimationUtil getInstance(){
		if(animationUtil==null){
			animationUtil = new AnimationUtil();
		}
		return animationUtil;
	}
	
	List<View> animationViews = new ArrayList<View>();
	
	public interface OnActivityAnimationListener{
		
		public void onAnimationStart(Animation animation);
		
		public void onAnimationEnd(Animation animation);
	}
	
	public void doActivityTransitAnimation(View root){
		AlphaAnimation an = new AlphaAnimation(0.2f, 1f);
		an.setDuration(2000);
		root.startAnimation(an);
	}
	
	public void doActivityInFromRightAnimation(List<View> views,final OnActivityAnimationListener onActivityAnimationListener){
		stopAllAnimation();
		animationViews.addAll(views);
		for(int i=0;i<animationViews.size();i++){
			AnimationSet aset = AnimationHelper.getActivityInAnimtion(DEFAULT_STEP*i);
			animationViews.get(i).startAnimation(aset);
			if(i==0){
				aset.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						if(onActivityAnimationListener!=null){
							onActivityAnimationListener.onAnimationStart(animation);
						}
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						
					}
				});
			}
			if(i==animationViews.size()-1){
				aset.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						if(onActivityAnimationListener!=null){
							onActivityAnimationListener.onAnimationEnd(animation);
						}
					}
				});
			}
		}
	}
	
	public void stopAllAnimation(){
		for(int i=0;i<animationViews.size();i++){
			animationViews.get(i).clearAnimation();
		}
		animationViews.clear();
	}
	
	public void releaseAnimation(){
		stopAllAnimation();
	}

}
