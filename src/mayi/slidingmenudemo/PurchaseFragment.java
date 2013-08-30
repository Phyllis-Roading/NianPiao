package mayi.slidingmenudemo;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class PurchaseFragment extends Fragment implements OnClickListener,OnTouchListener{

	public List<String> urls;
	public GuideGallery images_ga;
	private Thread timeThread = null;
	public boolean timeFlag = true;
	private boolean isExit = false;
	public ImageTimerTask timeTaks = null;
	int gallerypisition = 0;
	static View imageView;
	TicketDetails flow,question,partner;
	Button firsePurchase,secondPurchase;
	private ScrollView scrollView;
	private View firstBuy,secondBuy;
	int[] location = new int[2];
	int[] location2 = new int[2];
	private int lastY = 0;
	private int touchEventId = -135;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		BaseActivity.setActionbarTitle(R.string.trip_purchase);
		BaseActivity.setActionbarNavVisibility(View.VISIBLE);
		imageView=inflater.inflate(R.layout.url_connection_image, container, false);
        prepare();
		timeTaks = new ImageTimerTask();
		// �?000�?��每隔5000执行timeTaks
		autoGallery.scheduleAtFixedRate(timeTaks, 3000, 3000);
		timeThread = new Thread() {
			public void run() {
				while (!isExit) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (timeTaks) {
						if (!timeFlag) {
							timeTaks.timeCondition = true;
							timeTaks.notifyAll();
						}
					}
					timeFlag = true;
				}
			};
		};
		timeThread.start();
		return imageView;
	}

	public void prepare(){
		firstBuy=(View)imageView.findViewById(R.id.first_buy);
		secondBuy=(View)imageView.findViewById(R.id.second_buy);
		secondBuy.setVisibility(View.GONE);
		scrollView=(ScrollView)imageView.findViewById(R.id.scroll_view);
		scrollView.setOnTouchListener(this);
		firsePurchase=(Button)imageView.findViewById(R.id.first_purchase);
		firsePurchase.setOnClickListener(this);
		secondPurchase=(Button)imageView.findViewById(R.id.second_purchase);
		secondPurchase.setOnClickListener(this);
		flow=new TicketDetails((TextView)imageView.findViewById(R.id.txt_flow), (Button)imageView.findViewById(R.id.bnt_flow));
		question=new TicketDetails((TextView)imageView.findViewById(R.id.txt_question),(Button)imageView.findViewById(R.id.bnt_question));
		partner=new TicketDetails((TextView)imageView.findViewById(R.id.txt_partner), (Button)imageView.findViewById(R.id.bnt_partner));
	}
	
	Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == touchEventId) {
//				if (lastY != scrollView.getScrollY())//左右移动不变，但页面�?��
				{
					handler.sendMessageDelayed(
							handler.obtainMessage(touchEventId, scrollView), 5);
					lastY = scrollView.getScrollY();
					firstBuy.getLocationOnScreen(location);
					secondBuy.getLocationOnScreen(location2);
					if (location[1] <= location2[1]) {
						secondBuy.setVisibility(View.VISIBLE);
					} else {
						secondBuy.setVisibility(View.GONE);
					}
				}
			}
		}
	};
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			handler.sendMessageDelayed(
					handler.obtainMessage(touchEventId, v), 5);
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			handler.sendMessageDelayed(
					handler.obtainMessage(touchEventId, v), 5);
		}
		return false;
	}
	
	@Override
	public void onClick(View v) {
		LayoutInflater inflater = this.getActivity().getLayoutInflater();
	    View layout = inflater.inflate(R.layout.buy_dialog,
	     (ViewGroup) imageView.findViewById(R.id.buy_dialog));
	    new AlertDialog.Builder(this.getActivity()).setTitle("年票订单").setView(layout)
	     .setPositiveButton("提交", null)
	     .setNegativeButton("取消", null).show();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		init();
	}

	@Override
	public void onResume() {
		super.onResume();
		timeFlag = false;
	}

	@Override
	public void onPause() {
		super.onPause();
		timeTaks.timeCondition = false;
	}
	
	private void init() {
		images_ga = (GuideGallery) imageView.findViewById(R.id.image_wall_gallery);
		images_ga.setImageActivity(this);
		ImageAdapter imageAdapter = new ImageAdapter(this.getActivity());
		images_ga.setAdapter(imageAdapter);
		LinearLayout pointLinear = (LinearLayout) imageView.findViewById(R.id.gallery_point_linear);
		pointLinear.setBackgroundColor(Color.argb(200, 135, 135, 152));
		for (int i = 0; i < 4; i++) {
			ImageView pointView = new ImageView(this.getActivity());
			if (i == 0) {
				pointView.setBackgroundResource(R.drawable.feature_point_cur);
			} else
				pointView.setBackgroundResource(R.drawable.feature_point);
			pointLinear.addView(pointView);
		}
		images_ga.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println(arg2 + "arg2");
			}
		});

	}
	final Handler autoGalleryHandler = new Handler(){
		public void handleMessage(Message message) {
			super.handleMessage(message);
			switch (message.what) {
			case 1:
				images_ga.setSelection(message.getData().getInt("pos"));
				break;
			}
		}
	};
	
	class ImageTimerTask extends TimerTask {
		public volatile boolean timeCondition = true;

		public void run() {
			synchronized (this) {
				while (!timeCondition) {
					try {
						Thread.sleep(100);
						wait();
					} catch (InterruptedException e) {
						Thread.interrupted();
					}
				}
			}
			try {
				gallerypisition = images_ga.getSelectedItemPosition() + 1;
				Message msg = new Message();
				Bundle date = new Bundle();// 存放数据
				date.putInt("pos", gallerypisition);
				msg.setData(date);
				msg.what = 1;// 消息标识
				autoGalleryHandler.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	Timer autoGallery = new Timer();

}
