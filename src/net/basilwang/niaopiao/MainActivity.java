package net.basilwang.niaopiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

	private ImageButton imgbtn_top_left;
	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ��ʼ�������˵�
		
		
		setContentView(R.layout.activity_main);
		initSlidingMenu(savedInstanceState);
		imgbtn_top_left = (ImageButton) this.findViewById(R.id.imgbtn_top_left);
		imgbtn_top_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toggle();// ����SlidingMenu�Ĵ���ر�
			}
		});
		
	}

	private void initSlidingMenu(Bundle savedInstanceState) {
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new sightSearchFragment();

		//����������ͼ
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.content_frame, mContent).commit();
		// ���û����˵�����ͼ
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LeftBottomFragment()).commit();
		// ʵ���������˵�����
		SlidingMenu sm = getSlidingMenu();
		// ���û�����Ӱ�Ŀ��
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// ���û�����Ӱ��ͼ����Դ
		sm.setShadowDrawable(R.drawable.shadow);
		// ���û����˵���ͼ�Ŀ��
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// ���ý��뽥��Ч����ֵ
		sm.setFadeDegree(0.35f);
		// ���ô�����Ļ��ģʽ
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
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
