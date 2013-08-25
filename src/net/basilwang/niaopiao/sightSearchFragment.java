package net.basilwang.niaopiao;

import java.util.ArrayList;
import java.util.List;

import net.basilwang.nianpiao.adapter.SightSearchListAdapter;
import net.basilwang.nianpiao.model.SightListItemModel;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class sightSearchFragment extends Fragment {

	private View mView;
	private Context mContext;
	private ListView listView_sight;
	
	private List<SightListItemModel> sightItemModels; 


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (null == mView) {
			mView = inflater.inflate(R.layout.sight_search, container, false);
			listView_sight=(ListView)mView.findViewById(R.id.sightSearchList);
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


}
