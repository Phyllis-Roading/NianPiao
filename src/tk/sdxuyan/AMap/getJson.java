package tk.sdxuyan.AMap;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.amap.api.maps.model.LatLng;

public class getJson extends AsyncTask<Object, Object, ArrayList<LatLng>> {

	ArrayList<LatLng> jList = new ArrayList<LatLng>();
	ArrayList<LatLng> result = new ArrayList<LatLng>();

	@Override
	protected void onPostExecute(ArrayList<LatLng> result) {
		// TODO Auto-generated method stub
	}

	@Override
	protected ArrayList<LatLng> doInBackground(Object... params) {
		// TODO Auto-generated method stub
		String url = (String) params[0];
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = jsons(EntityUtils.toString(response.getEntity()));
			}
		} catch (Exception e) {

		}
		return result;
	}

	public ArrayList<LatLng> getResult() {
		return result;
	}

	public ArrayList<LatLng> jsons(String json) {
		try {
			JSONArray array = new JSONArray(json);
			for (int i = 0; i < array.length(); i++) {
				JSONObject temp = (JSONObject) array.get(i);
				LatLng point = new LatLng(temp.getDouble("latitude"), temp.getDouble("longitude"));
				jList.add(point);
			}
		} catch (Exception e) {

		}
//		Log.v("json",""+jList.get(2).latitude);
		return jList;
	}
}
