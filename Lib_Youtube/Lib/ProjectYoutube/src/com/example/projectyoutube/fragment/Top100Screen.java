/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.projectyoutube.fragment;

import com.example.projectyoutube.MainActivity;
import com.example.projectyoutube.R;
import com.example.projectyoutube.util.BaseFragment;
import com.example.projectyoutube.util.FragmentControler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Top100Screen extends BaseFragment {

	private boolean mSearchCheck;
	private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
	private Button btnExample;

	public static Top100Screen newInstance(String text) {
		Top100Screen mFragment = new Top100Screen();
		Bundle mBundle = new Bundle();
		mBundle.putString(TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (containerView == null) {
			containerView = inflate.inflate(R.layout.top_screen, null);
			containerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			btnExample = (Button) containerView.findViewById(R.id.button1);
			btnExample.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Fragment two = TwoFragment.newInstance("Two screen");
					FragmentControler.replaceWithAddToBackStackAnimation(getActivity(), two,
							TwoFragment.class.toString());
				}
			});
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Context mContext = (MainActivity) getActivity();
		ActionBar actionBar = ((MainActivity) mContext).getSupportActionBar();
		actionBar.setTitle("One fragment");
		((MainActivity) mContext).setTypeHomeMenu(0);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu, menu);

		// Select search item
		final MenuItem menuItem = menu.findItem(R.id.menu_search);
		menuItem.setVisible(true);

		SearchView searchView = (SearchView) menuItem.getActionView();
		searchView.setQueryHint(this.getString(R.string.search));

		((EditText) searchView.findViewById(R.id.search_src_text))
				.setHintTextColor(getResources().getColor(R.color.nliveo_white));
		searchView.setOnQueryTextListener(onQuerySearchView);

		menu.findItem(R.id.menu_add).setVisible(true);

		mSearchCheck = false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case R.id.menu_add:
			Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
			break;

		case R.id.menu_search:
			mSearchCheck = true;
			Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}

	private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
		@Override
		public boolean onQueryTextSubmit(String s) {
			return false;
		}

		@Override
		public boolean onQueryTextChange(String s) {
			if (mSearchCheck) {
				// implement your search here
			}
			return false;
		}
	};
}
