package com.techroadians.earthquakeapp.adapter;


import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.techroadians.earthquake.DTO.ReviewListItemDto;
import com.techroadians.earthquakeapp.AppController;
import com.techroadians.earthquakeapp.R;

public class ListAdapter extends BaseAdapter{
	private final Context context;
	private final List<ReviewListItemDto> values;
	ReviewHolder holder;
	public ListAdapter(Context context,List<ReviewListItemDto> allReviewList) {
		super();
		// TODO Auto-generated constructor stub
		 this.context = context;
		 this.values = allReviewList;
	}
	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	      ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	  // First let's verify the convertView is not null
	     if (convertView == null) {
		       holder = new ReviewHolder();
	         // This a new view we inflate the new layout
	         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	         convertView = inflater.inflate(R.layout.review_list_elements, null);
	         // Now we can fill the layout with the right values
	          holder.review = (TextView) convertView.findViewById(R.id.reviewTextView);
	          holder.img = (ImageView) convertView.findViewById(R.id.listImageView);
	          convertView.setTag(holder);
	     }
	     else
	      holder = (ReviewHolder) convertView.getTag();
	     
	      System.out.println("Position ["+position+"]");
	     ReviewListItemDto p = values.get(position);
	     holder.review.setText(p.getReview());
	     Log.d("iMge Url", p.getImageUrl());
	     Log.d("Review", p.getReview());
	     imageLoader.get(p.getImageUrl(), ImageLoader.getImageListener(
					holder.img, R.drawable.ico_loading, R.drawable.ico_error));
	     //holder.img.setImageResource(p.getImageUrl());
	      
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
	    public TextView review;
	    public ImageView img;
	}
}
