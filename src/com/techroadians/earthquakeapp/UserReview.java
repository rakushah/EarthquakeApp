package com.techroadians.earthquakeapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.techroadians.earthquake.DTO.ReviewListItemDto;
import com.techroadians.earthquakeapp.adapter.ListAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class UserReview extends Activity{
	ListAdapter listAdapter;
	ListView userReviewList;
	List<ReviewListItemDto> userReviewListData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_list);
		userReviewList=(ListView) findViewById(R.id.ListReview);
		userReviewListData= new ArrayList<ReviewListItemDto>();
		String[] image_url={"http://androidexample.com/media/webservice/LazyListView_images/image4.png",
		                            "http://androidexample.com/media/webservice/LazyListView_images/image5.png"};
		String[] review={"Not safe","Safe after maintaince"};
		for (int i = 0; i < image_url.length; i++) {
			ReviewListItemDto dto=new ReviewListItemDto();
			dto.setReview(review[i]);
			dto.setImageUrl(image_url[i]);
			Log.d("Review calss",image_url[i] );
			userReviewListData.add(dto);
		}
		// Create the Adapter
		listAdapter=new ListAdapter(this,userReviewListData);
        
        // Set The Adapter to ListView
		userReviewList.setAdapter(listAdapter);
	}
	
	public class MakeHttpRequest extends AsyncTask<Void, Void, String>
	{	private ProgressDialog pd = new ProgressDialog(UserReview.this);
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd.setMessage("Please Wait...");
            pd.show();
		}
		@Override
		protected String doInBackground(Void... params) {
			InputStream inputStream = null;
	        String result = "";
	        try {
	 
	            // create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	 
	            // make GET request to the given URL
	            HttpResponse httpResponse = httpclient.execute(new HttpGet(Config.USER_REVIEW_ONLY_URL));
	 
	            // receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            // convert inputstream to string
	            if(inputStream != null)
	                result = convertInputStreamToString(inputStream);
	            else
	                result = "Did not work!";
	 
	        } catch (Exception e) {
	            Log.d("InputStream", e.getLocalizedMessage());
	        }
			// TODO Auto-generated method stub
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			 pd.hide();
	         pd.dismiss();
		}
	}
	  // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
 
}
