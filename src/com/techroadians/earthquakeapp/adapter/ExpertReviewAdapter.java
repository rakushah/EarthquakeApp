package com.techroadians.earthquakeapp.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.techroadians.earthquake.DTO.ExpertReviewListItemDTO;
import com.techroadians.earthquake.DTO.ReviewListItemDto;
import com.techroadians.earthquakeapp.AppController;
import com.techroadians.earthquakeapp.R;

public class ExpertReviewAdapter extends BaseAdapter{

	private final Context context;
	private final List<ExpertReviewListItemDTO> values;
	ReviewHolder holder;
	String expertReviewComment;
	public ExpertReviewAdapter(Context context,List<ExpertReviewListItemDTO> expertReviewList) {
		super();
		// TODO Auto-generated constructor stub
		 this.context = context;
		 this.values = expertReviewList;
	}
	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	      ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	  // First let's verify the convertView is not null
	     if (convertView == null) {
		       holder = new ReviewHolder();
	         // This a new view we inflate the new layout
	         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	         convertView = inflater.inflate(R.layout.activity_expert, null);
	         // Now we can fill the layout with the right values
	          holder.Username = (TextView) convertView.findViewById(R.id.usernameTextView);
	          holder.img = (ImageView) convertView.findViewById(R.id.listImageView);
	          holder.submitReview=(Button) convertView.findViewById(R.id.reviewButton);
	          convertView.setTag(holder);
	     }
	     else
	      holder = (ReviewHolder) convertView.getTag();
	     
	     ExpertReviewListItemDTO p = values.get(position);
	     holder.Username.setText(p.getUserName());
	   
	     imageLoader.get(p.getImageURL(), ImageLoader.getImageListener(
					holder.img, R.drawable.ico_loading, R.drawable.ico_error));
	     //holder.img.setImageResource(p.getImageUrl());
	     holder.submitReview.setFocusable(true);
	      holder.submitReview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final AlertDialog.Builder alert = new AlertDialog.Builder(
						context);
				final EditText input = new EditText(context);
				alert.setView(input);
				alert.setPositiveButton("Send",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								expertReviewComment = input.getText()
										.toString().trim();
								Toast.makeText(context,
										expertReviewComment,
										Toast.LENGTH_SHORT).show();
							}
						});

				alert.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.cancel();
							}
						});
				alert.show();
			}
		});
	     return convertView;
	 }
	 
	  
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	private static class ReviewHolder {
	    public TextView Username;
	    public ImageView img;
	    public Button submitReview;
	}

}
