package net.basilwang.nianpiao.adapter;

import java.util.List;

import org.w3c.dom.Text;

import net.basilwang.nianpiao.model.SightListItemModel;
import net.basilwang.niaopiao.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SightSearchListAdapter extends BaseAdapter {

	private Context mContext;
	private List<SightListItemModel> mLists;
	private LayoutInflater mLayoutInflater;

	public SightSearchListAdapter(Context pContext,
			List<SightListItemModel> pLists) {

		this.mContext = pContext;
		this.mLists = pLists;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mLists!=null?mLists.size():0;
	}

	@Override
	public Object getItem(int position) {
		return mLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Holder mHolder=null;
		if(convertView==null){
			mHolder=new Holder();
			convertView=mLayoutInflater.inflate(R.layout.sight_list_item, null);
			mHolder.sight_list_item_img=(ImageView)convertView.findViewById(R.id.sight_logo);
			mHolder.sight_list_item_title=(TextView)convertView.findViewById(R.id.sight_title);
			mHolder.sight_list_item_level=(TextView)convertView.findViewById(R.id.sight_level);
			mHolder.sight_list_item_price=(TextView)convertView.findViewById(R.id.sight_price);
			mHolder.sight_list_item_discount=(TextView)convertView.findViewById(R.id.sight_discount);
			mHolder.sight_list_item_distance=(TextView)convertView.findViewById(R.id.sight_distance);
			convertView.setTag(mHolder);
		}else{
			mHolder=(Holder)convertView.getTag();
		}
		mHolder.sight_list_item_img.setImageResource(mLists.get(position).getId());
		mHolder.sight_list_item_title.setText(mLists.get(position).getTitle());
		mHolder.sight_list_item_level.setText(mLists.get(position).getLevel());
		mHolder.sight_list_item_price.setText(mLists.get(position).getPrice());
		mHolder.sight_list_item_discount.setText(mLists.get(position).getDiscount());
		mHolder.sight_list_item_distance.setText(mLists.get(position).getDistance());
		
		return convertView;
	}
	
	private static class Holder
	{
		ImageView sight_list_item_img;
		TextView sight_list_item_title;
		TextView sight_list_item_level;
		TextView sight_list_item_price;
		TextView sight_list_item_discount;
		TextView sight_list_item_distance;
	}

}
