package com.gxh.utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传
 * Servlet前需要添加注解 @MultipartConfig
 
 * @author bobzyh 
 */
public class FileUploadUtils {

	/**
	 * @param part	文件
	 * @param path	保存路径
	 * @return 保存成功返回文件名, 保存失败返回null
	 */
	public static String upload(Part part, String path) {
		String disposition = part.getHeader("Content-Disposition");
		//后缀
		String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);
		// 随机的生存一个32的字符串
		String filename = UUID.randomUUID() + suffix;

		File file = new File(path);
		//文件夹是否存在
		if (!file.exists()) {
			//新建文件夹
			file.mkdirs();
		}

		// 获取上传的文件名
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			//输入流 读取文件照片
			is = part.getInputStream();
			//输出 将文件照片写入指定指定
			fos = new FileOutputStream(path + "/" + filename);
			byte[] bty = new byte[1024];
			int length = 0;
			while ((length = is.read(bty)) != -1) {
				fos.write(bty, 0, length);
			}

			return filename;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
