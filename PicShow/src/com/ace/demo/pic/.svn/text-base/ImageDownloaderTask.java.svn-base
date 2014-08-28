package com.ace.demo.pic;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ace.demo.R;

public class ImageDownloaderTask  extends AsyncTask<String, Integer, Bitmap>{

	private final WeakReference<ImageView> _imageViewReference;
	private final Activity _ownerActivity;
	private final ImageDownloadHelper _helper;
	

	public ImageDownloaderTask(Activity ownerActivity, ImageView imageView) {
		_ownerActivity = ownerActivity;
		_imageViewReference = new WeakReference<ImageView>(imageView);
		_helper = new ImageDownloadHelper();
	}
	

	
	@Override
	protected void onPreExecute() {
		ProgressBar pb = (ProgressBar) _ownerActivity
			.findViewById(R.id.progressBarImageLoading);
		pb.bringToFront();
		pb.setVisibility(View.VISIBLE);
	}
	
	

	@Override
	protected void onPostExecute(Bitmap resultBmp) {
		if (isCancelled()) {
			resultBmp = null;
		}

		ProgressBar pb = (ProgressBar) _ownerActivity
		.findViewById(R.id.progressBarImageLoading);
		pb.setVisibility(View.INVISIBLE);
		
		if (resultBmp == null) {
			Toast tip = Toast.makeText(_ownerActivity, R.string.error_download_image, 5);
			tip.show();
			return;
		}
		

		if (_imageViewReference != null) {
			ImageView imageView = _imageViewReference.get();
			if (imageView != null && resultBmp != null) {
				imageView.setImageBitmap(resultBmp);
			}
		}
		
	}
	
	
	@Override
	protected Bitmap doInBackground(String... params) {
		try {
			Looper.myLooper();
			return _helper.loadImageFromUrl(params[0]);
		} catch (Exception e) {
			return null;
		}
		
	}

}
