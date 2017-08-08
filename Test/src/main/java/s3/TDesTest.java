package s3;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import s3.util.TDES;

public class TDesTest {
    static final String masterKey = "A533FC93D2D2B1271E0627C62474902E";

    public static void main(String[] args) throws Exception {
        String[] filePaths = new String[] { "d:\\chen.cheng\\桌面\\11" };
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if(file.isDirectory()) {
                // 文件目录时，读取所有文件
                File[] files = file.listFiles();
                for (File file2 : files) {
                    readFileRows(file2);
                }
            }else {
                // 单个文件时
                readFileRows(new File(filePath));
            }
        }
    }

    private static void readFileRows(File file) throws IOException, Exception {
        List<String> rows = FileUtils.readLines(file);
        for (String row : rows) {
            if(StringUtils.isBlank(row)) {
                continue;
            }
            row = row.replaceAll("\"", "").replaceAll("\\s*", "");
            String[] colums = row.split(",");
            if(colums.length != 5) {
                throw new Exception("格式错误");
            }
            colums[4] = TDES.des3Decrypt(colums[4], masterKey);
            System.out.println(colums[0] + "," + colums[1] + "," + colums[4] + ",");
        }
    }
}
