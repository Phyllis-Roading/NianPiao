package net.basilwang.niaopiao;

import android.os.Bundle;

public class SightItemActivity extends BaseActivity {

	public SightItemActivity() {
		super(R.string.app_name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sight_detail_page);
		setBehindContentView(R.layout.left_bottom_top);
	}
	
	

}
