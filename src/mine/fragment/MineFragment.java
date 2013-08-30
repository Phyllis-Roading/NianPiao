package mine.fragment;

import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MineFragment extends Fragment implements OnClickListener {

	View mineView;
	UITableView tableView;
	ImageView icon;
	TextView name;
	TextView integral;
	Button edit;
	Button strategy;
	Button gone;
	Button want;
	Button friend;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		BaseActivity.setActionbarTitle(R.string.mine_title);
		BaseActivity.setActionbarNavVisibility(View.VISIBLE);
		
		mineView = inflater.inflate(R.layout.mine, container, false);
		initwidget();
		edit.setOnClickListener(this);
		strategy.setOnClickListener(this);
		gone.setOnClickListener(this);
		want.setOnClickListener(this);
		friend.setOnClickListener(this);
		initTableView();
		tableView.setClickListener(new ClickListener() {

			@Override
			public void onClick(int index) {
				// TODO Auto-generated method stub
				clickTableItem(index);
			}
		});
		return mineView;
	}

	private void initwidget() {
		// TODO Auto-generated method stub
		tableView = (UITableView) mineView.findViewById(R.id.mineitem_view);
		icon = (ImageView) mineView.findViewById(R.id.icon);
		name = (TextView) mineView.findViewById(R.id.name);
		integral = (TextView) mineView.findViewById(R.id.integral);
		edit = (Button) mineView.findViewById(R.id.edit);
		strategy = (Button) mineView.findViewById(R.id.strategy);
		gone = (Button) mineView.findViewById(R.id.gone);
		want = (Button) mineView.findViewById(R.id.want);
		friend = (Button) mineView.findViewById(R.id.friend);
	}

	private void initTableView() {
		// TODO Auto-generated method stub
		tableView.addBasicItem(R.drawable.mine_comment, "我的订单", null);
		tableView.addBasicItem(R.drawable.mine_favorite, "我的收藏", null);
		tableView.addBasicItem(R.drawable.mine_browse_history, "浏览历史", null);
		tableView.commit();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		switch (v.getId()) {
		case R.id.edit:
			i = new Intent(MineFragment.this.getActivity(), InformationActivity.class);
			startActivity(i);
			// switchFragment(new ViewFragment());
			System.out.println("click");
			break;
		case R.id.strategy:
			// i = new Intent(this.getActivity(), ViewFragment.class);
			System.out.println("click");
			break;
		case R.id.gone:
			// i = new Intent(this.getActivity(), ViewFragment.class);
			break;
		case R.id.want:
			// i = new Intent(this.getActivity(), ViewFragment.class);
			System.out.println("click");
			break;
		case R.id.friend:
			// i = new Intent(this.getActivity(), ViewFragment.class);
			break;
		}

	}

	private void clickTableItem(int index) {
		// TODO Auto-generated method stub
		System.out.println("click" + index);
		Intent intent = new Intent();
		switch (index) {
		case 0:
			// intent=new Intent(MineFragment.this.getActivity(),
			// ViewFragment.class);
			// switchFragment(new ViewFragment());
			break;
		case 1:
			// intent = new Intent(MineFragment.this.getActivity(),
			// CollectActivity.class);
			// startActivity(intent);
			break;
		case 2:
			intent = new Intent(MineFragment.this.getActivity(),
					HistoryActivity.class);
			startActivity(intent);
			break;
		}

	}

	// private void switchFragment(Fragment fragment) {
	// if (getActivity() == null)
	// return;
	// if (getActivity() instanceof MainActivity) {
	// MainActivity fca = (MainActivity) getActivity();
	// fca.switchContent(fragment);
	// }
	// }
}
