package com.example.tailin.privatepage.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

/**
 * 自定义Drawable实现圆角图片
 * <p>
 * 用法：
 * Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.mv);
 * ImageView iv = (ImageView) findViewById(R.id.id_one);
 * iv.setImageDrawable(new RoundImageDrawable(bitmap));
 *
 * 相关类知识点
 * BitmapShader：http://blog.csdn.net/lmj623565791/article/details/41967509
 *
 */
public class RoundImageDrawable extends Drawable {

    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF rectF;

    public RoundImageDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        //参数说明：1.bitmap 2、3 TileMode（CLAMP 拉伸，REPEAT 重复，MIRROR 镜像）
        BitmapShader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP,
                TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {//设置绘制的范围
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {//关键的方法只有一个
        canvas.drawRoundRect(rectF, 30, 30, mPaint);
    }

    @Override
    public int getIntrinsicWidth() {//当view使用wrap_content时候，提供尺寸，默认是返回-1
        return mBitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}