package com.nadia.totoro.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ImageUtils {
	
	public static void createImageThumbnail(Context context, String largeImagePath, String thumbfilePath, int squareSize, int quality) throws IOException {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = 1;
		Bitmap cur_bitmap = getBitmapByPath(largeImagePath, opts);
		if (cur_bitmap != null) {
			int[] cur_imgSize = new int[]{cur_bitmap.getWidth(), cur_bitmap.getHeight()};
			int[] new_imgSize = scaleImageSize(cur_imgSize, squareSize);
			Bitmap thb_bitmap = zoomBitmap(cur_bitmap, new_imgSize[0], new_imgSize[1]);
			saveImageToSD(thumbfilePath, thb_bitmap, quality);
		}
	}
	
	public static Bitmap getBitmapByPath(String filePath, BitmapFactory.Options opts) {
		FileInputStream fis = null;
		Bitmap bitmap = null;
		
		try {
			File e = new File(filePath);
			fis = new FileInputStream(e);
			bitmap = BitmapFactory.decodeStream(fis, (Rect) null, opts);
		} catch (FileNotFoundException var15) {
			var15.printStackTrace();
		} catch (OutOfMemoryError var16) {
			var16.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}
	
	public static int[] scaleImageSize(int[] imgSize, int squareSize) {
		if (imgSize[0] <= squareSize && imgSize[1] <= squareSize) {
			return imgSize;
		} else {
			double ratio = (double) squareSize / (double) Math.max(imgSize[0], imgSize[1]);
			return new int[]{(int) ((double) imgSize[0] * ratio), (int) ((double) imgSize[1] * ratio)};
		}
	}
	
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		Bitmap newbmp = null;
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			Matrix matrix = new Matrix();
			float scaleWidth = (float) w / (float) width;
			float scaleHeight = (float) h / (float) height;
			matrix.postScale(scaleWidth, scaleHeight);
			newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		}
		return newbmp;
	}
	
	public static void saveImageToSD(String filePath, Bitmap bitmap, int quality) throws IOException {
		if (bitmap != null) {
			FileOutputStream fos = new FileOutputStream(filePath);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
			byte[] bytes = stream.toByteArray();
			fos.write(bytes);
			fos.close();
		}
	}
	
	/**
	 * 模糊图片的具体方法
	 *
	 * @param context 上下文对象
	 * @param image   需要模糊的图片
	 * @return 模糊处理后的图片
	 */
	//图片缩放比例
	private static final float BITMAP_SCALE = 0.4f;
	
	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
	public static Bitmap blurBitmap(Context context, Bitmap image, float blurRadius) {
		// 计算图片缩小后的长宽
		int width = Math.round(image.getWidth() * BITMAP_SCALE);
		int height = Math.round(image.getHeight() * BITMAP_SCALE);
		
		// 将缩小后的图片做为预渲染的图片
		Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
		// 创建一张渲染后的输出图片
		Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
		
		// 创建RenderScript内核对象
		RenderScript rs = RenderScript.create(context);
		// 创建一个模糊效果的RenderScript的工具对象
		ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
		
		// 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
		// 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
		Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
		Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
		
		// 设置渲染的模糊程度, 25f是最大模糊度
		blurScript.setRadius(blurRadius);
		// 设置blurScript对象的输入内存
		blurScript.setInput(tmpIn);
		// 将输出数据保存到输出内存中
		blurScript.forEach(tmpOut);
		
		// 将数据填充到Allocation中
		tmpOut.copyTo(outputBitmap);
		
		return outputBitmap;
	}
}
