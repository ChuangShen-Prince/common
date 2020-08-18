package com.sc.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {

	public static void main(String[] args) {
		String filePathSource = "E:\\二次开发\\2018下半年\\爬去站酷海洛表情图片\\test\\1";
		
		String fileNamePath = "E:\\二次开发\\2020\\天网系统\\地图测试数据\\全国乡镇边界去干扰.json";
		List<String> list = readFile(fileNamePath);
		String strJson = "";
		for (String string : list) {
			strJson = strJson + string;
		}
		
	}

	/**
	 * 下载图片，并按照指定的路径存储
	 * 
	 * @param bean
	 * @param filePath
	 */
	public static void makeImage(String urlString, String filename, String savePath) {
		// 网络请求所需变量
		try {
			// 获取输入流
			BufferedInputStream in = new BufferedInputStream(new URL(urlString).openStream());
			// 创建文件流
			File file = new File(savePath + "\\" + filename);
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			// 缓冲字节数组
			byte[] data = new byte[2048];
			int length = in.read(data);
			while (length != -1) {
				out.write(data, 0, data.length);
				length = in.read(data);
				out.flush();
			}
			System.out.println("正在执行下载任务：当前正在下载图片" + filename);
			in.close();
			out.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param urlString
	 * @param filename
	 * @param savePath
	 * @throws Exception
	 */
	public static String downloadImage(String urlString, String filename, String savePath) {
		// 输入流
		InputStream is = null;
		OutputStream os = null;
		try {
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024 * 50];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			os = new FileOutputStream(sf.getPath() + "/" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
				os.flush();
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
			return null;
		} catch (Exception e) {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return urlString;
		}
	}

	/**
	 * 输出数据
	 * 
	 * @param urlList
	 * @param fileName
	 */
	public static void writeFile(List<String> urlList, String fileName) {
		BufferedWriter bw = null;
		try {

			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));

			for (int i = 0; i < urlList.size(); i++) {
				String string = urlList.get(i);
				if (i == urlList.size() - 1) {
					bw.append(string + "\r\n");
				} else {
					bw.append(string + "\r\n");
				}
				bw.flush();
			}

			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输出数据
	 * 
	 * @param urlList
	 * @param fileName
	 */
	public static void writeFileByArr(String[] urlList, String fileName) {
		BufferedWriter bw = null;
		try {
			
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));
			
			for (int i = 0; i < urlList.length; i++) {
				System.out.println(i);
				String string = urlList[i];
				if (i == urlList.length - 1) {
					bw.append(string + "\r\n");
				} else {
					bw.append(string + "\r\n");
				}
				bw.flush();
			}
			
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出文本数据
	 * 
	 * @param urlList
	 * @param fileName
	 */
	public static void writeStringFile(String string, String fileName) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8"));
			bw.append(string);
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出数据
	 * 
	 * @param urlList
	 * @param fileName
	 */
	public static void writeReplaceFile(List<String> urlList, String fileName) {
		BufferedWriter bw = null;
		try {

			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));

			for (int i = 0; i < urlList.size(); i++) {
				String string = urlList.get(i);
				if (i == urlList.size() - 1) {
					bw.append(string);
				} else {
					bw.append(string + "\r\n");
				}
				bw.flush();
			}

			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param outFilename
	 * @param list
	 */
	public static void writerFile(String outFilename, List<String> list) {
		BufferedWriter bWriter = null;
		try {
			bWriter = new BufferedWriter(new FileWriter(new File(outFilename).getAbsoluteFile()));
			for (String string : list) {
				bWriter.write(string + "\r\n");
			}
			bWriter.flush();
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<String> readFile(String fileName) {

		List<String> listResult = new ArrayList<String>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName)); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line = "";
			while ((line = br.readLine()) != null) {
				listResult.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return listResult;
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<String> readFileBigData(String fileName) {

		List<String> listResult = new ArrayList<String>();
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
			BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024 * 1024);// 10M缓存
			String line = "";
			Integer num = 0;
			while (in.ready()) {
//				if (num == 20000) {
//					break;
//				}
				line = in.readLine();
				listResult.add(line);
				num++;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return listResult;
	}

	/**
	 * 
	 * @param oldPath
	 * @param newPath
	 * @throws IOException
	 */
	public static void copyFile(String oldPath, String newPath) throws IOException {
		File oldFile = new File(oldPath);
		File file = new File(newPath);
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);

		byte[] buffer = new byte[2097152];

		while ((in.read(buffer)) != -1) {
			out.write(buffer);
		}

	}

	/**
	 * 复制文件
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
			
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}

}
