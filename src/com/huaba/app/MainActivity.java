package com.huaba.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.huaba.app.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final int TAKE_PHOTO=1;
	public static final int CROP_PHOTO=2;
	private Button paizhao;
	private Uri imageUri;
	private ImageView tu;
	public static final int CHOOSE_PHOTO=3;
	private Button tuku;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Button paizhao=(Button)findViewById(R.id.paizhao);
		Button Baizhi=(Button)findViewById(R.id.Baizhi);
		tuku=(Button)findViewById(R.id.tuku);
		Baizhi.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			Intent intent=new Intent(MainActivity.this,PaintActivity.class);
			startActivity(intent);
		}
	});
	//œ‘ ΩINTENTµΩPaintActivity¿‡
	tuku.setOnClickListener(new OnClickListener(){
    	@Override
    	public void onClick(View v){
    		Intent intent=new Intent(MainActivity.this,SecondActivity.class);
    		startActivity(intent);
		}
	});
	}
				}

