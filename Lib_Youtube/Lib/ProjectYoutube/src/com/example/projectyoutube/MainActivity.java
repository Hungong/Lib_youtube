package com.example.projectyoutube;

import com.example.projectyoutube.fragment.ViewPagerFragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
	private Toolbar mToolbar;
	protected int typeHomeMenu;
	private float mElevationToolBar = 15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		this.setSupportActionBar(mToolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeButtonEnabled(true);
		}
		setTypeHomeMenu(0);
		initMainHome();
	}
	
	public void initMainHome(){
		Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragment = new ViewPagerFragment();
        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

	}

	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (typeHomeMenu == 1 && item.getItemId() == android.R.id.home) {
			if (getSupportFragmentManager().getBackStackEntryCount() > 0)
				getSupportFragmentManager().popBackStack();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * public void setElevation (float elevation) Added in API level 21 Default
	 * value is 15
	 * 
	 * @param elevation
	 *            Sets the base elevation of this view, in pixels.
	 */
	public void setElevationToolBar(float elevation) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			this.mElevationToolBar = elevation;
			this.getToolbar().setElevation(elevation);
		}
	}

	public Toolbar getToolbar() {
		return this.mToolbar;
	}

	public void setTypeHomeMenu(int typeHomeMenu) {
		this.typeHomeMenu = typeHomeMenu;
		ActionBar actionBar = getSupportActionBar();
		if (typeHomeMenu == 0) {
			if (actionBar != null) {

				actionBar.setDisplayHomeAsUpEnabled(true);
				actionBar.setHomeButtonEnabled(true);

				if (mToolbar != null){
					mToolbar.setNavigationIcon(R.color.transparent);
				}
			}
		} else {
			if (actionBar != null) {
				if (mToolbar != null)
					mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
			}
		}
	}
}
