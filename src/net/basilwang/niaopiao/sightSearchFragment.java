package net.basilwang.niaopiao;

import java.util.ArrayList;
import java.util.List;

import net.basilwang.nianpiao.adapter.SightSearchListAdapter;
import net.basilwang.nianpiao.model.SightListItemModel;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

public class sightSearchFragment extends Fragment implements OnClickListener {

	private View mView;
	private Context mContext;
	private ListView listView_sight;
	private PopupMenu popMenu;
	private PopupWindow popWindow;
	
	private List<SightListItemModel> sightItemModels; 


	@Override
	public View getView() {
		// TODO Auto-generated method stub
		Context np=(NianPiaoActivity)getActivity();
		
		return super.getView();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (null == mView) {
			mView = inflater.inflate(R.layout.sight_search, container, false);
			initView();
			initValidata();
			bindData();
		}
		
		listView_sight.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
			
		});

		return mView;
	}


	private void initView() {
		listView_sight=(ListView)mView.findViewById(R.id.sightSearchList);
		mView.findViewById(R.id.sight_search_bottom_left).setOnClickListener(this);
		View popView=null;
		popWindow=new PopupWindow(popView);
		popWindow.setFocusable(true);
		popWindow.setOutsideTouchable(true);
		popWindow.update();
		popWindow.setBackgroundDrawable(new BitmapDrawable());
		popMenu=new PopupMenu(this.getActivity(), popView);
		popMenu.inflate(R.menu.recommend_menu);
	}


	private void initValidata() {
		// TODO Auto-generated method stub
		mContext=mView.getContext();
		sightItemModels=new ArrayList<SightListItemModel>();
		Integer[] sight_list_item_img = new Integer[] {
				R.drawable.sight_img1,
				R.drawable.sight_img2,
				R.drawable.sight_img3,
				R.drawable.sight_img4};
		String[] sight_list_title = mContext.getResources().getStringArray(
				R.array.sight_list_title);
		String[] sight_list_item_price = mContext.getResources().getStringArray(
				R.array.sight_list_item_price);
		String[] sight_list_item_level = mContext.getResources().getStringArray(
				R.array.sight_list_item_level);
		String[] sight_list_item_discount = mContext.getResources().getStringArray(
				R.array.sight_list_item_discount);
		String[] sight_list_item_distance = mContext.getResources().getStringArray(
				R.array.sight_list_item_distance);
		for (int i = 0; i < sight_list_item_img.length; i++) {
			SightListItemModel ListItem = new SightListItemModel(sight_list_item_img[i],
					sight_list_title[i],sight_list_item_level[i], sight_list_item_price[i],sight_list_item_discount[i], sight_list_item_distance[i]);
			sightItemModels.add(ListItem);
		}
	}
	
	private void bindData() {
		// 创建适配器并且进行绑定数据到listview中
		listView_sight.setAdapter(new SightSearchListAdapter(mContext,
				sightItemModels));
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.sight_search_main, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sight_search_bottom_left:
			popWindow.showAsDropDown(v, 0, -80);
			Log.v("left is clicked", "wefdkewnfkremkl");
			break;

		default:
			break;
		}
	}


}
