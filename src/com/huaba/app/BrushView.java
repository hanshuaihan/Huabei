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
		////继承自android.view.View的Java类创建一个定制的视图
		brush.setAntiAlias(true);
		brush.setColor(Color.BLACK);
		brush.setStyle(Paint.Style.STROKE);
		brush.setStrokeJoin(Paint.Join.ROUND);
		brush.setStrokeWidth(15f);

		tu=new ImageView(context);
		param = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT );
		tu.setLayoutParams(param);
		//android.graphics.Paint类。该类有我们所需要的方法来在canvas上画图该类负责封装复合的几何路径，可以由直线线段、二次曲线、三次曲线组成
		
		btnEraseAll = new Button(context);
		btnEraseAll.setText("重画！");
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
//Button类对象。同时用LayoutParams对象来设置按钮参数.简单的重置按钮.简单的点击事件
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
		
		postInvalidate();
		return false;
//onTouchEvent事件一个Path对象只封装一个路径。我们需要给路径增加点，用来描绘用户触碰屏幕和拖动他的手指。所以我们需要处理View的一些事件，它们能告诉我们用户正触摸的区域。
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, brush);
	}
}
