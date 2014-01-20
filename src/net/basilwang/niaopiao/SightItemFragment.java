package net.basilwang.niaopiao;

import net.basilwang.nianpiao.model.SightListItemModel;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SightItemFragment extends Fragment {
	
	private View mView;
//	private ImageView imgTop;
//	private TextView txImgCount;
//	private TextView txLevel;
//	private TextView txRefPrice;
//	private TextView txOpenTime;
//	private TextView txAddress;
//	private TextView txSightIntroduce;
//	private TextView txSightIntroduceMore;
	private SightListItemModel sightDetailModel;
	
	public SightItemFragment(SightListItemModel slim){
		this.sightDetailModel=slim;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		BaseActivity.setActionbarTitle(sightDetailModel.getTitle());
		BaseActivity.setActionbarNavVisibility(View.GONE);
		if(null==mView){
			mView=inflater.inflate(R.layout.sight_detail_page, container, false);
			initViw();
			
		}
		return mView;
	}

	private void initViw() {
		Holder mHolder=new Holder();
		mHolder.imgTop=(ImageView)mView.findViewById(R.id.imgTop);
		mHolder.txImgCount=(TextView)mView.findViewById(R.id.txImgCount);
		mHolder.txLevel=(TextView)mView.findViewById(R.id.txLevel);
		mHolder.txRefPrice=(TextView)mView.findViewById(R.id.txRefPrice);
		mHolder.txOpenTime=(TextView)mView.findViewById(R.id.txOpenTime);
		mHolder.txAddress=(TextView)mView.findViewById(R.id.txAddress);
		mHolder.txSightIntroduce=(TextView)mView.findViewById(R.id.txSightIntroduce);
		mHolder.txSightIntroduceMore=(TextView)mView.findViewById(R.id.txSightIntroduceMore);
		bindData(mHolder);
	}
	
	private void bindData(Holder mHolder) {
		mHolder.imgTop.setImageResource(sightDetailModel.getId());
		mHolder.txImgCount.setText("5");
		mHolder.txLevel.setText(sightDetailModel.getLevel());
		mHolder.txRefPrice.setText(sightDetailModel.getPrice());
		mHolder.txOpenTime.setText(sightDetailModel.getOpenTime());
		mHolder.txAddress.setText(sightDetailModel.getDistance());
		mHolder.txSightIntroduce.setText(sightDetailModel.getIntroduce());
		
	}
	
	private static class Holder
	{
		ImageView imgTop;
		TextView txImgCount;
		TextView txLevel;
		TextView txRefPrice;
		TextView txOpenTime;
		TextView txAddress;
		TextView txSightIntroduce;
		TextView txSightIntroduceMore;
	}

}
