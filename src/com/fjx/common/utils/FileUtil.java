package com.fjx.common.utils;

import java.io.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @version FileUtil.java 2014年9月16日
 */
public final class FileUtil {
	
	
	/**
	 * 创建文件目录
	 */
	public static void mkdir(File file){
		if (!file.exists()) {
			try {
				file.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 创建文件目录
	 */
	public static void mkdir(String path){
		File file = new File(path);
		mkdir(file);
	}

	/**
	 * Locale specific strings displayed to the user.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	protected static ResourceBundle labels = ResourceBundle.getBundle("com.Ostermiller.util.FileHelper",  Locale.getDefault());


	/**
	 * Move a file from one location to another.  An attempt is made to rename
	 * the file and if that fails, the file is copied and the old file deleted.
	 *
	 * If the destination file already exists, an exception will be thrown.
	 *
	 * @param from file which should be moved.
	 * @param to desired destination of the file.
	 * @throws java.io.IOException if an error occurs.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public static void move(File from, File to) throws IOException {
		move(from, to, false);
	}

	/**
	 * Move a file from one location to another.  An attempt is made to rename
	 * the file and if that fails, the file is copied and the old file deleted.
	 *
	 * @param from file which should be moved.
	 * @param to desired destination of the file.
	 * @param overwrite If false, an exception will be thrown rather than overwrite a file.
	 * @throws IOException if an error occurs.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public static void move(File from, File to, boolean overwrite) throws IOException {
		if (to.exists()){
			if (overwrite){
				if (!to.delete()){
					throw new IOException(
							MessageFormat.format(
									labels.getString("deleteerror"),
									(Object[]) new String[]{
											to.toString()
									}
							)
					);
				}
			} else {
				throw new IOException(
						MessageFormat.format(
								labels.getString("alreadyexistserror"),
								(Object[])new String[] {
										to.toString()
								}
						)
				);
			}
		}

		if (from.renameTo(to)) return;

		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to);
			copy(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
			if (!from.delete()){
				throw new IOException(
						MessageFormat.format(
								labels.getString("deleteoriginalerror"),
								(Object[])new String[] {
										from.toString(),
										to.toString()
								}
						)
				);
			}
		} finally {
			if (in != null){
				in.close();
				in = null;
			}
			if (out != null){
				out.flush();
				out.close();
				out = null;
			}
		}
	}

	/**
	 * Buffer size when reading from input stream.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	private final static int BUFFER_SIZE = 1024;

	/**
	 * Copy the data from the input stream to the output stream.
	 *
	 * @param in data source
	 * @param out data destination
	 * @throws IOException in an input or output error occurs
	 *
	 * @since ostermillerutils 1.00.00
	 */
	private static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}
	}

}
