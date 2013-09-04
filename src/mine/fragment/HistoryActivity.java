package mine.fragment;

import com.actionbarsherlock.view.MenuItem;

import net.basilwang.nianpiao.custom.MyListView;
import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.NianPiaoActivity;
import net.basilwang.niaopiao.R;
import br.com.dina.ui.widget.UITableView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryActivity extends TestActivity {

	UITableView historyView;
	MyListView listView;
	HistoryAdapter adapter;

	public HistoryActivity() {
		super(R.string.app_name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_history);
		BaseActivity.setActionbarTitle(R.string.mine_history);
		BaseActivity.setActionbarNavVisibility(View.GONE);
		// setBehindContentView(R.layout.mine);
		listView = (MyListView) findViewById(R.id.listview_history);
		adapter = new HistoryAdapter(this);
		adapter.add(new HistoryItem(R.drawable.sight_img1, "ʪ�ع�԰", "AAAA", "��ɽ�����Ŷ�����·", "160Ԫ"));
		adapter.add(new HistoryItem(R.drawable.sight_img2, "ɭ�ֹ�԰", "AAAA", "��ɽ�����Ŷ�����·", "160Ԫ"));
		listView.setAdapter(adapter);
	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// setTheme(R.style.Theme_Sherlock);
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.mine_history);
	// listView = (MyListView) findViewById(R.id.listview_history);
	// adapter = new HistoryAdapter(this);
	// adapter.add(new HistoryItem(0, "�ൺ��ɳ̲", "5��", "�ൺ�лƵ����ɳ̲", "100Ԫ"));
	// adapter.add(new HistoryItem(0, "�ൺ��ɳ̲", "5��", "�ൺ�лƵ����ɳ̲", "100Ԫ"));
	// listView.setAdapter(adapter);
	//
	// }

	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.mine_history);
	// // listView = (MyListView) findViewById(R.id.listview_history);
	// // adapter = new HistoryAdapter(this);
	// // adapter.add(new HistoryItem(0, "�ൺ��ɳ̲", "5��", "�ൺ�лƵ����ɳ̲", "100Ԫ"));
	// // adapter.add(new HistoryItem(0, "�ൺ��ɳ̲", "5��", "�ൺ�лƵ����ɳ̲", "100Ԫ"));
	// // listView.setAdapter(adapter);
	// }

	// @Override
	// public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu)
	// {
	// // TODO Auto-generated method stub
	// menu.add("���").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	// return super.onCreateOptionsMenu(menu);
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(
	// com.actionbarsherlock.view.MenuItem item) {
	// // TODO Auto-generated method stub
	// adapter.clear();
	// adapter.notifyDataSetChanged();
	// return super.onOptionsItemSelected(item);
	// }

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			switchFragment(new MineFragment());
//			System.out.println("succeed");
//			return true;
//			// case ActionBar.DISPLAY_USE_LOGO:
//			// toggle();
//			// return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	private void switchFragment(Fragment fragment) {

		NianPiaoActivity fca = new NianPiaoActivity();
		fca.switchContent(fragment,1);
	}

	private class HistoryItem {
		public int iconRes;
		public String scenery;
		public String evaluate;
		public String adress;
		public String price;

		public HistoryItem(int iconRes, String scenery, String evaluate,
				String adress, String price) {
			this.iconRes = iconRes;
			this.scenery = scenery;
			this.evaluate = evaluate;
			this.adress = adress;
			this.price = price;
		}
	}

	public class HistoryAdapter extends ArrayAdapter<HistoryItem> {

		public HistoryAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.mine_history_item, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.scenery_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView scenery = (TextView) convertView
					.findViewById(R.id.scenery);
			scenery.setText(getItem(position).scenery);
			TextView evaluate = (TextView) convertView
					.findViewById(R.id.evaluate);
			evaluate.setText(getItem(position).evaluate);
			TextView adress = (TextView) convertView.findViewById(R.id.adress);
			adress.setText(getItem(position).adress);
			TextView price = (TextView) convertView.findViewById(R.id.price);
			price.setText(getItem(position).price);

			return convertView;
		}
	}

}
