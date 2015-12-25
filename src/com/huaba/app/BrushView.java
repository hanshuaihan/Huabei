package com.huaba.app;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;

public class BrushView extends View {
	private Paint brush = new Paint();
	private Path path = new Path();
	public Button btnEraseAll;
	public LayoutParams params;
	public ImageView tu;
	public LayoutParams param;

	public BrushView(Context context) {
		super(context);
		brush.setAntiAlias(true);
		brush.setColor(Color.BLACK);
		brush.setStyle(Paint.Style.STROKE);
		brush.setStrokeJoin(Paint.Join.ROUND);
		brush.setStrokeWidth(15f);

		tu=new ImageView(context);
		param = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT );
		tu.setLayoutParams(param);
		
		
		btnEraseAll = new Button(context);
		btnEraseAll.setText("÷ÿª≠£°");
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT );
		btnEraseAll.setLayoutParams(params);
		btnEraseAll.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// reset the path
				path.reset();
				// invalidate the view
				postInvalidate();

			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float pointX = event.getX();
		float pointY = event.getY();

		// Checks for the event that occurs
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(pointX, pointY);

			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(pointX, pointY);
			break;
		case MotionEvent.ACTION_UP:
			break;
		default:
			return false;
		}
		// Force a view to draw.
		postInvalidate();
		return false;

	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, brush);
	}
}
