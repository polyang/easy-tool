package com.github.polyang.tool.file;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Base64;
import java.util.Base64.Decoder;

/**
 * @Author polyang
 * @Description 文件操作工具类
 * @Date 2020/3/28 20:59
 **/
public class FileUtil {

	/**
	 * 查询目标目录下的文件夹数量
	 * @param dir		目标目录
	 * @param findSon	是否查询子目录
	 * @return	文件夹数量
	 */
	public static int findDirCount(String dir, boolean findSon) {
		int count = 0;
		File file = new File(dir);
		if(!file.exists()) {
			return count;
		}
		File[] fileArr = file.listFiles();
		if (fileArr != null) {
			for(File son : fileArr) {
				if(son.isDirectory()) {
					count ++;
					if(findSon) {
						count += findDirCount(son.getAbsolutePath(), findSon);
					}
				}
			}
		}
		return count;
	}
	
	/**
	 * 查询目标文件夹下的文件数量
	 * @param dir		目标文件夹
	 * @param findSon	是否查找目标文件夹下的子文件夹
	 * @return	文件数量
	 */
	public static int findFileCount(String dir, boolean findSon) {
		int count = 0;
		File file = new File(dir);
		if(!file.exists()) {
			return count;
		}
		File[] fileArr = file.listFiles();
		if (fileArr != null) {
			for(File son : fileArr) {
				if(son.isFile()) {
					count++;
				}
				if(son.isDirectory() && findSon) {
					count += findFileCount(son.getAbsolutePath(), findSon);
				}
			}
		}
		return count;
	}
	
	/**
	 * 转存文件
	 * @param orginFile	源文件路径
	 * @param newFile	目标文件路径
	 * @return 是否转存成功
	 */
	public static boolean transferFile(String orginFile, String newFile) {
		boolean bool = false;
		try {
			File orginf = new File(orginFile);
			if(!orginf.exists()) {
				throw new Exception("源文件不存在");
			}
			File newf = new File(newFile);
            bool = transferFile(orginf, newf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	/**
	 * 转存文件
	 * @param orgin		源文件输入流
	 * @param dir		目标文件夹
	 * @param fileName	目标文件名
	 * @return	是否转存成功
	 */
	public static boolean transferFile(InputStream orgin, String dir, String fileName) {
		boolean bool = false;
        FileOutputStream out = null;
        try {
        	File target = new File(dir, fileName);
    		if(target.exists()) {
    			target.delete();
    		}
        	target.createNewFile();
            out = new FileOutputStream(target);
            byte[] b = new byte[1024 * 3];
            int len = 0;
            while((len = orgin.read(b)) != -1) {
            	out.write(b, 0, len);
            }
            bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bool;
	}
	/**
	 * 转存文件
	 * @param orginF	源文件
	 * @param newF		目标文件
	 * @return	是否转存成功
	 */
	public static boolean transferFile(File orginF, File newF) {
		boolean bool = false;
		FileInputStream in = null;
        FileOutputStream out = null;
        FileChannel inChannel = null;
        WritableByteChannel outChannel = null;
        try {
        	if(newF.exists()) {
				newF.delete();
			}
			newF.createNewFile();
			in = new FileInputStream(orginF);
            out = new FileOutputStream(newF);
            inChannel = in.getChannel();
            outChannel = out.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(inChannel != null) {
					inChannel.close();
				}
				if(outChannel != null) {
					outChannel.close();
				}
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return bool;
	}
	
	/**
	 * 将base64字符串转换成图片保存至本地
	 * @param base64	base64字符串
	 * @param savePath	保存路径
	 * @param fileName	保存文件名
	 * @return	是否转存成功
	 */
	public static boolean saveLocalFile(String base64, String savePath, String fileName) {
		boolean bool = false;
		try {
			if(base64 == null || base64.length() == 0) {
				return bool;
			}
			String pattern = "^data:image/.*;base64,";
			//去掉前缀
			base64 = base64.replaceAll(pattern, "");
			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File targetFile = new File(savePath, fileName);
			if (targetFile.exists()) {
				targetFile.delete();
			}
			Decoder decoder = Base64.getDecoder();
			//Base64解码  
	        byte[] b = decoder.decode(base64);
	        for(int i=0;i<b.length;++i) {
	        	if(b[i]<0) {
	        		//调整异常数据
	        		b[i]+=256;
	        	}
	        }
			OutputStream out = new FileOutputStream(targetFile);
			out.write(b); 
	        out.close();
	        bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
}
