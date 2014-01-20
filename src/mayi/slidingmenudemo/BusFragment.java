package mayi.slidingmenudemo;

import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BusFragment extends Fragment {

	private View busFragment;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		BaseActivity.setActionbarTitle(R.string.bus_news);
		BaseActivity.setActionbarNavVisibility(View.VISIBLE);
		busFragment=inflater.inflate(R.layout.bus_fragment, container,false);
		return busFragment;
	}

}
