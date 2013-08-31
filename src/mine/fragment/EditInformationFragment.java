package mine.fragment;

import java.util.zip.Inflater;

import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import br.com.dina.ui.widget.UIButton;
import br.com.dina.ui.widget.UIButton.ClickListener;
import br.com.dina.ui.widget.UITableView;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class EditInformationFragment extends Fragment {

	UIButton icon;
	UITableView information;
	UIButton more;
	View editView;
	View popUp;
	private PopupWindow popup;
	int width;
	int height;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		BaseActivity.setActionbarTitle(R.string.mine_edit);
		BaseActivity.setActionbarNavVisibility(View.GONE);
//		popUp=inflater.inflate(R.layout.popup, null);
//		width=getActivity().getWindowManager().getDefaultDisplay().getWidth();
//		height=getActivity().getWindowManager().getDefaultDisplay().getHeight();
//        // 创建PopupWindow对象
//		popup = new PopupWindow(popUp, width, height/2);
		if (editView == null) {
			editView = inflater.inflate(R.layout.mine_information, null);
			initView();
		}
		return editView;
	}

	private void initView() {
		// TODO Auto-generated method stub
		icon = (UIButton) editView.findViewById(R.id.mine_icon);
		more = (UIButton) editView.findViewById(R.id.mine_more);
		information = (UITableView) editView
				.findViewById(R.id.mine_information);
		
		initTableView();
		icon.addClickListener(new ClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
//				//使其聚焦
//				popup.setFocusable(true);
//				//设置允许在外点击消失
//				popup.setOutsideTouchable(true);
//				//点击“返回back”,不会影响你的背景
//				popup.setBackgroundDrawable(new BitmapDrawable());
//				
//				// 以下拉方式显示。
////				popup.showAsDropDown(v);
//				//将PopupWindow显示在指定位置
//				popup.showAtLocation(editView.findViewById(R.id.icon), Gravity.CENTER, 0,
//					height/2);
			}
		});

	}

	private void initTableView() {
		// TODO Auto-generated method stub
		information.addBasicItem("昵称");
		information.addBasicItem("姓名");
		information.addBasicItem("电话号码");
		information.addBasicItem("性别");
		information.commit();
	}

}
