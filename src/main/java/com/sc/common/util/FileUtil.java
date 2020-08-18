package com.sc.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {
	public static List<String> FileToList(String filename) {
		List<String> list = new ArrayList<String>();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(filename).getAbsoluteFile()),
					"UTF-8"));
			while ((line = br.readLine()) != null) {
				list.add(line.toLowerCase());
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String LocalFileToString(String filename) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader br = null;
		try {
			InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream(filename);
			br = new BufferedReader(new InputStreamReader(resourceAsStream,"UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	
	public static List<String> LocalFileToList(String name) {
		List<String> list =new ArrayList<String>();
		
		BufferedReader br = null;
		try {
			InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream(name);
			br = new BufferedReader(new InputStreamReader(resourceAsStream,"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	/***
	 * hpo文件：hpoid，英文名称，中文名称
	 * 
	 * @return hpoid，英文名称，中文名称的组合
	 * 
	 */
	public static Map<String, Object> LocalFileToMap(String name) {
		Map<String, Object> subclassMap = new LinkedHashMap<String, Object>();
		BufferedReader br = null;
		try {
			InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream(name);
			br = new BufferedReader(new InputStreamReader(resourceAsStream,"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\t",2);
				subclassMap.put(split[0], split[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return subclassMap;
	}

	
	public static void writeFile(String filePath, String content){
		BufferedWriter bw = null ;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/***
	 * 
	 * <p>Title: 遍历文件</p>
	 * <p>Description: 遍历指定目录的下的所有文件，包括文件夹下的文件</p>
	 * <p>Company: CapitalBio Corporation. </p>
	 * @author: guohuiyang
	 * @date: 2017年1月6日 上午10:43:09
	 * 	
	 * @param dirPath 目录名称
	 * @return
	 *
	 */
	public static List<File> getAllFiles(String dirPath) {
		File dir = new File(dirPath);
		ArrayList<File> files = new ArrayList<File>();

		if (dir.isDirectory()) {
			File[] fileArr = dir.listFiles();
			for (int i = 0; i < fileArr.length; i++) {
				File f = fileArr[i];
				if (f.isFile()) {
					files.add(f);
				} else {
					files.addAll(getAllFiles(f.getPath()));
				}
			}
		}
		return files;
	}
	
	/** *//**文件重命名 
	    * @param path 文件目录 
	    * @param oldname  原来的文件名 
	    * @param newname 新文件名 
	    */ 
	    public static void renameFile(String path,String oldname,String newname){ 
	        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
	            File oldfile=new File(path+"/"+oldname); 
	            File newfile=new File(path+"/"+newname); 
	            if(!oldfile.exists()){
	                return;//重命名文件不存在
	            }
	            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
	                System.out.println(newname+"已经存在！"); 
	            else{ 
	                oldfile.renameTo(newfile); 
	            } 
	        }else{
	            System.out.println("新文件名和旧文件名相同...");
	        }
	    }
	    
		public static List<File> getAllDirectory(String dirPath) {
			File dir = new File(dirPath);
			ArrayList<File> files = new ArrayList<File>();

			if (dir.isDirectory()) {
				File[] fileArr = dir.listFiles();
				for (int i = 0; i < fileArr.length; i++) {
					File f = fileArr[i];
					if (f.isFile()) {
//						files.add(f);
					} else {
						files.add(f);
					}
				}
			}
			return files;
		}
		
	    
	    /***
		 * 复制单个文件
		 * 
		 * @param oldPath
		 *            String 原文件路径 如：c:/fqf.txt
		 * @param newPath
		 *            String 复制后路径 如：f:/fqf.txt
		 * @return boolean
		 */
		public static void copyFile(String oldPath, String newPath) {
			try {
				int bytesum = 0;
				int byteread = 0;
				File oldfile = new File(oldPath);
				if (oldfile.exists()) { // 文件存在时
					InputStream inStream = new FileInputStream(oldPath); // 读入原文件
					FileOutputStream fs = new FileOutputStream(newPath);
					byte[] buffer = new byte[1444];
					int length;
					while ((byteread = inStream.read(buffer)) != -1) {
						bytesum += byteread; // 字节数 文件大小
						fs.write(buffer, 0, byteread);
					}
					inStream.close();
				}
			} catch (Exception e) {
				System.out.println("复制单个文件操作出错");
				e.printStackTrace();

			}

		}
	public static void main(String[] args) {}
	
	
}
