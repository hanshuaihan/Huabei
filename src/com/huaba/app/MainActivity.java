package com.huaba.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	public static final int TAKE_PHOTO=1;
	public static final int CROP_PHOTO=2;
	private Button paizhao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		paizhao=(Button)findViewById(R.id.paizhao);
		Button Baizhi=(Button)findViewById(R.id.Baizhi);
	Baizhi.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			Intent intent=new Intent(MainActivity.this,PaintActivity.class);
			startActivity(intent);
		}
	});
	paizhao.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			File outputImage=
		}
	}
	}
}

