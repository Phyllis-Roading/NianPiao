package mine.fragment;

import br.com.dina.ui.widget.UIButton;
import br.com.dina.ui.widget.UIButton.ClickListener;
import br.com.dina.ui.widget.UITableView;
import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import net.basilwang.niaopiao.R.layout;
import net.basilwang.niaopiao.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class InformationActivity extends TestActivity {

	UIButton icon;
	UITableView information;
	UIButton more;

	public InformationActivity() {
		super(R.string.app_name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_information);
		BaseActivity.setActionbarTitle(R.string.mine_edit);
		BaseActivity.setActionbarNavVisibility(View.GONE);
		
		icon = (UIButton) findViewById(R.id.mine_icon);
		more = (UIButton) findViewById(R.id.mine_more);
		information = (UITableView) findViewById(R.id.mine_information);
		icon.addClickListener(new ClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				System.out.println("succeed///////");
				Intent i=new Intent(InformationActivity.this, IconUploadActivity.class);
				startActivity(i);
			}
		});
		// more.addClickListener(this);
		initTableView();
		// information.setClickListener(new ClickListener() {
		//
		// @Override
		// public void onClick(View view) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

	}

	private void initTableView() {
		// TODO Auto-generated method stub
		information.addBasicItem("昵称");
		information.addBasicItem("性别");
		information.addBasicItem("生日");
		information.addBasicItem("电话号码");
		information.commit();
	}

//	@Override
//	public void onClick(View view) {
//		// TODO Auto-generated method stub
//		switch (view.getId()) {
//		case R.id.mine_icon:
//			System.out.println("succeed///////");
//			Intent i=new Intent(this, IconUploadActivity.class);
//			startActivity(i);
//			break;
//		case R.id.mine_more:
//			break;
//		}
//	}
}
