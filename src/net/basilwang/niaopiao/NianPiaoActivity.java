package net.basilwang.niaopiao;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageButton;


public class NianPiaoActivity extends BaseActivity {


	private ImageButton imgbtn_top_left;
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
//		imgbtn_top_left = (ImageButton) this.findViewById(R.id.imgbtn_top_left);
//		imgbtn_top_left.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				toggle();// 尽心SlidingMenu的打开与关闭
//			}
//		});
		
		//添加自定义的ActionBar
//		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
//				ActionBar.LayoutParams.MATCH_PARENT,
//				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
//		View ABTitleView=getLayoutInflater().inflate(R.layout.activity_main,
//				null);
//		ActionBar actionBar=getActionBar();
//		actionBar.setCustomView(ABTitleView,lp);
//		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//		actionBar.setDisplayShowCustomEnabled(true);
		
		getActionBar().setIcon(R.drawable.nav_left);
		Drawable d=(Drawable)getResources().getDrawable(R.drawable.titlebar_whole);
		getActionBar().setBackgroundDrawable(d);
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
