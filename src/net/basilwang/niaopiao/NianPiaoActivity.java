package net.basilwang.niaopiao;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;




public class NianPiaoActivity extends BaseActivity {


	private Fragment mContent;
	
	public NianPiaoActivity() {
		super(R.string.app_name);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
//		setContentView(R.layout.activity_main);
		initSlidingMenu(savedInstanceState);
		
		//添加自定义的ActionBar,还未成功
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
		
		
//		Drawable d=(Drawable)getResources().getDrawable(R.drawable.titlebar_whole);
//		ActionBar actionbar=getSupportActionBar();
//		actionbar.setBackgroundDrawable(d);
//		actionbar.setLogo(R.drawable.btn_back);
//		actionbar.setDisplayShowHomeEnabled(true);
//		actionbar.setHomeButtonEnabled(true);
		
		
		View view = (View)findViewById(R.id.abs_left);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggle();
			}
		});
		
	}

	private void initSlidingMenu(Bundle savedInstanceState) {
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new sightSearchFragment();

		//设置内容试图
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.content_frame, mContent).commit();
		// 设置滑动菜单的视图
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LeftBottomFragment()).commit();
		
	}
	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
////		case R.id.abs_left:
////			toggle();
////			break;
//
//		default:
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
	}

}
