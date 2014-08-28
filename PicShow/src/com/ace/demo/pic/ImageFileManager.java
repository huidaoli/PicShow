package com.ace.demo.pic;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

public class ImageFileManager {
	
	private Context _context;
	
	public ImageFileManager(Context customContext) {
		_context = customContext;
	}

	public boolean save(Bitmap bmp) {
		boolean result;
		try {
			String filePath = Environment.getExternalStorageDirectory().toString();
			File file = new File(filePath, "a.bmp");
			FileOutputStream fos = new FileOutputStream(file);
			result =  bmp.compress(CompressFormat.PNG,	90, fos);
			fos.flush();
			fos.close();
		}
		catch (Exception e) {
			Log.e("imageFileManager", e.getMessage());
			result = false;
		}
		
		return result;
	}

}
