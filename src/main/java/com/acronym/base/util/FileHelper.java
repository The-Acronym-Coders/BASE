package com.acronym.base.util;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by EwyBoy
 **/
public class FileHelper {

    public List<String> scanFile(String modid, String key, String texture, File base) {

        List<String> content = new ArrayList<>();
        try {
            Scanner scan = new Scanner(base);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                content.add(line.replaceAll("%modid%", modid).replaceAll("%key%", key).replaceAll("%texture%", texture));
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void writeFile(File base, List<String> content) {
        try {
            FileWriter write = new FileWriter(base);

            for (String s : content) {
                write.write(s + "\n");
            }

            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFromJar(Class baseClass, String fileName, File to) {
        URL baseUrl = baseClass.getResource("/assets/" + fileName);
        try {
            org.apache.commons.io.FileUtils.copyURLToFile(baseUrl, to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param directory The directory to zip the contents of. Content structure will
     *                  be preserved.
     * @param zipfile   The zip file to output to.
     * @author McDowell -
     * http://stackoverflow.com/questions/1399126/java-util-zip
     * -recreating-directory-structure
     */
    @SuppressWarnings("resource")
    public static void zipFolderContents(File directory, File zipfile) {
        try {
            URI base = directory.toURI();
            Deque<File> queue = new LinkedList<File>();
            queue.push(directory);
            OutputStream out = new FileOutputStream(zipfile);
            Closeable res = out;
            try {
                ZipOutputStream zout = new ZipOutputStream(out);
                res = zout;
                while (!queue.isEmpty()) {
                    directory = queue.pop();
                    for (File child : directory.listFiles()) {
                        String name = base.relativize(child.toURI()).getPath();
                        if (child.isDirectory()) {
                            queue.push(child);
                            name = name.endsWith("/") ? name : name + "/";
                            zout.putNextEntry(new ZipEntry(name));
                        } else {
                            zout.putNextEntry(new ZipEntry(name));
                            copy(child, zout);
                            zout.closeEntry();
                        }
                    }
                }
            } finally {
                res.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author tterrag1098
     *         <p>
     *         from <a href=
     *         "https://github.com/tterrag1098/ttCore/blob/master/src/main/java/tterrag/core/common/util/TTFileUtils.java">
     *         this site </a>
     */
    /**
     * @see #zipFolderContents(File, File)
     */
    private static void copy(InputStream in, OutputStream out) {
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                int readCount = in.read(buffer);
                if (readCount < 0) {
                    break;
                }
                out.write(buffer, 0, readCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see #zipFolderContents(File, File)
     */
    private static void copy(File file, OutputStream out) {
        try {
            InputStream in = new FileInputStream(file);
            try {
                copy(in, out);
            } finally {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
