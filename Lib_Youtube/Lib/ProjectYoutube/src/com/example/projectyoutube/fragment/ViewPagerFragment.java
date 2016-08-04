package com.example.projectyoutube.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.projectyoutube.MainActivity;
import com.example.projectyoutube.R;
import com.example.projectyoutube.adapter.TabPagerItem;
import com.example.projectyoutube.adapter.ViewPagerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class ViewPagerFragment extends Fragment {
	private List<TabPagerItem> mTabs = new ArrayList<>();
	private Context mContext;
	private TabLayout mSlidingTabLayout;
	private ViewPagerAdapter pageAdapter;
	protected LayoutInflater inflate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = (MainActivity) getActivity();
		createTabPagerItem();
		inflate = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		((MainActivity) mContext).setTypeHomeMenu(0);
	}

	private void createTabPagerItem() {
		mTabs.add(new TabPagerItem(getString(R.string.top),
				Top100Screen.newInstance(getString(R.string.top)),
				R.drawable.topchart));
		mTabs.add(new TabPagerItem(getString(R.string.popular), 
				MainFragment.newInstance(getString(R.string.popular)),
				R.drawable.popular));
		mTabs.add(new TabPagerItem(getString(R.string.top_single),
				MainFragment.newInstance(getString(R.string.top_single)),
				R.drawable.playlist));
		mTabs.add(new TabPagerItem(getString(R.string.my_playlist),
				MainFragment.newInstance(getString(R.string.my_playlist)),
				R.drawable.star));
		mTabs.add(new TabPagerItem(getString(R.string.more),
				MainFragment.newInstance(getString(R.string.more)),
				R.drawable.ic_more));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
		rootView.setLayoutParams(
				new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

		mViewPager.setOffscreenPageLimit(mTabs.size());
		pageAdapter = new ViewPagerAdapter(getChildFragmentManager(), mTabs);
		mViewPager.setAdapter(pageAdapter);
		mSlidingTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mSlidingTabLayout.setElevation(15);
		}
		mSlidingTabLayout.setupWithViewPager(mViewPager);
		setupTabIcons();
	}

	private void setupTabIcons() {
		for (int i = 0; i < mSlidingTabLayout.getTabCount(); i++) {
			TabLayout.Tab tab = mSlidingTabLayout.getTabAt(i);
			if (tab != null)
				tab.setCustomView(pageAdapter.getTabView(inflate, mContext, i));
		}

		mSlidingTabLayout.getTabAt(0).getCustomView().setSelected(true);
	}
}