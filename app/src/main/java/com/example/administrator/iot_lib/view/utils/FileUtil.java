package com.example.administrator.iot_lib.view.utils;

import android.text.TextUtils;

import com.example.administrator.iot_lib.view.trace.Trace;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class FileUtil {

    public static String getMd5ByFile(File file) {
        FileInputStream fis = null;
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024 * 256];
            int length = -1;
            // Trace.d(logTag, "getFileMD5, GenMd5 start");
            long s = System.currentTimeMillis();
            if (fis == null || md == null) {
                return null;
            }
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] bytes = md.digest();
            if (bytes == null) {
                return null;
            }
            for (int i = 0; i < bytes.length; i++) {
                String md5s = Integer.toHexString(bytes[i] & 0xff);
                if (md5s == null || buf == null) {
                    return null;
                }
                if (md5s.length() == 1) {
                    buf.append("0");
                }
                buf.append(md5s);
            }
            // Trace.d(logTag, "getFileMD5, GenMd5 success! spend the time: "+ (System.currentTimeMillis() - s) + "ms");
            String value = buf.toString();
            int fix_num = 32 - value.length();
            for (int i = 0; i < fix_num; i++) {
                value = "0" + value;
            }
            return value;
        } catch (Exception ex) {
            Trace.d("FileUtil", "getFileMD5, Exception " + ex.toString());
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getMd5ByFile(String filePath) {
        File fd = new File(filePath);
        if (fd.exists()) {
            return getMd5ByFile(fd);
        }
        return "";
    }
    /**
     * @param filePath
     * @param md5sum   not null
     * @return 验证是否通过
     */
    public static boolean validateFile(String filePath,String md5sum){
        if(TextUtils.isEmpty(filePath) || TextUtils.isEmpty(md5sum))
            return false;
        return md5sum.equals(getMd5ByFile(filePath));
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(String dirPath){
        if (isSpace(dirPath))
            return false;
        File file = new File(dirPath);
        return file.exists()? file.isDirectory() : file.mkdirs();
    }

    /**
     * 递归删除文件夹 要利用File类的delete()方法删除目录时， 必须保证该目录下没有文件或者子目录，否则删除失败，
     * 因此在实际应用中，我们要删除目录， 必须利用递归删除该目录下的所有子目录和文件， 然后再删除该目录。
     *
     * @param dirPath
     */
    public static void deleteFileDir(String dirPath){
        File file = new File(dirPath);
        if(!file.exists())
            return;
        File[] childFiles = file.listFiles();
        for (File f : childFiles) {
            if(f.isDirectory())
                deleteFileDir(f.getAbsolutePath());
            else
                f.delete();
        }
    }

    public static boolean isSpace(String str){
        if(str == null)
            return true;
        for (int i = 0; i < str.length() ; i++) {
            if (!Character.isWhitespace(str.charAt(i)))
                return false;
        }
        return true;
    }

    public static String convertFileSize(long size) {
        float result = size;
        String suffix = "B";
        if (result > 1024) {
            suffix = "KB";
            result = result / 1024;
        }
        if (result > 1024) {
            suffix = "MB";
            result = result / 1024;
        }
        if (result > 1024) {
            suffix = "GB";
            result = result / 1024;
        }
        if (result > 1024) {
            suffix = "TB";
            result = result / 1024;
        }

        final String roundFormat;
        if (result < 10) {
            roundFormat = "%.2f";
        } else if (result < 100) {
            roundFormat = "%.1f";
        } else {
            roundFormat = "%.0f";
        }
        return String.format(roundFormat + suffix, result);
    }

    public static void closeIO(Closeable... closeables){
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
