package mine.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import net.basilwang.niaopiao.R.layout;
import net.basilwang.niaopiao.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
import br.com.dina.ui.widget.UIButton;
import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;

public class InformationActivity extends TestActivity implements
		OnClickListener, br.com.dina.ui.widget.UIButton.ClickListener,
		ClickListener {

	private PopupWindow popup;
	private Button selectPhoto, uploadPhoto, photograph;
	private ImageView imageView;
	private String picPath = null;
	private Bitmap bitmap;
	UITableView information;
	private Uri photoUri;
	private File photoDir;
	private File photoAbsoluteDir;
	private int height;

	public InformationActivity() {
		super(R.string.app_name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_information);
		BaseActivity.setActionbarTitle(R.string.mine_edit);
		BaseActivity.setActionbarNavVisibility(View.GONE);// 设置导航
		initView();
		initTableView();

	}

	private void initView() {
		View view = this.getLayoutInflater().inflate(R.layout.popup, null);// 装载R.layout.popup对应的界面布局
		WindowManager windowmanager = (WindowManager) getSystemService(WINDOW_SERVICE);
		int width = windowmanager.getDefaultDisplay().getWidth();
		height = windowmanager.getDefaultDisplay().getHeight();
		// 创建PopupWindow对象
		popup = new PopupWindow(view, width, height * 2 / 5);
		imageView = (ImageView) view.findViewById(R.id.imageView);
		information = (UITableView) findViewById(R.id.mine_information);
		selectPhoto = (Button) view.findViewById(R.id.selectPhoto);
		uploadPhoto = (Button) view.findViewById(R.id.uploadPhoto);
		photograph = (Button) view.findViewById(R.id.camera);
		clickListener();
	}

	private void clickListener() {
		UIButton icon = (UIButton) findViewById(R.id.mine_icon);
		UIButton more = (UIButton) findViewById(R.id.mine_more);
		icon.addClickListener(this);
		more.setOnClickListener(this);
		selectPhoto.setOnClickListener(this);
		uploadPhoto.setOnClickListener(this);
		photograph.setOnClickListener(this);
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				System.out.println("succeed///////");
				Intent i=new Intent(InformationActivity.this, IconUploadActivity.class);
				startActivity(i);
			}
		});
		// more.addClickListener(this);
		initTableView();
		// information.setClickListener(new ClickListener() {
		//
		// @Override
		// public void onClick(View view) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

	}

	private void initTableView() {
		information.addBasicItem("昵称");
		information.addBasicItem("姓名");
		information.addBasicItem("性别");
		information.addBasicItem("电话号码");
		information.commit();
		information.setClickListener(this);
	}

	@Override
	public void onClick(int index) {
		switch (index) {
		case 0:
			Intent in = new Intent(InformationActivity.this,
					CollectActivity.class);
			startActivity(in);
			System.out.println("sfn,adhkjlanjkn");
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mine_icon:
			System.out.println("dsdkjfkasjfkasj");
			showPopUpWindow(v);// 出现popUpWindow
			break;
		case R.id.camera:
			photograph();// 拍照
			break;
		case R.id.selectPhoto:
			selectPhoto();// 选择图片
			break;
		case R.id.uploadPhoto:
			uploadPhoto();// 上传图片
		case R.id.mine_more:
			break;
		default:
			break;
		}
	}

	private void uploadPhoto() {
		if (picPath == null) {
			Toast.makeText(InformationActivity.this, "请选择图片！",
					Toast.LENGTH_SHORT).show();
		} else {
			File file = new File(picPath);
			if (file != null)
				Log.i("upload", "image");

			UploadUtil uploadutiltask = new UploadUtil();
			uploadutiltask.execute(file);
		}
	}

	private void selectPhoto() {
		destoryBimap();
		Intent i = new Intent();
		i.setType("image/*");
		i.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(i, Activity.DEFAULT_KEYS_SEARCH_GLOBAL);
	}

	private void showPopUpWindow(View v) {
		// 使其聚焦
		popup.setFocusable(true);
		// 设置允许在外点击消失
		// popup.setOutsideTouchable(true);
		// 点击“返回back”,不会影响你的背景
		popup.setBackgroundDrawable(new BitmapDrawable());
		popup.setAnimationStyle(R.style.AnimationPreview);
		// 将PopupWindow显示在指定位置
		popup.showAtLocation(findViewById(R.id.mine_icon), Gravity.CENTER, 0,
				height * 2 / 5);
	}

	private void photograph() {
		isSdState();// 判断sd卡是否可用
		creatPhotoDir();// 创建存储路径
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
		photoUri = Uri.fromFile(getphotoAbsoluteDir());
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		startActivityForResult(intent, Activity.DEFAULT_KEYS_DIALER);
	}

	private void creatPhotoDir() {
		photoDir = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/DCIM/Camera");
		if (!photoDir.exists()) {
			photoDir.mkdirs();
		}
	}

	private void isSdState() {
		String sdState = Environment.getExternalStorageState();
		if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(InformationActivity.this, R.string.sd_card,
					Toast.LENGTH_SHORT).show();
			return;
		}
	}

	private File getphotoAbsoluteDir() {
		FormatPhotoName formatPhotoName = new FormatPhotoName();
		String photoName = formatPhotoName.getPhotoName(new Date());
		photoAbsoluteDir = new File(photoDir, photoName);
		return photoAbsoluteDir;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			dealRequest(requestCode, data);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void dealRequest(int requestCode, Intent data) {
		switch (requestCode) {
		case Activity.DEFAULT_KEYS_DIALER:
			savePhotograph();
			break;
		case Activity.DEFAULT_KEYS_SEARCH_GLOBAL:
			selectPic(data);
			break;
		}
	}

	private void selectPic(Intent data) {
		Uri uri = data.getData();
		String[] pojo = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, pojo, null, null, null);
		if (cursor != null) {
			int colunm_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			showSelectPic(cursor, colunm_index, uri);
		} else {
			alert();
		}
	}

	private void savePhotograph() {
		Toast.makeText(this, R.string.save_uccess, Toast.LENGTH_SHORT).show();
		sendBroadcast(new Intent(
				Intent.ACTION_MEDIA_MOUNTED,
				Uri.parse("file://" + Environment.getExternalStorageDirectory())));
		showPhotograph();
	}

	private void showPhotograph() {
		Bitmap photoBitmap;
		try {
			photoBitmap = MediaStore.Images.Media.getBitmap(
					getContentResolver(), photoUri);
			picPath = photoAbsoluteDir.toString();// 拍照存储照片路径
			imageView.setImageBitmap(photoBitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showSelectPic(Cursor cursor, int index, Uri uri) {

		String path = cursor.getString(index);
		if (path.endsWith("jpg") || path.endsWith("png")
				|| path.endsWith("gif")) {
			picPath = path;
			imageView.setImageBitmap(getBitmap(uri));
		} else {
			alert();
		}
	}

	private Bitmap getBitmap(Uri uri) {
		ContentResolver cr = this.getContentResolver();
		try {
			bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	private void alert() {
		Dialog dialog = new AlertDialog.Builder(this).setTitle("提示ʾ")
				.setMessage("你选择的不是有效图片！")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						picPath = null;
					}
				}).create();
		dialog.show();
	}

	private void destoryBimap() {
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
		}
	}

//	@Override
//	public void onClick(View view) {
//		// TODO Auto-generated method stub
//		switch (view.getId()) {
//		case R.id.mine_icon:
//			System.out.println("succeed///////");
//			Intent i=new Intent(this, IconUploadActivity.class);
//			startActivity(i);
//			break;
//		case R.id.mine_more:
//			break;
//		}
//	}
}
