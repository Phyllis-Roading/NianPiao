package mine.fragment;

import net.basilwang.niaopiao.R;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

public class CollectActivity extends SherlockActivity implements
		ActionBar.TabListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_collect);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (int i = 0; i < 3; i++) {
			ActionBar.Tab tab = getSupportActionBar().newTab();
			setText(i, tab);
			tab.setTabListener(this);
			getSupportActionBar().addTab(tab);
		}
	}

	private void setText(int i, ActionBar.Tab tab) {
		// TODO Auto-generated method stub
		switch (i) {
		case 0:
			tab.setText("¾°µã");
			break;
		case 1:
			tab.setText("¹¥ÂÔ");
			break;
		case 2:
			tab.setText("ÏßÂ·");
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("±à¼­").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return super.onCreateOptionsMenu(menu);
	}
	

}
