package com.mysiteforme.admin.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 通过Java HTTP连接将网络图片下载到本地
 * 
 * @author: CB
 * @createDate: 2017-2-13 下午03:12:20
 */
public class DownLoadImageUtil {
	private static Log logger = LogFactory.getLog(DownLoadImageUtil.class.getName());
	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://image1.ljcdn.com/440300-inspection/9941c676-aacf-424b-b0ca-1f2ab4b2eea6.JPG.232x174.jpg";
		byte[] btImg = getImageFromNetByUrl(url);
		if (null != btImg && btImg.length > 0) {
			System.out.println("读取到：" + btImg.length + " 字节");
			String fileName = "lianjia.jpg";
			writeImageToDisk(btImg, "C:\\", fileName);
		} else {
			logger.error("没有从该连接获得内容");
		}
	}

	/**
	 * 将图片写入到磁盘
	 * 
	 * @param img
	 *            图片数据流
	 * @param fileName
	 *            文件保存时的名称
	 */
	public static boolean writeImageToDisk(byte[] img, String folderPath, String fileName) {
		try {
			File file = new File(folderPath + fileName);
			if (file.exists()) {
				return false;
			} else {
				// 判断目标文件所在的目录是否存在
				if (!file.getParentFile().exists()) {
					// 如果目标文件所在的目录不存在，则创建父目录
					file.getParentFile().mkdirs();
				}
			}
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
			logger.info("图片已经写入:" + file.getAbsolutePath());
			return true;
		} catch (Exception e) {
			logger.error("图片写入失败");
		}
		return false;
	}

	public static boolean writeImageToDisk(byte[] img, String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				return false;
			} else {
				// 判断目标文件所在的目录是否存在
				if (!file.getParentFile().exists()) {
					// 如果目标文件所在的目录不存在，则创建父目录
					file.getParentFile().mkdirs();
				}
			}
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
			logger.info("图片已经写入:" + file.getAbsolutePath());
			return true;
		} catch (Exception e) {
			logger.error("图片写入失败");
		}
		return false;
	}
	
	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			logger.error("FileNotFound: " + strUrl);
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		/**
		 * 设置缓存区大小 1024字节
		 */
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}
