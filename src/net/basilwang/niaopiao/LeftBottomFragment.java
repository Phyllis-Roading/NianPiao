package net.basilwang.niaopiao;

import java.util.ArrayList;
import java.util.List;

import net.basilwang.nianpiao.adapter.CommonOrMoreAdapter;
import net.basilwang.nianpiao.adapter.SettingAdapter;
import net.basilwang.nianpiao.model.ItemComOrMoreModel;
import net.basilwang.nianpiao.model.ItemSettingModel;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class LeftBottomFragment extends ListFragment {
	private View mView;
	private Context mContext;
	private ListView listview_common;
	private ListView listview_more;
	private ListView listview_setting;
	

	private List<ItemComOrMoreModel> commonModels; // 常用列表的Item集合
	private List<ItemComOrMoreModel> moreModels; // 更多列表的item集合
	private List<ItemSettingModel> settingModels; // 设置列表的item集合

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (null == mView) {
			mView = inflater.inflate(R.layout.fragment_left_bottom, container,
					false);
			initView();
			initValidata();
			bindData();
//			slidingMenuItemClick();
//			getActivity().getActionBar().setTitle(R.string.sight_search_title);
			
		}
		return mView;
	}

	@Override
	public void onListItemClick(ListView listView, View v, int position, long id) {
		Fragment fragmentContent=null;
		switch (position) {
		case 0:
			fragmentContent = new sightSearchFragment();
			Log.v("onclick", "onclick happend");
			break;
		case 1:
			break;

		default:
			break;
		}
		if (fragmentContent != null)
			switchFragment(fragmentContent);
		
	}

	/**
	 * 初始化界面元素
	 */
	private void initView() {
		listview_common = (ListView) mView.findViewById(android.R.id.list);
		listview_more = (ListView) mView.findViewById(R.id.listview_more);
		listview_setting = (ListView) mView.findViewById(R.id.listview_setting);

	}

	/**
	 * 初始化变量
	 */
	private void initValidata() {
		mContext = mView.getContext();
		commonModels = new ArrayList<ItemComOrMoreModel>();
		moreModels = new ArrayList<ItemComOrMoreModel>();
		settingModels = new ArrayList<ItemSettingModel>();
		// 1:进行构造常用列表中的数据,图标,名称,数量
		Integer[] common_icon_id = new Integer[] {
				R.drawable.v5_2_1_desktop_list_newsfeed,
				R.drawable.v5_2_1_desktop_list_message,
				R.drawable.v5_2_1_desktop_list_chat,
				R.drawable.v5_2_1_desktop_list_friends,
				R.drawable.v5_2_1_desktop_list_search,
				R.drawable.v5_9_3_desktop_list_barcode };
		String[] arrays_commom = mContext.getResources().getStringArray(
				R.array.list_items);
		int[] common_number = new int[] { 0, 1, 2, 3, 4, 1 };
		for (int i = 0; i < common_icon_id.length; i++) {
			ItemComOrMoreModel commcon = new ItemComOrMoreModel(
					common_icon_id[i], arrays_commom[i], common_number[i]);
			commonModels.add(commcon);
		}

		// 2：进行构造更多列表中的数据,图标,名称,数量
		/*
		 * Integer[] more_icon_id=new Integer[]
		 * {R.drawable.v5_2_1_desktop_list_location
		 * ,R.drawable.v5_2_1_desktop_list_page
		 * ,R.drawable.v5_2_0_desktop_list_hot
		 * ,R.drawable.v5_2_1_desktop_list_apps_center}; String[]
		 * arrays_more=mContext
		 * .getResources().getStringArray(R.array.list_items); int[]
		 * more_number=new int[]{0,0,0,0}; for(int
		 * i=0;i<more_icon_id.length;i++) { ItemComOrMoreModel more=new
		 * ItemComOrMoreModel(more_icon_id[i],arrays_more[i],more_number[i]);
		 * moreModels.add(more); }
		 * 
		 * //3:进行构造设置列表中的数据,图标,名称 Integer[] setting_icon_id=new
		 * Integer[]{R.drawable
		 * .v_5_8day_mode_unselected,R.drawable.v5_2_1_desktop_list_settings
		 * ,R.drawable.v5_2_1_desktop_list_log_out}; String[]
		 * arrays_setting=mContext
		 * .getResources().getStringArray(R.array.list_items); for(int
		 * i=0;i<setting_icon_id.length;i++) { ItemSettingModel setting=new
		 * ItemSettingModel(setting_icon_id[i],arrays_setting[i]);
		 * settingModels.add(setting); }
		 */
	}

	/**
	 * 绑定数据
	 */
	private void bindData() {
		// 创建适配器并且进行绑定数据到listview中
		listview_common.setAdapter(new CommonOrMoreAdapter(mContext,
				commonModels));
		listview_more.setAdapter(new CommonOrMoreAdapter(mContext, moreModels));
		listview_setting
				.setAdapter(new SettingAdapter(mContext, settingModels));
	}

	private void switchFragment(Fragment mContent2) {
		if (getActivity() == null)
			return;
		if (getActivity() instanceof NianPiaoActivity) {
			NianPiaoActivity ma = (NianPiaoActivity)getActivity();
			ma.switchContent(mContent2);
		}
	}

}