package com.ace.demo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.ace.demo.pic.ImageDownloader;
import com.ace.demo.pic.SimpleImageViewListAdapter;

public class PicShow extends Activity implements ViewFactory, OnItemClickListener {
	SimpleImageViewListAdapter _imageListAdapter = new SimpleImageViewListAdapter();
	ImageSwitcher _mainImageSwitcher;
	Dialog _urlInputDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);		
		InitUIComponents();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result =  super.onCreateOptionsMenu(menu);
		menu.add(0, R.string.download_image, 0, R.string.download_image);
		return result;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.string.download_image) {
			ShowInputImageURLDialog();
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public View makeView() {
		ImageView iv = new ImageView(this);
		iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT));
		return iv;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		ImageView iv = (ImageView)_imageListAdapter.getItem(position);
		_mainImageSwitcher.setImageDrawable(iv.getDrawable());
	}
	
	

	private void ShowInputImageURLDialog() {
		createInputURLDialog();
		
		Button goBtn = (Button)_urlInputDialog.findViewById(R.id.buttonGo);
		goBtn.setOnClickListener(new Button.OnClickListener() {


			@Override
			public void onClick(View arg0) {

				EditText editUrl = (EditText) _urlInputDialog.findViewById(R.id.editUrl);
				downLoadImageByUrl(editUrl.getText().toString());
				_urlInputDialog.dismiss();
			}

			private  void downLoadImageByUrl(String imageUrl) {
		
					ImageView imageView = new ImageView(getApplicationContext());
					ImageDownloader downloader = new ImageDownloader(
							PicShow.this,imageView);
					downloader.download(imageUrl);
					
					imageView.setAdjustViewBounds(true);
					imageView.setLayoutParams(new Gallery.LayoutParams(
					     LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					_imageListAdapter.addImage(imageView);			
					_imageListAdapter.notifyDataSetChanged();
			}
			
			
		});
		
		_urlInputDialog.show();
	}


	private void createInputURLDialog() {
		_urlInputDialog = new Dialog(this);
		_urlInputDialog.setContentView(R.layout.url_input_dialog);
		_urlInputDialog.setTitle(R.string.input_dialog_title);
		_urlInputDialog.setCancelable(true);
		_urlInputDialog.setCanceledOnTouchOutside(true);
	}

	


	private void InitUIComponents() {
		_mainImageSwitcher = (ImageSwitcher)this.findViewById(R.id.imageSwitcherMain);
        _mainImageSwitcher.setFactory(this);
        _mainImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        _mainImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        Gallery g = (Gallery)findViewById(R.id.galleryMain);
		g.setAdapter(_imageListAdapter);
        g.setOnItemClickListener(this);
        
        Toast tip = Toast.makeText(this, "Please press MENU to download image", Toast.LENGTH_LONG);
        tip.show();
        
	}

}