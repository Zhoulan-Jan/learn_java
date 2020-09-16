package parser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private static final String INPUT_PATH = "F:\\docs\\api";
    private static final String OUTPUT_PATH = "F:\\data.txt";

    public static void main(String[] args) throws IOException {
        FileWriter resFileWriter = new FileWriter(new File(OUTPUT_PATH));
        //1.得到目录中的所有文件
        ArrayList<File> fileList = new ArrayList<File>();
        enumFile(INPUT_PATH, fileList);
        //System.out.println(fileList);
        //2.遍历html文件列表
        for (File file : fileList) {
            //3.读取文件并转为结构化数据
            String line = convertLine(file);
            //System.out.println(line);
            //4.将结果写到行文本形式的输出文件中
            resFileWriter.write(line);
        }

        // System.out.println("done!");
        resFileWriter.close();
    }

    //枚举出INPUT_PATH的所有文件（递归）
    public static void enumFile(String path, ArrayList<File> fileList) {
        File root = new File(path);
        File[] files = root.listFiles();

        for (File f : files) {
            if (f.isDirectory()) { //是目录，递归调用
                enumFile(f.getPath(), fileList);
            } else if (f.getAbsolutePath().endsWith(".html")) { //不是目录，若文件后缀是.html，就加入到fileList中
                fileList.add(f);
            }
        }
    }

    public static String convertLine(File file) throws IOException {
        String title = convertTitle(file);
        String url = convertUrl(file);
        String content = convertContent(file);
        return title + "\3" + url + "\3" + content + "\n";
    }

    public static String convertTitle(File file) {
        String name = file.getName();
        return name.substring(0,name.length()-".html".length());
    }

    public static String convertUrl(File file) {
        String path1 = "http://docs.oracle.com/javase/8/docs/api";
        String path2 = file.getAbsolutePath().substring(INPUT_PATH.length());
        return path1 + path2;
    }

    //去掉文档的 html 标签 即文档正文
    public static String convertContent(File file) throws IOException {
        boolean isContent = true;
        FileReader fileReader = new FileReader(file);
        StringBuilder output = new StringBuilder();

        while (true) {
            int ret = fileReader.read();
            if (ret == -1) {
                break;
            }

            char c = (char) ret;
            if (isContent) {
                if (c == '<') {
                    isContent = false;
                    continue;
                }
                if (c == '\n' || c == '\r'){
                    output.append(' ');
                    continue;
                }
                output.append(c);
            } else {
                if (c == '>') {
                    isContent = true;
                }
            }
        }
        return output.toString();
    }
}
