package mine.fragment;

import java.util.ArrayList;
import java.util.List;

import net.basilwang.nianpiao.adapter.SightSearchListAdapter;
import net.basilwang.nianpiao.custom.MyListView;
import net.basilwang.nianpiao.model.SightListItemModel;
import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HistoryFragment extends ListFragment {

	private MyListView listView;
	private View historyView;
	private Context mContext;
	private List<SightListItemModel> historyItemModels;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		BaseActivity.setActionbarTitle(R.string.mine_history);
		BaseActivity.setActionbarNavVisibility(View.GONE);
		if (historyView == null) {
			historyView = inflater.inflate(R.layout.mine_history, null);
			initView();
			initValidata();
			bindData();
		}
		return historyView;

	}

	private void initValidata() {
		mContext = historyView.getContext();
		historyItemModels = new ArrayList<SightListItemModel>();
		for (int i = 0; i < 2; i++) {
			SightListItemModel ListItem = new SightListItemModel(
					R.drawable.sight_img1, "森林公园", "AAAA", "100", "4.5",
					"燕山立交桥二环东路", "one");
			historyItemModels.add(ListItem);
		}
	}

	private void initView() {
		listView = (MyListView) historyView.findViewById(android.R.id.list);
	}

	private void bindData() {
		listView.setAdapter(new SightSearchListAdapter(mContext,
				historyItemModels));
	}

}
