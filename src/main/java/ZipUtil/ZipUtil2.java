package ZipUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipUtil2 {
    /**
     * 压缩多个指定文件到一个文件
     * 
     * @author lzx 2015年5月15日下午12:11:35
     */
    public static void zipFilsToOne(String[] fileList, String targteZipFile) throws IOException {

	File zipFile = new File(targteZipFile);

	FileInputStream input = null;
	FileOutputStream ops=new FileOutputStream(zipFile);
	ZipOutputStream zipOut = new ZipOutputStream(ops);
	// zip的名称为
	zipOut.setComment(zipFile.getName());
	// byte[] buf = new byte[1024];
	for (String oneFile : fileList) {
	    File file = new File(oneFile);
	    input = new FileInputStream(new File(oneFile));
	    long size = input.available();
	    FileChannel inc = input.getChannel();
	    MappedByteBuffer bf = inc.map(FileChannel.MapMode.READ_ONLY, 0, size);
	    zipOut.putNextEntry(new ZipEntry(file.getName()));
	    int n = bf.remaining();
	    for (int i = 0; i < n; i++)
		zipOut.write(bf.get());
	    
	    inc.close();
	    input.close();
	}
	zipOut.close();
	ops.close();
    }

    /**
     * 把指定目录压缩成zip文件，例如：/data 压缩成data.zip
     * 
     * @author lzx 2015年6月10日上午11:03:39
     */
    public static String zipDirToOneFile(String srcDir, String targetDir,String nameSuffix) throws IOException {
	File f = new File(srcDir);
	File target=new File(targetDir);
	if(!target.exists()) target.mkdirs();
	File targetFile=new File(targetDir + File.separator + f.getName()+nameSuffix + ".zip");
	
	OutputStream os = new FileOutputStream(targetFile);
	BufferedOutputStream bs = new BufferedOutputStream(os);
	ZipOutputStream zo = new ZipOutputStream(bs);
	zip(srcDir, f.getParentFile(), zo, true, true);
	zo.closeEntry();
	zo.close();
	bs.close();
	os.close();
	return targetFile.getAbsolutePath();
    }
    public static String zipDirToOneFile(String srcDir, String targetDir) throws IOException {
	return zipDirToOneFile(srcDir,targetDir,"");
    }
    /**
     * @param path
     *            要压缩的路径, 可以是目录, 也可以是文件.
     * @param basePath
     *            如果path是目录,它一般为new File(path), 作用是:使输出的zip文件以此目录为根目录,
     *            如果为null它只压缩文件, 不解压目录.
     * @param zo
     *            压缩输出流
     * @param isRecursive
     *            是否递归
     * @param isOutBlankDir
     *            是否输出空目录, 要使输出空目录为true,同时baseFile不为null.
     * @throws IOException
     */
    public static void zip(String path, File basePath, ZipOutputStream zo, boolean isRecursive, boolean isOutBlankDir) throws IOException {

	File inFile = new File(path);

	File[] files = new File[0];
	if (inFile.isDirectory()) { // 是目录
	    files = inFile.listFiles();
	} else if (inFile.isFile()) { // 是文件
	    files = new File[1];
	    files[0] = inFile;
	}
	// System.out.println("baseFile: "+baseFile.getPath());
	for (int i = 0; i < files.length; i++) {
	    String pathName = "";
	    if (basePath != null) {
		if (basePath.isDirectory()) {
		    System.out.println(basePath.getPath());
		    pathName = files[i].getPath().substring(basePath.getPath().length() + 1);
		} else {// 文件
		    pathName = files[i].getPath().substring(basePath.getParent().length() + 1);
		}
	    } else {
		pathName = files[i].getName();
	    }
	    System.out.println(pathName);
	    if (files[i].isDirectory()) {
		if (isOutBlankDir && basePath != null) {
		    zo.putNextEntry(new ZipEntry(pathName + "/")); // 可以使空目录也放进去
		}
		if (isRecursive) { // 递归
		    zip(files[i].getPath(), basePath, zo, isRecursive, isOutBlankDir);
		}
	    } else {
		FileInputStream input = new FileInputStream(files[i]);
		long size = input.available();
		FileChannel inc = input.getChannel();
		MappedByteBuffer bf = inc.map(FileChannel.MapMode.READ_ONLY, 0, size);
		zo.putNextEntry(new ZipEntry(pathName));
		int n = bf.remaining();
		for (int j = 0; j < n; j++)
		    zo.write(bf.get());
		inc.close();
		input.close();
	    }
	}
    }

    public static void main(String[] args) throws IOException {
//	String[] files = { "本框架约定.txt", "pom.xml" };
//	zipFilsToOne(files, "r.zip");
//	String zipFilePath="E:/新建文件夹/新建文件夹.zip";
//	unzip(zipFilePath,null);
    }

    public static void mapChannel() throws IOException {
	long t1 = System.currentTimeMillis();
	FileInputStream in = new FileInputStream("d:/1.txt");
	long size = in.available();
	RandomAccessFile out = new RandomAccessFile("d:/2.txt", "rw");
	FileChannel inc = in.getChannel();
	MappedByteBuffer bf = inc.map(FileChannel.MapMode.READ_ONLY, 0, size);
	FileChannel outc = out.getChannel();
	MappedByteBuffer outbf = outc.map(FileChannel.MapMode.READ_WRITE, 0, size);
	outbf.put(bf);
	inc.close();
	outc.close();
	in.close();
	out.close();
	long t2 = System.currentTimeMillis();
	System.out.println(t2 - t1);
    }

}
