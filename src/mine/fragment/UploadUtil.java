package mine.fragment;

import java.io.File;
import java.io.IOException;

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

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

class UploadUtil extends AsyncTask<Object, Object, Integer> {

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
		// if (result == HttpStatus.SC_OK) {
		// Toast.makeText(InformationActivity.this, "图片上传成功！",
		// Toast.LENGTH_SHORT).show();
		// } else {
		// Toast.makeText(InformationActivity.this, "图片上传失败！",
		// Toast.LENGTH_SHORT).show();
		// }
	}
}