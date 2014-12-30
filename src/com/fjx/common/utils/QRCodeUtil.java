package com.fjx.common.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;

public final class QRCodeUtil {
	
	
	class J2SEImage implements QRCodeImage {
	    BufferedImage image;
	    
	    public J2SEImage(BufferedImage image) {
	        this.image = image;
	    }
	    
	    public int getWidth() {
	        return image.getWidth();
	    }
	    
	    public int getHeight() {
	        return image.getHeight();
	    }
	    
	    public int getPixel(int x, int y) {
	        return image.getRGB(x, y);
	    }
	    
	}
	
	/**
	 * 生成二维码
	 * 
	 * @param content 生成的内容
	 * @param createPath 生成的路径
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void createImg(String content, String createPath)
			throws UnsupportedEncodingException, IOException {
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeErrorCorrect('M');
		qrcode.setQrcodeEncodeMode('B');
		qrcode.setQrcodeVersion(7);
		byte[] d = content.getBytes("UTF-8");

		BufferedImage bi = new BufferedImage(139, 139,
				BufferedImage.TYPE_INT_RGB);

		// createGraphics
		Graphics2D g = bi.createGraphics();

		// set background
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 139, 139);

		g.setColor(Color.BLACK);

		if (d.length > 0 && d.length < 123) {
			boolean[][] b = qrcode.calQrcode(d);

			for (int i = 0; i < b.length; i++) {

				for (int j = 0; j < b.length; j++) {
					if (b[j][i]) {
						g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
					}
				}

			}
		}

		g.dispose();
		bi.flush();

		File f = new File(createPath);

		ImageIO.write(bi, "png", f);
		System.out.println("doned!");
	}
	
	/**
	 * 解析二维码
	 * @param filePath 解码文件路径
	 */
	public void imgTocontent(String filePath) {
		QRCodeDecoder decoder = new QRCodeDecoder();
        
        File imageFile = new File(filePath);
        
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Error: "+ e.getMessage());
        }
        try {
        	String decodedData = new String(decoder.decode(new J2SEImage(image)),"UTF-8");
            System.out.println(decodedData);
        } catch (DecodingFailedException dfe) {
			throw new RuntimeException(dfe);
        } catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成中间带LoGo的二维码
	 * @param content 二维码内容
	 * @param imgPath 生成图片的地址
	 * @param ccbPath logo图片地址
	 * @throws IOException 
	 */
	public void createQRCode(String content, String imgPath,
			String ccbPath) throws IOException {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('M');
			// N代表数字,A代表字符a-Z,B代表其他字符
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(7);

			byte[] contentBytes = content.getBytes("UTF-8");
			BufferedImage bufImg = new BufferedImage(139, 139,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 139, 139);

			// 设定图像颜色 > BLACK
			gs.setColor(Color.BLACK);

			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 123) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,125]. ");
			}
			Image img = ImageIO.read(new File(ccbPath));// 实例化一个Image对象。
			//gs.drawImage(img,61,50,null); //图片大小 20 * 20
			gs.drawImage(img,61,44,null);
			gs.dispose();
			bufImg.flush();

			// 生成二维码QRCode图片
			File imgFile = new File(imgPath);
			ImageIO.write(bufImg, "png", imgFile);
			System.out.println("doned!");
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		QRCodeUtil qrcode = new QRCodeUtil();
		
//		qrcode.createQRCode("你好sdafsdafsda啊但撒酒烧垃圾1111111111111111111112242@！vsdsd舒服撒@#%#￥&&%￥&￥afsa！", "D:/123logo.png", "D:/logo.png");
//		qrcode.imgTocontent("D:/123logo.png");
		
	}
}




