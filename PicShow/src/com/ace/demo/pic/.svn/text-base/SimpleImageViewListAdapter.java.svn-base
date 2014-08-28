package com.ace.demo.pic;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SimpleImageViewListAdapter extends BaseAdapter {

	private List<ImageView> _imageViewList = new ArrayList<ImageView>();


	public void addImage(ImageView imageView) {   
		_imageViewList.add(imageView);
	}
	
	@Override
	public int getCount() {
		return _imageViewList.size();
	}

	@Override
	public Object getItem(int index) {
		return _imageViewList.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parentView) {
		return _imageViewList.get(index);
	}

}
