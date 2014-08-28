package com.ace.demo.pic;

import android.app.Activity;
import android.widget.ImageView;

public class ImageDownloader {
	private ImageDownloaderTask _task;
	public ImageDownloader(Activity ownerActivity, ImageView imageView) {
		_task = new ImageDownloaderTask(ownerActivity, imageView);
	}
	public void download(String url) {
		_task.execute(url);
	}
}
