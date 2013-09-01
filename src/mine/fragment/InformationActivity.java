package mine.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import br.com.dina.ui.widget.UIButton;
import br.com.dina.ui.widget.UIButton.ClickListener;
import br.com.dina.ui.widget.UITableView;
import net.basilwang.niaopiao.BaseActivity;
import net.basilwang.niaopiao.R;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class InformationActivity extends TestActivity implements ClickListener,
		OnClickListener {

	private PopupWindow popup;
	private int width;
	private int height;
	private Button selectPhoto, uploadPhoto, photograph;
	private ImageView imageView;
	private String picPath = null;
	private Bitmap bitmap;
	UploadUtil uploadutiltask;
	UIButton icon;
	UITableView information;
	UIButton more;
	private FormatPhotoName formatPhotoName;
	private Uri photoUri;
	private File photoDir;
	private File photoAbsoluteDir;
	private String photoName;
	private View view;
	WindowManager windowmanager;

	public InformationActivity() {
		super(R.string.app_name);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
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
		// TODO Auto-generated method stub
		view = this.getLayoutInflater().inflate(R.layout.popup, null);// 装载R.layout.popup对应的界面布局
		windowmanager = (WindowManager) getSystemService(WINDOW_SERVICE);
		width = windowmanager.getDefaultDisplay().getWidth();
		height = windowmanager.getDefaultDisplay().getHeight();
		// 创建PopupWindow对象
		popup = new PopupWindow(view, width, height / 2);
		imageView = (ImageView) view.findViewById(R.id.imageView);
		icon = (UIButton) findViewById(R.id.mine_icon);
		more = (UIButton) findViewById(R.id.mine_more);
		information = (UITableView) findViewById(R.id.mine_information);
		icon.addClickListener(this);
		selectPhoto = (Button) view.findViewById(R.id.selectPhoto);
		selectPhoto.setOnClickListener(this);
		uploadPhoto = (Button) view.findViewById(R.id.uploadPhoto);
		uploadPhoto.setOnClickListener(this);
		photograph = (Button) view.findViewById(R.id.camera);
		photograph.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mine_icon:
			showPopUpWindow(v);
			break;
		case R.id.camera:
			initPhotograph();
			break;
		case R.id.selectPhoto:
			selectPhoto();
			break;
		case R.id.uploadPhoto:
			uploadPhoto();
		default:
			break;
		}
	}

	private void uploadPhoto() {
		// TODO Auto-generated method stub
		if (picPath == null) {
			Toast.makeText(InformationActivity.this, "请选择图片！",
					Toast.LENGTH_SHORT).show();
		} else {
			File file = new File(picPath);
			if (file != null)
				Log.i("upload", "image");
			uploadutiltask = new UploadUtil();
			uploadutiltask.execute(file);
		}
	}

	private void selectPhoto() {
		// TODO Auto-generated method stub
		Log.i("select", "image");
		destoryBimap();
		Intent i = new Intent();
		i.setType("image/*");
		i.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(i, Activity.DEFAULT_KEYS_SEARCH_GLOBAL);
	}

	private void showPopUpWindow(View v) {
		// TODO Auto-generated method stub
		// 使其聚焦
		popup.setFocusable(true);
		// 设置允许在外点击消失
		popup.setOutsideTouchable(true);
		// 点击“返回back”,不会影响你的背景
		popup.setBackgroundDrawable(new BitmapDrawable());

		// 以下拉方式显示。
		// //popup.showAsDropDown(v);
		popup.showAsDropDown(v);
		popup.setAnimationStyle(R.style.AnimationPreview);
		// 将PopupWindow显示在指定位置
		// popup.showAtLocation(findViewById(R.id.mine_icon),
		// Gravity.CENTER, 0,
		// height/2);
	}

	private void initPhotograph() {
		// TODO Auto-generated method stub
		isSdState();
		creatPhotoDir();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
		photoUri = Uri.fromFile(getphotoAbsoluteDir());
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		startActivityForResult(intent, Activity.DEFAULT_KEYS_DIALER);
	}

	private void creatPhotoDir() {
		// TODO Auto-generated method stub
		photoDir = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/DCIM/Camera");
		if (!photoDir.exists()) {
			photoDir.mkdirs();
		}
	}

	private void isSdState() {
		// TODO Auto-generated method stub
		String sdState = Environment.getExternalStorageState();
		if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(InformationActivity.this, R.string.sd_card,
					Toast.LENGTH_SHORT).show();
			return;
		}
	}

	private File getphotoAbsoluteDir() {
		// TODO Auto-generated method stub
		formatPhotoName = new FormatPhotoName();
		photoName = formatPhotoName.getPhotoName(new Date());
		photoAbsoluteDir = new File(photoDir, photoName);
		return photoAbsoluteDir;
	}

	private void initTableView() {
		// TODO Auto-generated method stub
		information.addBasicItem("昵称");
		information.addBasicItem("姓名");
		information.addBasicItem("性别");
		information.addBasicItem("电话号码");
		information.commit();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			getResult(requestCode, data);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void getResult(int requestCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case Activity.DEFAULT_KEYS_DIALER:
			savePhoto();
			break;
		case Activity.DEFAULT_KEYS_SEARCH_GLOBAL:
			selectPic(data);
			break;
		}
	}

	private void selectPic(Intent data) {
		// TODO Auto-generated method stub
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

	private void savePhoto() {
		// TODO Auto-generated method stub
		Toast.makeText(this, R.string.save_uccess, Toast.LENGTH_SHORT).show();
		sendBroadcast(new Intent(
				Intent.ACTION_MEDIA_MOUNTED,
				Uri.parse("file://" + Environment.getExternalStorageDirectory())));
		showSavePhoto();
	}

	private void showSavePhoto() {
		// TODO Auto-generated method stub
		Bitmap photoBitmap;
		try {
			photoBitmap = MediaStore.Images.Media.getBitmap(
					getContentResolver(), photoUri);
			picPath = photoAbsoluteDir.toString();
			imageView.setImageBitmap(photoBitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showSelectPic(Cursor cursor, int index, Uri uri) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		ContentResolver cr = this.getContentResolver();
		try {
			bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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

	private class UploadUtil extends AsyncTask<Object, Object, Integer> {

		private final static String requestURL = "http://120.192.31.164:8993/api/uploading";

		public int uploadFile(File file) throws IOException {

			HttpClient httpclient = new DefaultHttpClient();

			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

			HttpPost httppost = new HttpPost(requestURL);
			MultipartEntity entity = new MultipartEntity();

			ContentBody cbFile = new FileBody(file);

			entity.addPart("userfile", cbFile);
			Log.v("selu", "http");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			@SuppressWarnings("unused")
			HttpEntity httpentity = response.getEntity();
			Log.v("selu", "http2");
			int stateCode = response.getStatusLine().getStatusCode();
			Log.v("selu", "http3" + stateCode);
			httpclient.getConnectionManager().shutdown();
			return stateCode;

		}

		@Override
		protected Integer doInBackground(Object... params) {
			// TODO Auto-generated method stub
			Log.v("selu", "http1");
			File file = (File) params[0];
			int resultcode = 0;
			try {
				resultcode = uploadFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return resultcode;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result == HttpStatus.SC_OK) {
				Toast.makeText(InformationActivity.this, "图片上传成功！",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(InformationActivity.this, "图片上传失败！",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
