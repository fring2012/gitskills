package com.example.administrator.iot_lib.view.trace;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Trace {
        public static final int VERBOSE = 2;
        public static final int DEBUG = 3;
        public static final int INFO = 4;
        public static final int WARN = 5;
        public static final int ERROR = 6;
        public static final int NONE = 7;
        private static int s_level = 7;
        private static boolean s_show_code_position = false;
        private static boolean s_write_file = true;
        private static int offset;
        private static String m_log_path = Environment.getExternalStorageDirectory() + "/iport_log.txt";
        private static int s_log_size = 500;
        private static String global_tag = "iport/";

        public Trace() {
        }

        public static void setShowPosition(boolean showPosition) {
            s_show_code_position = showPosition;
        }

        private static void println(int level, String tag, String message) {
            if (level >= s_level) {
                if (s_write_file) {
                    write_log(tag, message);
                }

                if (s_show_code_position) {
                    message = message + getCodePosition();
                }

                printAndroidLog(level, global_tag + tag, message);
            }

        }

        private static void printAndroidLog(int level, String tag, String message) {
            switch(level) {
                case 2:
                    Log.v(tag, message);
                    break;
                case 3:
                    Log.d(tag, message);
                    break;
                case 4:
                    Log.i(tag, message);
                    break;
                case 5:
                    Log.w(tag, message);
                    break;
                case 6:
                    Log.e(tag, message);
            }

        }

        public static void setLevel(int level) {
            s_level = level;
        }

        public static void write_file(boolean write_file) {
            s_write_file = write_file;
        }

        public static void setLog_size(int log_size) {
            s_log_size = log_size;
        }

        public static void setLog_path(String m_log_path) {
            m_log_path = m_log_path;
        }

        public static void v(String tag, String msg) {
            println(2, tag, msg);
        }

        public static void d(String tag, String msg) {
            println(3, tag, msg);
        }

        public static void d(String tag, String msg, Object... args) {
            println(3, tag, String.format(msg, args));
        }

        public static void i(String tag, String msg) {
            println(4, tag, msg);
        }

        public static void i(String tag, String msg, Object... args) {
            println(4, tag, String.format(msg, args));
        }

        public static void w(String tag, String msg, Object... args) {
            println(5, tag, String.format(msg, args));
        }

        public static void w(String tag, String msg) {
            println(5, tag, msg);
        }

        public static void e(String tag, String msg) {
            println(6, tag, msg);
        }

        public static void e(String tag, String msg, Throwable tr) {
            println(6, tag, msg + '\n' + getStackTraceString(tr));
        }

        public static void e(String tag, Throwable tr) {
            println(6, tag, getStackTraceString(tr));
        }

        public static <T> void array(String tag, T[] array) {
            offset = 1;
            if (array == null) {
                e(tag, "array is null");
            } else {
                d(tag, Arrays.toString(array));
            }

        }

        public static void list(String tag, List<?> lists) {
            offset = 1;
            if (lists == null) {
                e(tag, "lists is null");
            } else {
                int iMax = lists.size() - 1;
                if (iMax == -1) {
                    d(tag, "{}");
                } else {
                    StringBuilder b = new StringBuilder();
                    b.append('{');

                    for(int i = 0; i < lists.size(); ++i) {
                        b.append(String.valueOf(lists.get(i)));
                        if (i == iMax) {
                            b.append('}');
                        }

                        b.append(", ");
                    }

                    d(tag, b.toString());
                }
            }

        }

        public static void json(String tag, String json) {
            offset = 1;
            if (TextUtils.isEmpty(json)) {
                e(tag, "Empty/Null json content");
            } else {
                try {
                    json = json.trim();
                    String message;
                    if (json.startsWith("{")) {
                        JSONObject jsonObject = new JSONObject(json);
                        message = jsonObject.toString(2);
                        d(tag, message);
                        return;
                    }

                    if (json.startsWith("[")) {
                        JSONArray jsonArray = new JSONArray(json);
                        message = jsonArray.toString(2);
                        d(tag, message);
                        return;
                    }

                    e(tag, "Invalid Json");
                } catch (JSONException var4) {
                    e(tag, "Invalid Json");
                }

            }
        }

        public static void xml(String tag, String xml) {
            offset = 1;
            if (TextUtils.isEmpty(xml)) {
                e(tag, "Empty/Null xml content");
            } else {
                try {
                    Source xmlInput = new StreamSource(new StringReader(xml));
                    StreamResult xmlOutput = new StreamResult(new StringWriter());
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty("indent", "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    transformer.transform(xmlInput, xmlOutput);
                    xml = xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
                    d(tag, xml);
                } catch (Exception var5) {
                    e(tag, "Invalid Xml");
                }

            }
        }

        public static void file(String tag, File file) {
            offset = 1;
            if (file != null && file.exists()) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] bytes = new byte[4096];
                    fis.read(bytes);
                    String content = new String(bytes, Charset.defaultCharset());
                    String result = String.format("file name:%s,file size:%s\n%s", file.getName(), file.length(), content);
                    d(tag, result);
                } catch (Exception var6) {
                    e(tag, "Invalid Xml");
                }

            } else {
                e(tag, "Empty/Null file");
            }
        }

        private static String getStackTraceString(Throwable tr) {
            if (tr == null) {
                return "";
            } else {
                for(Throwable t = tr; t != null; t = t.getCause()) {
                    ;
                }

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                tr.printStackTrace(pw);
                pw.flush();
                return sw.toString();
            }
        }

        private static String getCodePosition() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int index = 5 + offset;
            offset = 0;
            String className = stackTrace[index].getFileName();
            String methodName = stackTrace[index].getMethodName();
            int lineNumber = stackTrace[index].getLineNumber();
            return String.format(".(%s:%s) %s()", className, lineNumber, methodName);
        }

        private static boolean check_log_file() {
            File log_file = new File(m_log_path);
            if (!log_file.exists()) {
                if (!log_file.getParentFile().exists()) {
                    boolean mkdirs = log_file.getParentFile().mkdirs();
                    if (!mkdirs) {
                        Log.e(global_tag, getStackTraceString(new IOException("Can't create the directory of trace. Please check the trace path.")));
                        return false;
                    }
                }

                try {
                    log_file.createNewFile();
                } catch (IOException var2) {
                    Log.e("Trace", getStackTraceString(var2));
                    return false;
                }
            } else if (log_file.length() > (long)(1024 * s_log_size)) {
                log_file.renameTo(new File(m_log_path + "(1)"));
            }

            return true;
        }

        private static void write_log(String tag, String msg) {
            File log_file = new File(m_log_path);
            if (check_log_file()) {
                String text = getFormatLog(tag, msg);
                FileOutputStream fos = null;

                try {
                    boolean append = log_file.length() <= (long)(1024 * s_log_size);
                    fos = new FileOutputStream(log_file, append);
                    fos.write(text.getBytes());
                    fos.write("\n".getBytes());
                } catch (Exception var14) {
                    var14.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (Exception var13) {
                        var13.printStackTrace();
                    }

                }

            }
        }

        private static String getFormatLog(String tag, String msg) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateStr = sdf.format(new Date());
            return dateStr + " " + String.format("%s ", convertThreadId((int)Thread.currentThread().getId())) + String.format("%s: ", tag) + msg;
        }

        private static String convertThreadId(int value) {
            int limit = 5;
            String src = String.valueOf(value);
            int i = limit - src.length();
            if (i < 0) {
                src = src.substring(-i, src.length());
            }

            while(i > 0) {
                src = "0" + src;
                --i;
            }

            return src;
        }
}
