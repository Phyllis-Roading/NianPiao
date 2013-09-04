package mayi.slidingmenudemo;

import tk.sdxuyan.AMap.RouteFragment;
import mine.fragment.MineFragment;

import net.basilwang.niaopiao.NianPiaoActivity;
import net.basilwang.niaopiao.R;
import net.basilwang.niaopiao.sightSearchFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SlideMenuFragment extends ListFragment implements OnClickListener{

	private View listView;
	private View img_icon_top;
	private int newBusMessages=3;//閺傛壆娈戦崝銊︼拷娣団剝浼呴弶鈩冩殶閿涘矁浠堢純鎴ｅ箯閸欙拷	
	private int[] messageNumImage={0,R.drawable.message1,
			R.drawable.message2,R.drawable.message3,
			R.drawable.message4,R.drawable.message5,
			R.drawable.message6};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		listView=inflater.inflate(R.layout.left_bottom_top, null);
		return listView;
	}

	public void prepare(){
		img_icon_top=(View)listView.findViewById(R.id.margin);
//		img_icon_top.setOnClickListener(this);
		img_icon_top=(ImageView)listView.findViewById(R.id.img_icon_top);
		img_icon_top.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Log.v("view","click");
//				Fragment newContent = null;
////				newContent = new ColorFragment(R.color.blue);
//				if (newContent != null)
//					switchFragment(newContent);
			}
		});
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] menuNames = getResources().getStringArray(R.array.menu_items);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < menuNames.length; i++) {
			adapter.add(new SampleItem(menuNames[i], getIconResc(i),getMessage_IconResc(i)));
			setListAdapter(adapter);
		}
	}
	private int getIconResc(int position) {
		int[] iconResc = {
				R.drawable.menu_search,
				R.drawable.menu_purchase, 
				R.drawable.menu_road,
				R.drawable.menu_youji,
				R.drawable.menu_bus,
				R.drawable.menu_setting,
				R.drawable.menu_help,
				R.drawable.ic_launcher};
		return iconResc[position];
	}
	private int getMessage_IconResc(int position) {
		int[] iconResc = {
				0,
				0, 
				0,
				0,
				messageNumImage[newBusMessages],
				0,
				0,
				0};
		return iconResc[position];

	}
	private class SampleItem {
		public String tag;
		public int iconRes;
		public int messageRes;

		public SampleItem(String tag, int iconRes,int messageRes) {
			this.tag = tag;
			this.iconRes = iconRes;
			this.messageRes=messageRes;
		}
	}
	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView
					.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);
			ImageView message_icon = (ImageView) convertView
					.findViewById(R.id.new_message_icon);
			message_icon.setImageResource(getItem(position).messageRes);

			return convertView;
		}

	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new sightSearchFragment();
			break;
		case 1:
			newContent = new PurchaseFragment();
			break;
		case 2:
			newContent=new RouteFragment();
			break;
		case 3:
			break;
		case 4:
			newContent = new BusFragment();
			newBusMessages=0;
			onActivityCreated(null);//閻愮懓鍤崥搴礉閺傛澘濮╅幀浣规蒋閺侀璐�閿涳拷			
			break;
		case 5:
			break;
		case 6:
			newContent=new MineFragment();
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		if (getActivity() instanceof NianPiaoActivity) {
			NianPiaoActivity fca = (NianPiaoActivity) getActivity();
			fca.switchContent(fragment,0);
		}
	}

	@Override
	public void onClick(View v) {
		Log.v("icon", "click");
	}
}
