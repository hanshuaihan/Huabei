package com.huaba.app;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.provider.MediaStore.Images.Media;

public class SecondActivity extends Activity implements OnTouchListener,
OnClickListener{
	private Button choose;
	private Button baocun;
	private ImageView picture;
	private final static int RESULT = 0;
    private Paint paint;
    private Canvas canvas;
    private Bitmap bitmap;
    private Bitmap alterBitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second);
		choose=(Button)findViewById(R.id.choose);
		baocun=(Button)findViewById(R.id.baocun);
		picture=(ImageView)findViewById(R.id.picture);
		choose.setOnClickListener(this);
		baocun.setOnClickListener(this);
		picture.setOnTouchListener(this);
	}
	public void onClick(View arg0) {
        if(arg0==choose){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT);
        }else if(arg0==baocun){
            //保存画好的图片
            if(alterBitmap!=null){
                try {
                    Uri imageUri=getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());
                    OutputStream outputStream=getContentResolver().openOutputStream(imageUri);
                    alterBitmap.compress(CompressFormat.PNG, 90, outputStream);
                    Toast.makeText(getApplicationContext(), "save!", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	private float downx = 0;
    private float downy = 0;
    private float upx = 0;
    private float upy = 0;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
            downx = event.getX();
            downy = event.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            // 路径画板
            upx = event.getX();
            upy = event.getY();
            canvas.drawLine(downx, downy, upx, upy, paint);
            picture.invalidate();
            downx = upx;
            downy = upy;
            break;
        case MotionEvent.ACTION_UP:
            // 直线画板

            upx = event.getX();
            upy = event.getY();
            canvas.drawLine(downx, downy, upx, upy, paint);
            picture.invalidate();// 刷新
            break;

        default:
            break;
        }

        return true;
	}
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageFileUri = data.getData();
            Display display = getWindowManager().getDefaultDisplay();
            float dw = display.getWidth();
            float dh = display.getHeight();

            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(imageFileUri), null, options);
                int heightRatio = (int) Math.ceil(options.outHeight / dh);
                int widthRatio = (int) Math.ceil(options.outWidth / dw);
                if (heightRatio > 1 && widthRatio > 1) {
                    if (heightRatio > widthRatio) {
                        options.inSampleSize = heightRatio;
                    } else {
                        options.inSampleSize = widthRatio;
                    }
                }
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(imageFileUri), null, options);
                alterBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                        bitmap.getHeight(), bitmap.getConfig());
                canvas = new Canvas(alterBitmap);
                paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(10);
                Matrix matrix = new Matrix();
                canvas.drawBitmap(bitmap, matrix, paint);
                picture.setImageBitmap(alterBitmap);
                picture.setOnTouchListener(this);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }

        }
    }
}