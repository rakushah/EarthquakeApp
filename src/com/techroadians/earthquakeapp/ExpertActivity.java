package com.techroadians.earthquakeapp;

import java.util.ArrayList;
import java.util.List;

import com.techroadians.earthquake.DTO.ExpertReviewListItemDTO;
import com.techroadians.earthquake.DTO.ReviewListItemDto;
import com.techroadians.earthquakeapp.adapter.ExpertReviewAdapter;
import com.techroadians.earthquakeapp.adapter.ListAdapter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ExpertActivity extends ActionBarActivity {
	ExpertReviewAdapter listAdapter;
	ListView userReviewList;
	List<ExpertReviewListItemDTO> expertReviewList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_list);
		userReviewList=(ListView) findViewById(R.id.ListReview);
		expertReviewList= new ArrayList<ExpertReviewListItemDTO>();
		String[] image_url={"http://androidexample.com/media/webservice/LazyListView_images/image0.png",
		                            "http://androidexample.com/media/webservice/LazyListView_images/image1.png",
		                            "http://androidexample.com/media/webservice/LazyListView_images/image2.png",
		                            "http://androidexample.com/media/webservice/LazyListView_images/image3.png",
		                            "http://androidexample.com/media/webservice/LazyListView_images/image4.png",
		                            "http://androidexample.com/media/webservice/LazyListView_images/image5.png"};
		String[] namePerson={"Ram","Shyam","hari","Sanam","Rajkumar","Krishna"};

		for (int i = 0; i < image_url.length; i++) {
			ExpertReviewListItemDTO dto=new ExpertReviewListItemDTO();
			dto.setImageURL(image_url[i]);
			dto.setUserName(namePerson[i]);
			expertReviewList.add(dto);
		}
		
		// Create the Adapter
		listAdapter=new ExpertReviewAdapter(this,expertReviewList);
        
        // Set The Adapter to ListView
		userReviewList.setAdapter(listAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expert, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
