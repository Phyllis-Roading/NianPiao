package mine.fragment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.basilwang.niaopiao.R;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.HttpVersion;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.ContentBody;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.CoreProtocolPNames;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
//import android.widget.Toast;

public class IconUploadActivity extends Activity implements OnClickListener{

	private Button selectImage,uploadImage;
	private ImageView imageView;
	private Bitmap bitmap;
	private ProgressBar mprogressbar;
	private TextView mtextview;
	private String picPath = null;
	//private final Builder builder=new AlertDialog.Builder(this);
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iconupload_main);
        
        selectImage = (Button) this.findViewById(R.id.selectImage);
        uploadImage = (Button) this.findViewById(R.id.uploadImage);
        selectImage.setOnClickListener(this);
        uploadImage.setOnClickListener(this);
        imageView = (ImageView) this.findViewById(R.id.imageView);      
    }
    
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.selectImage:
			//destoryBimap();
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(intent, 1);
			break;
		case R.id.uploadImage:
			File file = new File(picPath);
			if(file!=null)
    		{
				//�����view���ϴ���ȵĵ���
				View uploadingView = getLayoutInflater().inflate(R.layout.iconuploading_progress, null);
				mprogressbar = (ProgressBar)uploadingView.findViewById(R.id.progressBar);
				mtextview = (TextView)uploadingView.findViewById(R.id.textView);
				new AlertDialog.Builder(IconUploadActivity.this).setTitle("ͼƬ�ϴ����").setView(uploadingView).create().show();
//				builder.setTitle("ͼƬ�ϴ����");
//				builder.setView(uploadingView);
//				builder.create().show();
				UploadUtil uploadutiltask=new UploadUtil();
				uploadutiltask.execute(file);				
			}
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==Activity.RESULT_OK)
		{
			Uri uri = data.getData();
			try {
				String[] pojo = {MediaStore.Images.Media.DATA};
				
				Cursor cursor = managedQuery(uri, pojo, null, null,null);
				if(cursor!=null)
				{
					ContentResolver cr = this.getContentResolver();
					int colunm_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					String path = cursor.getString(colunm_index);
					if(path.endsWith("jpg")||path.endsWith("png")||path.endsWith("gif"))
					{
						picPath = path;
					    bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
						imageView.setImageBitmap(bitmap);
//					    Intent i=new Intent(this, InformationActivity.class);
//						i.putExtra("bitmap", bitmap);
//						startActivity(i);
					}else{alert();}
				}else{alert();}
			
			} catch (Exception e) {
			}
		}
		 
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void alert()
    {
    	Dialog dialog = new AlertDialog.Builder(this)
		.setTitle("��ʾ")
		.setMessage("��ѡ��Ĳ�����Ч��ͼƬ")
		.setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						picPath = null;
					}
				})
		.create();
		dialog.show();
    }
	
//	private void destoryBimap() {
//		if (bitmap != null && !bitmap.isRecycled()) {
//		bitmap.recycle();
//		bitmap = null;
//		}
//		}
	private class UploadUtil extends AsyncTask<Object, Integer, String>{

		
		private final static String requestURL = "http://120.192.31.164:8993/api/uploading";
//		private long filelength;
//	    public int uploadFile(File file) throws IOException {
//			
//	    	  
//		      HttpClient httpclient = new DefaultHttpClient();
//		
//		      //����ͨ��Э��汾
//		
//		     httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//		 
//		      HttpPost httppost = new HttpPost(requestURL);
//		      
//		      MultipartEntity entity = new MultipartEntity(); //�ļ�����
//		      
//		      ContentBody cbFile = new FileBody(file);
//		
//		      entity.addPart("userfile", cbFile); 
//		      
//		      Log.v("selu", "http");
//		      httppost.setEntity(entity);
//		      
//		      HttpResponse response = httpclient.execute(httppost);
//		      @SuppressWarnings("unused")
//			  HttpEntity httpentity=response.getEntity();
//		      Log.v("selu", "http2");
//		      int stateCode = response.getStatusLine().getStatusCode();
//		      Log.v("selu", "http3"+stateCode);
//		      
//		      //publishProgress((int) ((filelength / (float)filelength) * 100));
//		      httpclient.getConnectionManager().shutdown();
//		      return stateCode;
//			
//		    }

		@Override
		protected String doInBackground(Object... params) {
			// TODO Auto-generated method stub
			 Log.v("selu", "http1");
			File file=(File)params[0];
			String end = "\r\n";
			String twoHyphens = "--";
			String boundary = "******";
			try {
				URL url = new URL(requestURL);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url
						.openConnection();
				httpURLConnection.setDoInput(true);
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setUseCaches(false);
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setConnectTimeout(6*1000);
				httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
				httpURLConnection.setRequestProperty("Charset", "UTF-8");
				httpURLConnection.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + boundary);

				DataOutputStream dos = new DataOutputStream(httpURLConnection
						.getOutputStream());
				dos.writeBytes(twoHyphens + boundary + end);
				dos
						.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\""
								+ file.toString().substring(file.toString().lastIndexOf("/") + 1)
								+ "\"" + end);
				dos.writeBytes(end);

				FileInputStream fis = new FileInputStream(file);
				long total = fis.available();
				String totalstr = String.valueOf(total);
				Log.d("�ļ���С", totalstr);
				byte[] buffer = new byte[8192]; // 8k
				int count = 0;
				int length = 0;
				while ((count = fis.read(buffer)) != -1) {
					dos.write(buffer, 0, count);
					length += count;
					publishProgress((int) ((length / (float) total) * 100));
					//Ϊ����ʾ���,����500����
					Thread.sleep(300);
				}			
				fis.close();
				dos.writeBytes(end);
				dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
				dos.flush();

				InputStream is = httpURLConnection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "utf-8");
				BufferedReader br = new BufferedReader(isr);
				@SuppressWarnings("unused")
				String result = br.readLine();
				dos.close();
				is.close();
				return "ͼƬ�ϴ��ɹ���";
		}catch (Exception e) {
			e.printStackTrace();
			return "ͼƬ�ϴ�ʧ�ܣ�";
		}	
			
	}
       
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			mtextview.setText(result);
		
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			//mtextview.setText();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			mprogressbar.setProgress(values[0]);
			mtextview.setText("loading..." + values[0] + "%");
		}
		}

//		@Override
//		protected void onPostExecute(Integer result) {
//			// TODO Auto-generated method stub
//  		    super.onPostExecute(result);
//  		    if(result==HttpStatus.SC_OK){
//  			    Toast.makeText(MainActivity.this,"ͼƬ�ϴ��ɹ���" ,Toast.LENGTH_SHORT).show();
//  		    }else{
//  			     Toast.makeText(MainActivity.this,"ͼƬ�ϴ�ʧ�ܣ�" ,Toast.LENGTH_SHORT).show();
//  		         }
//  			
//		}
		
 	    
	}


