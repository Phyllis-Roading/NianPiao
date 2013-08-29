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
	private static TextView tv_title;
	protected ListFragment mFrag;

	public BaseActivity(int titleRes) {
		super();
		mTitleRes = titleRes;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(mTitleRes);
		
		// customize the ActionBar
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		View ABTitleView=getLayoutInflater().inflate(R.layout.title_bar,
				null);
		ActionBar actionBar=getSupportActionBar();
		actionBar.setCustomView(ABTitleView,lp);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);
		Drawable d=(Drawable)getResources().getDrawable(R.drawable.titlebar_whole);
		actionBar.setBackgroundDrawable(d);
		actionBar.setHomeButtonEnabled(true);
		
		View view = (View)findViewById(R.id.abs_left);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggle();
			}
		});
		tv_title=(TextView)findViewById(R.id.abs_title);
		setActionbarTitle(mTitleRes);
//		mtv.setText("sdcdsvdfs");

//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//		getActionBar().setDisplayShowTitleEnabled(true);
//		getActionBar().setBackgroundDrawable(d)
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
//		case ActionBar.DISPLAY_USE_LOGO:
//			toggle();
//			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static void setActionbarTitle(int mTitleRes){
		tv_title.setText(mTitleRes);
	}
	
	public static void setActionbarTitle(String mTitleRes){
		tv_title.setText(mTitleRes);
	}
}
