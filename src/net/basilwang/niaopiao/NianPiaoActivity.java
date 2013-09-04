package net.basilwang.niaopiao;

import net.basilwang.utils.NetworkUtils;
import mayi.slidingmenudemo.SlideMenuFragment;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class NianPiaoActivity extends BaseActivity {

	private Fragment mContent;

	public NianPiaoActivity() {
		super(R.string.app_name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

		initSlidingMenu(savedInstanceState);
		checkNetwork();
	}

	private void initSlidingMenu(Bundle savedInstanceState) {
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new sightSearchFragment();
		//设置内容视图
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();
		//设置滑动菜单视图
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new SlideMenuFragment()).commit();

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	public void switchContent(Fragment fragment, int flag) {
		mContent = fragment;
		if (flag == 1) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, mContent).addToBackStack(null)
					.commit();
		} else {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, fragment).commit();
			//如果Fragment是由滑动菜单切换的，则将BackStack清空
			getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
		getSlidingMenu().showContent();
	}

	private void checkNetwork() {
		if (!NetworkUtils.isConnect(this))
			Toast.makeText(this, "请检查网络是否连接", Toast.LENGTH_LONG).show();
	}

}
