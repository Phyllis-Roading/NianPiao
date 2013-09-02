package mine.fragment;

import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.dina.ui.widget.UIButton;
import br.com.dina.ui.widget.UIButton.ClickListener;
import br.com.dina.ui.widget.UITableView;

public class EditInformationFragment extends Fragment {

	UIButton icon;
	UITableView information;
	UIButton more;
	View editView;
	View popUp;
	int width;
	int height;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		BaseActivity.setActionbarTitle(R.string.mine_edit);
		BaseActivity.setActionbarNavVisibility(View.GONE);
		if (editView == null) {
			editView = inflater.inflate(R.layout.mine_information, null);
			initView();
		}
		return editView;
	}

	private void initView() {
		icon = (UIButton) editView.findViewById(R.id.mine_icon);
		more = (UIButton) editView.findViewById(R.id.mine_more);
		information = (UITableView) editView
				.findViewById(R.id.mine_information);

		initTableView();
		icon.addClickListener(new ClickListener() {

			@Override
			public void onClick(View view) {
			}
		});

	}

	private void initTableView() {
		information.addBasicItem("昵称");
		information.addBasicItem("姓名");
		information.addBasicItem("电话号码");
		information.addBasicItem("性别");
		information.commit();
	}

}
