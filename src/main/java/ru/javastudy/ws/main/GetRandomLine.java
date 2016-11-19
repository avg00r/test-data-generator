package ru.javastudy.ws.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 1 on 19.11.2016.
 */
public class GetRandomLine {
    /**
     * Считать рандомную линию с файла
     * @param file
     * @param encoding
     * @return
     */
    public static String getRandomLine(File file, String encoding) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return getRandomLine(fis, encoding);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "IO problem with file " + file.getAbsolutePath();
    }

    public static String getRandomLine(InputStream is, String encoding) {
        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(is, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getRandomLine(streamReader);
    }

    private static List<String> fillList(Reader reader) {
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Считать случайную линию с ридера
     *
     * @param reader
     * @return
     */
    public static String getRandomLine(Reader reader) {
        return getRandomLine(fillList(reader));
    }

    private static String getRandomLine(List<String> list) {
        return list.get(getRandomLineNumber(list));
    }

    private static int getRandomLineNumber(List<String> list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }

}
