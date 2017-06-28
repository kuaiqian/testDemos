package junit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import steel.util.FileUtil;

public class TestMakeFile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String filePathStr ="/opt/tmp/mas/rcb/sh/20160809/29979626";
		String batchFileName="BRF_20160809_29979626.txt";
		String zipFileName="/nfs/tmp/mas/rcb/sh/BRF_20160809_1000323736.zip";
		File dirPath = new File(filePathStr);
		File batchFile = new File(dirPath, batchFileName);

		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(batchFile);
		fos.write("aaaaaaa".getBytes());
		fos.flush();
		
		FileUtil.zipFile(zipFileName, dirPath.getPath());
		
	}

}
