package com.techroadians.earthquakeapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignUpForm {
	Context context;
	ProgressDialog pDialog;

	public SignUpForm(Context context) {
		super();
		this.context = context;
	}

	Dialog dialog;

	public void Form() {

		dialog = new Dialog(context);
		dialog.setTitle("Create Account");
		dialog.setContentView(R.layout.signup);
		Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
		dialog.show();
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new SendFormData().execute();

			}
		});

	}

	private class SendFormData extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Please wait");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... params) {

			try {
				// Data

				EditText etName = (EditText) dialog.findViewById(R.id.etName);
				EditText etLocation = (EditText) dialog
						.findViewById(R.id.etLocation);
				EditText etEmail = (EditText) dialog.findViewById(R.id.etEmail);
				EditText etPassword = (EditText) dialog
						.findViewById(R.id.etPassword);
				EditText etMobile = (EditText) dialog
						.findViewById(R.id.etMobileNo);

				/*
				 * ArrayList<NameValuePair> nameValuePairs = new
				 * ArrayList<NameValuePair>(); nameValuePairs.add(new
				 * BasicNameValuePair("name", etName .getText().toString()));
				 * nameValuePairs.add(new BasicNameValuePair("location",
				 * etLocation.getText().toString())); nameValuePairs.add(new
				 * BasicNameValuePair("email", etEmail .getText().toString()));
				 * nameValuePairs.add(new BasicNameValuePair("password",
				 * etPassword.getText().toString())); nameValuePairs.add(new
				 * BasicNameValuePair("mobileno", etMobile
				 * .getText().toString()));
				 */
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("name", "abc"));
				nameValuePairs.add(new BasicNameValuePair("location", "abc"));
				nameValuePairs.add(new BasicNameValuePair("email", "abc"));
				nameValuePairs.add(new BasicNameValuePair("password", "abc"));
				nameValuePairs.add(new BasicNameValuePair("mobileno", "123"));
				// Log.d("values", etName.getText().toString());

				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(Urls.URL_USER_REGISTER);

				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				Log.d("Nikesh Replies", response.toString());

			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			} catch (ClientProtocolException e) {

				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();

		}

	}
}
