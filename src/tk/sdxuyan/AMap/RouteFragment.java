package tk.sdxuyan.AMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import net.basilwang.niaopiao.R;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapFragment;
import com.amap.api.maps.MapView;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.search.core.AMapException;
import com.amap.api.search.core.LatLonPoint;
import com.amap.api.search.poisearch.PoiItem;
import com.amap.api.search.poisearch.PoiPagedResult;
import com.amap.api.search.poisearch.PoiSearch;
import com.amap.api.search.poisearch.PoiSearch.SearchBound;
import com.amap.api.search.poisearch.PoiTypeDef;

public class RouteFragment extends Fragment implements
OnMarkerClickListener, InfoWindowAdapter, OnInfoWindowClickListener,
OnGestureListener, OnMarkerDragListener, OnMapLoadedListener{
	
	private AMap aMap;
	private ViewFlipper flipper;
	private GestureDetector detector;
	// private OnLocationChangedListener mListener;
	// private LocationManagerProxy mAMapLocationManager;

	private ArrayList<MarkerOptions> markers = new ArrayList<MarkerOptions>();

	private int flag = 0;// 当前景点的标号

	private PoiPagedResult result;
	private ProgressDialog progDialog = null;
	private int curpage = 1;
	// private int cnt = 0;
	private String query = null;

	private getJson json;
	private ArrayList<LatLng> routeSteps = new ArrayList<LatLng>();
	
	private float zoom;

	private View routeView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		detector = new GestureDetector(this.getActivity(), this);
		json = new getJson();
		json.execute("http://120.192.31.164:8993/api/pointOfRoutes/");


		routeView=inflater.inflate(R.layout.amap_main,container,false);
		init();
		routeView.setOnTouchListener(new View.OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent event) {
	                return detector.onTouchEvent(event);
	            }
	        });
		return routeView;
	}
	
	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			
			SupportMapFragment fragment = ((SupportMapFragment) this.getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.map));
			aMap=fragment.getMap();
			
			if (AMapUtil.checkReady(this.getActivity(), aMap)) {

				setUpMap();
			}

		}
//		
		
	}

	private View addTextView(String name, String intro) {

		LayoutInflater layoutInflater = LayoutInflater.from(this.getActivity());
		LinearLayout resultView = (LinearLayout) layoutInflater.inflate(
				R.layout.amap_intro, null);
		((TextView) resultView.findViewById(R.id.textView1)).setText(name);
		((TextView) resultView.findViewById(R.id.textView2)).setText(intro);
		((Button) resultView.findViewById(R.id.button1))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						query = markers.get(flag).getTitle();
						curpage = 1;
						// cnt = 0;
						showProgressDialog();// 显示进度框
						new Thread(new Runnable() {
							public void run() {
								try {
									PoiSearch poiSearch = new PoiSearch(
											RouteFragment.this.getActivity(),
											new PoiSearch.Query("",
													PoiTypeDef.FoodBeverages,
													"0531"));// 设置搜索字符串，poi搜索类型，poi搜索区域（空字符串代表全国）
									poiSearch
											.setBound(new SearchBound(
													new LatLonPoint(
															markers.get(flag)
																	.getPosition().latitude,
															markers.get(flag)
																	.getPosition().longitude),
													800));
									result = poiSearch.searchPOI();
									// if (result != null) {
									// cnt = result.getPageCount();
									// }
									handler.sendMessage(Message.obtain(handler,
											Constants.POISEARCH));
								} catch (AMapException e) {
									handler.sendMessage(Message.obtain(handler,
											Constants.ERROR));
									e.printStackTrace();
								}
							}
						}).start();

					}
				});
		return resultView;
	}

	private void setUpMap() {
		// 自定义系统定位小蓝点
		// MyLocationStyle myLocationStyle = new MyLocationStyle();
		// myLocationStyle.myLocationIcon(BitmapDescriptorFactory
		// .fromResource(R.drawable.location_marker));
		// myLocationStyle.strokeColor(Color.BLUE);
		// myLocationStyle.strokeWidth(5);
		// aMap.setMyLocationStyle(myLocationStyle);
		// mAMapLocationManager = LocationManagerProxy
		// .getInstance(MainActivity.this);
		// aMap.setLocationSource(this);
		// aMap.setMyLocationEnabled(true);//
		// 设置为true表示系统定位按钮显示并响应点击，false表示隐藏，默认是false
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.setInfoWindowAdapter(this);
		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
		aMap.setOnMarkerDragListener(this);// 设置marker可拖拽事件监听器
		aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
		flipper = (ViewFlipper) routeView.findViewById(R.id.viewFlipperIntro);
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.672115,
				117.024027), 10));
		query="路线";
		showProgressDialog();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					routeSteps = json.get();
					handler.sendMessage(Message.obtain(handler,
							Constants.GETJSON_READY));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}).start();
