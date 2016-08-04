package com.example.projectyoutube.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import com.example.projectyoutube.R;

public class FragmentControler {
	//https://finfo-api.vndirect.com.vn/industries
	/**
	 * replace new fragment to screen, but don't add to {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param shlFragment
	 */
	public static void replaceDontAddToBackStack(Context mContext, Fragment mFragment) {
		FragmentManager mFragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
		mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
	}

	/**
	 * replace new fragment to screen, and add to {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param psdlFragment
	 * @param nameClass
	 */
	public static void replaceWithAddToBackStack(Context mContext, Fragment mFragment, String nameClass) {
		FragmentManager mFragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
		mFragmentManager.beginTransaction().replace(R.id.container, mFragment, nameClass).addToBackStack(nameClass)
				.commit();
	}

	/**
	 * replace new fragment to screen, and add to {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param psdlFragment
	 * @param nameClass
	 */
	public static void replaceWithAddToBackStackAnimation(Context mContext, Fragment mFragment, String nameClass) {
		FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit,
				R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);
		ft.replace(R.id.container, mFragment, nameClass);
		ft.addToBackStack(nameClass);
		ft.commit();

	}

	/**
	 * replace new fragment to screen, and remove all {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param shlFragment
	 */
	public static void replaceWithPopAllBackStack(Context mContext, Fragment mFragment) {
		((FragmentActivity) mContext).getSupportFragmentManager().popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
		((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment)
				.commit();
	}

}
