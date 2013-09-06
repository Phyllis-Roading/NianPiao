package net.basilwang.niaopiao;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity {

	private int mTitleRes;
	private View actionbarBack;
	private static View actionbarNav;
	private static TextView tv_title;
	protected ListFragment mFrag;

	public BaseActivity(int titleRes) {
		super();
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTitle(mTitleRes);

		// customize the ActionBar
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		View ABTitleView = getLayoutInflater()
				.inflate(R.layout.title_bar, null);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setCustomView(ABTitleView, lp);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);
		Drawable d = (Drawable) getResources().getDrawable(
				R.drawable.titlebar_whole);
		actionBar.setBackgroundDrawable(d);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);

		initActinBarView();

		setActionbarTitle(mTitleRes);
	}

	private void initActinBarView() {
		tv_title = (TextView) findViewById(R.id.abs_title);
		actionbarBack = (View) findViewById(R.id.abs_left_back);
		actionbarNav = (View) findViewById(R.id.abs_left_nav);
		actionbarNav.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggle();
			}
		});
		actionbarBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getSupportFragmentManager().popBackStack();
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// switch (item.getItemId()) {
		// case android.R.id.home:
		// toggle();
		// return true;
		// case ActionBar.DISPLAY_USE_LOGO:
		// toggle();
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static void setActionbarTitle(int mTitleRes) {
		tv_title.setText(mTitleRes);
	}

	public static void setActionbarTitle(String mTitleRes) {
		tv_title.setText(mTitleRes);
	}

	public static void setActionbarNavVisibility(int visibility) {
		actionbarNav.setVisibility(visibility);
	}
}
