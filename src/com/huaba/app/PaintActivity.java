package com.huaba.app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
public class PaintActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		BrushView view=new BrushView(this);
		setContentView(view);
		addContentView(view.btnEraseAll, view.params);
	}
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
