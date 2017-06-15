package com.Vshop.core.image.gif;

import java.awt.AWTException;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import com.Vshop.core.image.ImageJDKUtil;


public class GifUtil {
	public static BufferedImage resize(BufferedImage srcImage, double wRatio, double hRatio) {
		BufferedImage dstImage = null;
		AffineTransform transform = AffineTransform.getScaleInstance(wRatio, hRatio);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		dstImage = op.filter(srcImage, null);
		return dstImage;
	}

	public static boolean isAnimateGif(String fileName) throws IOException {
		Iterator imageReaders = ImageIO.getImageReadersBySuffix("GIF");
		if (!imageReaders.hasNext()) {
			throw new IOException("no ImageReaders for GIF");
		}
		File file = new File(fileName);
		if (!file.exists()) {
			throw new IOException("file " + fileName + " is not exists");
		}
		ImageReader imageReader = (ImageReader) imageReaders.next();
		imageReader.setInput(ImageIO.createImageInputStream(file));
		int i = 0;
		while (i < 2) {
			try {
				imageReader.read(i);
			} catch (IndexOutOfBoundsException ex) {
				break;
			}
			++i;
		}
		imageReader.abort();
		return i >= 2;
	}

	public static void resizeByRate(String srcFile, OutputStream os, double wRatio, double hRatio) throws IOException,
			AWTException {
		GifDecoder decoder = new GifDecoder();
		decoder.read(srcFile);

		int count = decoder.getFrameCount();
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		e.start(os);
		e.setRepeat(decoder.getLoopCount());
		if (decoder.isTransparency()) {
			e.setTransparent(decoder.lastTransparencyColor);
		}
		for (int i = 0; i < count; ++i) {
			e.setDelay(decoder.getDelay(i));
			e.addFrame(resize(decoder.getFrame(i), wRatio, hRatio));
		}
		e.finish();
	}

	public static void resizeByRate(String srcFile, OutputStream os, int width, int height, boolean keepRate)
			throws IOException, AWTException {
		GifDecoder decoder = new GifDecoder();
		decoder.read(srcFile);

		int count = decoder.getFrameCount();
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		e.start(os);
		e.setRepeat(decoder.getLoopCount());
		if (decoder.isTransparency()) {
			e.setTransparent(decoder.lastTransparencyColor);
		}
		for (int i = 0; i < count; ++i) {
			double w = decoder.getFrame(i).getWidth();
			double h = decoder.getFrame(i).getHeight();
			double wr = 1, hr = 1;
			if (keepRate) {
				if (w > width && h > height) {
					if (height == 0) {
						if (w > width) {
							wr = hr = width / w;
						}
					} else if (width == 0) {
						if (h > height) {
							wr = hr = height / h;
						}
					} else if (w / h > width / height) {
						wr = hr = width / w;
					} else {
						wr = hr = height / h;
					}
				}
			} else {
				wr = width * 1.0 / w;
				hr = height * 1.0 / h;
			}
			e.setDelay(decoder.getDelay(i));
			e.addFrame(resize(decoder.getFrame(i), wr, hr));
		}
		e.finish();
	}

	public static void main(String[] args) {
		try {
//			ImageJDKUtil.scaleRateImageFile("F:/Document/My Pictures/澶嶄欢 1.gif", "G:/1.gif", 0.5);
//			ImageJDKUtil.scaleRateImageFile("F:/Document/My Pictures/澶嶄欢 1.gif", "G:/2.gif", 300, 300);
//			ImageJDKUtil.scaleFixedImageFile("F:/Document/My Pictures/澶嶄欢 1.gif", "G:/3.gif", 300, 300, false);
			
//			ImageJDKUtil.scaleRateImageFile("F:/Document/My Pictures/Lifestreams.png", "G:/1.png", 0.5);
//			ImageJDKUtil.scaleRateImageFile("F:/Document/My Pictures/Lifestreams.png", "G:/2.png", 300, 300);
//			ImageJDKUtil.scaleFixedImageFile("F:/Document/My Pictures/Lifestreams.png", "G:/3.png", 400, 200, false);
			
			ImageJDKUtil.scaleRateImageFile("F:/21.jpg", "G:/1.jpg", 0.5);			
			ImageJDKUtil.scaleRateImageFile("F:/21.jpg", "G:/2.jpg", 300, 300);
			ImageJDKUtil.scaleFixedImageFile("F:/21.jpg", "G:/3.jpg", 300, 300, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
