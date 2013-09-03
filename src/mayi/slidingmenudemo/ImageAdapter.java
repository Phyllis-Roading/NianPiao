package mayi.slidingmenudemo;

import java.util.List;

import net.basilwang.niaopiao.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

@SuppressWarnings("deprecation")
public class ImageAdapter extends BaseAdapter{  
    private List<String> imageUrls;         
    private Context context;  
    private int positon=0;
    private ImageAdapter self;
    Uri uri;
    Intent intent;
    ImageView imageView;
    PurchaseFragment imageFragment=new PurchaseFragment();
   public static Integer[] imgs = {
 			R.drawable.one,
 			R.drawable.two,
			R.drawable.three,
			R.drawable.four
	};
    public ImageAdapter(/*List<String> imageUrls, */Context context) {  
        this.context = context;  
        this.self = this;
    }  
  
    public int getCount() {  
        return Integer.MAX_VALUE;  
    }  
  
    public Object getItem(int position) {  
        return imageUrls.get(position % imgs.length);  
    }  
   
    public long getItemId(int position) {  
        return position;  
    }  
  
    @SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				switch (msg.what) {
					case 0: {
						self.notifyDataSetChanged();
					}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
			}
		}
	};
    
    public View getView(int position, View convertView, ViewGroup parent) {  
  
        //Bitmap image;  
        if(convertView==null){  
            convertView = LayoutInflater.from(context).inflate(R.layout.imageitem,null);   
            Gallery.LayoutParams params = new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT,Gallery.LayoutParams.WRAP_CONTENT);
            convertView.setLayoutParams(params);
            convertView.setTag(imgs);  
  
        }  
       else{  
              
        }  
        imageView = (ImageView) convertView.findViewById(R.id.gallery_image);  
        imageView.setImageResource(imgs[position % imgs.length]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY); 
        changePointView(position % imgs.length);
        return convertView;  
        
    }  
	public void changePointView(int cur) {
		LinearLayout pointLinear=(LinearLayout)PurchaseFragment.imageView.findViewById(R.id.gallery_point_linear);
		pointLinear.getBackground().setAlpha(0);
		View view = pointLinear.getChildAt(positon);
		View curView = pointLinear.getChildAt(cur);
		if (view != null && curView != null) {
			ImageView pointView = (ImageView) view;
			ImageView curPointView = (ImageView) curView;
			pointView.setBackgroundResource(R.drawable.feature_point);
			curPointView.setBackgroundResource(R.drawable.feature_point_cur);
			positon = cur;
		}
	}
}  
