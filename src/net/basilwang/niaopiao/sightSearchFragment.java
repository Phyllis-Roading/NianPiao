package net.basilwang.niaopiao;

import java.util.ArrayList;
import java.util.List;

import net.basilwang.nianpiao.adapter.SightSearchListAdapter;
import net.basilwang.nianpiao.model.SightListItemModel;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

public class sightSearchFragment extends ListFragment implements
		OnClickListener {

	private View mView;
	private Context mContext;
	private ListView listView_sight;
	private ListView listView_recommend;
	private LinearLayout layout;
	private PopupWindow popWindow;
	private String title[] = { "默认排序", "按人气", "按价格" };

	private List<SightListItemModel> sightItemModels;

	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (null == mView) {
			mView = inflater.inflate(R.layout.sight_search, container, false);
			initView();
			initValidata();
			bindData();
		}
		return mView;
	}

	private void initView() {
		listView_sight = (ListView) mView.findViewById(android.R.id.list);
		
		mView.findViewById(R.id.sight_search_bottom_left).setOnClickListener(
				this);
		layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
				R.layout.recommend_list, null);
		listView_recommend = (ListView) layout
				.findViewById(R.id.recommend_list);
		listView_recommend.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:

					Log.v("recommend item0 is clicked", "默认排序被电击了	");
					break;

				default:
					break;
				}
				popWindow.dismiss();
			}
		});
	}

	private void showpopupWindow(View view, int width) {
		popWindow = new PopupWindow(getActivity());
		popWindow.setBackgroundDrawable(new BitmapDrawable());
		popWindow.setWidth(width - 5);
		popWindow.setHeight(340);
		popWindow.setOutsideTouchable(true);
		popWindow.setFocusable(true);
		popWindow.setContentView(layout);
		popWindow.showAsDropDown(view, 0, -290);

	}

	private void initValidata() {
		mContext = mView.getContext();
		sightItemModels = new ArrayList<SightListItemModel>();
		Integer[] sight_list_item_img = new Integer[] { R.drawable.sight_img1,
				R.drawable.sight_img2, R.drawable.sight_img3,
				R.drawable.sight_img4 };
		String[] sight_list_title = mContext.getResources().getStringArray(
				R.array.sight_list_title);
		String[] sight_list_item_price = mContext.getResources()
				.getStringArray(R.array.sight_list_item_price);
		String[] sight_list_item_level = mContext.getResources()
				.getStringArray(R.array.sight_list_item_level);
		String[] sight_list_item_discount = mContext.getResources()
				.getStringArray(R.array.sight_list_item_discount);
		String[] sight_list_item_distance = mContext.getResources()
				.getStringArray(R.array.sight_list_item_distance);
		for (int i = 0; i < sight_list_item_img.length; i++) {
			SightListItemModel ListItem = new SightListItemModel(
					sight_list_item_img[i], sight_list_title[i],
					sight_list_item_level[i], sight_list_item_price[i],
					sight_list_item_discount[i], sight_list_item_distance[i]);
			sightItemModels.add(ListItem);
		}
	}

	private void bindData() {
		// 创建适配器并且进行绑定数据到listview中
		listView_sight.setAdapter(new SightSearchListAdapter(mContext,
				sightItemModels));
		listView_recommend.setAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.recommend_list_item, R.id.recommend_list_item, title));
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.sight_search_main, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sight_search_bottom_left:
			showpopupWindow(v, v.getWidth());
			Log.v("left is clicked", "wefdkewnfkremkl");
			break;
		case R.id.sight_search_bottom_center:
			swapBackground();
			break;
		case R.id.sight_search_bottom_right:
			break;
		default:
			break;
		}
	}

	private void swapBackground() {
		
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		switch (position) {
		case 0:
			Log.v("sight item0 is clicked", "湿地公园被电击了	");
			break;

		default:
			break;
		}
		super.onListItemClick(listView, view, position, id);
	}

}
