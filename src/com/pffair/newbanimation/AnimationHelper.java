package com.pffair.newbanimation;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

public class AnimationHelper {
	
	private  static AnimationHelper animationHelper;
	
	public static int DEFAULT_DURATION = 800;
	public static int SHAKE_DURATION = 200;
	
	private AnimationHelper() {
	}
	
	public static AnimationHelper getInstance(){
		if(animationHelper==null){
			animationHelper = new AnimationHelper();
		}
		return animationHelper;
	}
	
	List<View> animationViews = new ArrayList<View>();
	
	public static TranslateAnimation getTranslateFromRightToLeftAnimtion(){
		
		return new TranslateAnimation(Animation.RELATIVE_TO_PARENT,  
                4.0f, Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,  
                0.0f); 
	}
	
	public static TranslateAnimation getTranslateFromLeftToRightAnimtion(){
		
		return new TranslateAnimation(Animation.RELATIVE_TO_PARENT,  
                -4.0f, Animation.RELATIVE_TO_PARENT, 0.0f,  
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,  
                0.0f); 
	}
	
	public static Animation getShakeAnimtion(){
		TranslateAnimation shake =  new TranslateAnimation(Animation.ABSOLUTE,  
                -40.0f, Animation.ABSOLUTE, 0.0f,  
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,  
                0.0f); 
		shake.setInterpolator(new OvershootInterpolator());
		shake.setDuration(SHAKE_DURATION);
		return shake;
	}
	
	public static AnimationSet getActivityInAnimtion(int startOffset){
		AnimationSet animationSet = new AnimationSet(false);
		TranslateAnimation tran = getTranslateFromRightToLeftAnimtion();
		Animation shake = getShakeAnimtion();
		tran.setDuration(DEFAULT_DURATION);
		shake.setStartOffset(DEFAULT_DURATION);
		animationSet.addAnimation(tran);
		animationSet.addAnimation(shake);
		animationSet.setStartOffset(startOffset);
		animationSet.setInterpolator(new AccelerateInterpolator());
		return animationSet;
	}

}
