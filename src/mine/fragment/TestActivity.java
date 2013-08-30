package mine.fragment;

import net.basilwang.niaopiao.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class TestActivity extends SherlockActivity{

	private int mTitleRes;
	public TestActivity(int titleRes) {
		super();
		mTitleRes = titleRes;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTitle(mTitleRes);

		// customize the ActionBar
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		View ABTitleView=getLayoutInflater().inflate(R.layout.title_bar,
				null);
		ActionBar actionBar=getSupportActionBar();
		actionBar.setCustomView(ABTitleView,lp);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayShowCustomEnabled(true);
		Drawable d=(Drawable)getResources().getDrawable(R.drawable.titlebar_whole);
		actionBar.setBackgroundDrawable(d);
		actionBar.setHomeButtonEnabled(true);
		
		View view = (View)findViewById(R.id.abs_left);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				toggle();
			}
		});
		
		TextView mtv=(TextView)findViewById(R.id.abs_title);
		mtv.setText("sdcdsvdfs");
	}
	
}