//		try {
//			routeSteps = json.get();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ExecutionException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
//		setmarkers();
//		addMarkersToMap(markers.get(flag));
//		
//		drawPolyline();

	}

	private void setmarkers() {
		markers.add(new MarkerOptions().position(routeSteps.get(0)).title("起点"));
		markers.add(new MarkerOptions().position(routeSteps.get(200)).title(
				"途经点"));
		markers.add(new MarkerOptions().position(
				routeSteps.get(routeSteps.size() - 1)).title("终点"));
	}

	/**
	 * 往地图上添加marker
	 * */
	private void addMarkersToMap(MarkerOptions options) {
		aMap.addMarker(options.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_sight))).showInfoWindow();
	}

	private void drawPolyline() {
		aMap.addPolyline((new PolylineOptions().addAll(routeSteps).width(10)
				.color(Color.RED)));
	}

	@Override
	public void onPause() {
		super.onPause();
		// deactivate();
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// arg0.showInfoWindow();
		return false;
	}

	@Override
	public View getInfoContents(Marker arg0) {
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		return null;
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		ToastUtil.show(this.getActivity(), "你点击了景点" + arg0.getTitle());

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return true; 
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		zoom=aMap.getCameraPosition().zoom;
		if (e1.getX() - e2.getX() > 120) {
			if ((flag < markers.size() - 1) && markers.size() > 0) {
				// markers.get(flag).setVisible(false);
				aMap.clear();
				flag++;
				addMarkersToMap(markers.get(flag));
				drawPolyline();
				// markers.get(flag).setVisible(true);
				// marker_query=markers.get(flag);
				aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers
						.get(flag).getPosition(), zoom));
				// markers.get(flag).showInfoWindow();
				this.flipper.setInAnimation(AnimationUtils.loadAnimation(this.getActivity(),
						R.anim.push_left_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this.getActivity(),
						R.anim.push_left_out));
				this.flipper.showNext();
			}

			return true;
		} else if (e1.getX() - e2.getX() < -120) {
			if (flag > 0 && markers.size() > 0) {
				// markers.get(flag).setVisible(false);
				flag--;
				aMap.clear();
				addMarkersToMap(markers.get(flag));
				drawPolyline();
				// markers.get(flag).setVisible(true);
				// marker_query=markers.get(flag);
				aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers
						.get(flag).getPosition(), zoom));
				// markers.get(flag).showInfoWindow();
				this.flipper.setInAnimation(AnimationUtils.loadAnimation(this.getActivity(),
						R.anim.push_right_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this.getActivity(),
						R.anim.push_right_out));
				this.flipper.showPrevious();
			}

			return true;
		}

		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// ToastUtil.show(this, "你点击了viewFlipper");

		return false;
	}

	/**
	 * poi搜索
	 * */

	/**
	 * 显示进度框
	 */
	private void showProgressDialog() {
		if (progDialog == null)
			progDialog = new ProgressDialog(this.getActivity());
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setIndeterminate(false);
		progDialog.setCancelable(true);
		progDialog.setMessage("正在搜索:\n" + query);
		progDialog.show();
	}

	/**
	 * 隐藏进度框
	 */
	private void dissmissProgressDialog() {
		if (progDialog != null) {
			progDialog.dismiss();
		}
	}

	private LatLngBounds getLatLngBounds(List<PoiItem> poiItems) {
		LatLngBounds.Builder b = LatLngBounds.builder();
		for (int i = 0; i < poiItems.size(); i++) {
			b.include(new LatLng(poiItems.get(i).getPoint().getLatitude(),
					poiItems.get(i).getPoint().getLongitude()));
		}
		return b.build();
	}

	private void showPoiItem(List<PoiItem> poiItems) {
		if (poiItems != null && poiItems.size() > 0) {
			if (aMap == null)
				return;
			aMap.clear();
			LatLngBounds bounds = getLatLngBounds(poiItems);
			aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 5));
			addMarkersToMap(markers.get(flag));
			addMarkers(poiItems);
		} else {
			ToastUtil.show(this.getActivity(), "没有搜索到数据！");
		}
	}

	/**
	 * 一次性打印多个Marker出来
	 */
	private void addMarkers(List<PoiItem> poiItems) {
		for (int i = 0; i < poiItems.size(); i++) {
			aMap.addMarker(new MarkerOptions()
					.position(
							new LatLng(
									poiItems.get(i).getPoint().getLatitude(),
									poiItems.get(i).getPoint().getLongitude()))
					.title(poiItems.get(i).getTitle())
					.snippet(poiItems.get(i).getSnippet())
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.marker_rest)));
		}

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == Constants.POISEARCH) {
				dissmissProgressDialog();// 隐藏对话框

				if (result != null) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								final List<PoiItem> poiItems = result
										.getPage(1);
								RouteFragment.this.getActivity().runOnUiThread(new Runnable() {
									@Override
									public void run() {
										showPoiItem(poiItems);// 每页显示10个poiitem
										drawPolyline();
									}
								});

							} catch (AMapException e) {
								e.printStackTrace();

							}
						}
					}).start();
				}

			} else if (msg.what == Constants.ERROR) {
				dissmissProgressDialog();// 隐藏对话框
				ToastUtil.show(RouteFragment.this.getActivity(), "搜索失败,请检查网络连接！");
			} else if (msg.what == Constants.GETJSON_READY) {
				dissmissProgressDialog();// 隐藏对话框
				setmarkers();
				addMarkersToMap(markers.get(flag));
				
				drawPolyline();
				flipper.addView(addTextView(markers.get(0).getTitle(), null));
				flipper.addView(addTextView(markers.get(1).getTitle(), null));
				flipper.addView(addTextView(markers.get(2).getTitle(), null));
//				curpage++;
//				new Thread(new Runnable() {
//
//					@Override
//					public void run() {
//						final List<PoiItem> poiItems;
//						try {
//							poiItems = result.getPage(curpage);
//							runOnUiThread(new Runnable() {
//								@Override
//								public void run() {
//									showPoiItem(poiItems);// 每页显示10个poiitem
//								}
//							});
//						} catch (AMapException e) {
//							e.printStackTrace();
//						}
//					}
//				}).start();
			} 
		}
	};

	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapLoaded() {
		// TODO Auto-generated method stub

	}

	/**
	 * 暂时去掉定位功能
	 * */
	// /**
	// * 此方法已经废弃
	// */
	// @Override
	// public void onLocationChanged(Location location) {
	// }
	//
	// @Override
	// public void onProviderDisabled(String provider) {
	// }
	//
	// @Override
	// public void onProviderEnabled(String provider) {
	// }
	//
	// @Override
	// public void onStatusChanged(String provider, int status, Bundle extras) {
	// }
	//
	// /**
	// * 定位成功后回调函数
	// */
	// @Override
	// public void onLocationChanged(AMapLocation aLocation) {
	// if (mListener != null) {
	// mListener.onLocationChanged(aLocation);
	//
	// }
	// }
	//
	// /**
	// * 激活定位
	// */
	// @Override
	// public void activate(OnLocationChangedListener listener) {
	// mListener = listener;
	// if (mAMapLocationManager == null) {
	// mAMapLocationManager = LocationManagerProxy.getInstance(this);
	// }
	// /*
	// * mAMapLocManager.setGpsEnable(false);//
	// * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true
	// */
	// // Location API定位采用GPS和网络混合定位方式，时间最短是5000毫秒
	// mAMapLocationManager.requestLocationUpdates(
	// LocationProviderProxy.AMapNetwork, 5000, 10, this);
	//
	// }
	//
	// /**
	// * 停止定位
	// */
	// @Override
	// public void deactivate() {
	// mListener = null;
	// if (mAMapLocationManager != null) {
	// mAMapLocationManager.removeUpdates(this);
	// mAMapLocationManager.destory();
	// }
	// mAMapLocationManager = null;
	// }

}
