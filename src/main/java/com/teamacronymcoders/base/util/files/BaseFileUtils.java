package com.teamacronymcoders.base.util.files;

import com.teamacronymcoders.base.util.Platform;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Deque;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * Borrowed From EnderCore
 * (https://github.com/SleepyTrousers/EnderCore/blob/1.10/src/main/java/com/enderio/core/common/util/EnderFileUtils.java)
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class BaseFileUtils {
    /**
     * @param jarClass - A class from the jar in question
     * @param filename - Name of the file to copy, automatically prepended with
     *                 "/assets/"
     * @param to       - File to copy to
     */
    public static void copyFromJar(Class<?> jarClass, String filename, File to) {
        URL url = jarClass.getResource("/assets/" + filename);

        try {
            FileUtils.copyURLToFile(url, to);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param directory The directory to zip the contents of. Content structure will be
     *                  preserved.
     * @param zipfile   The zip file to output to.
     * @throws IOException if any file activity fails
     * @author McDowell - http://stackoverflow.com/questions/1399126/java-util-zip
     * -recreating-directory-structure
     */
    @SuppressWarnings("resource")
    public static void zipFolderContents(File directory, File zipfile) throws IOException {
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
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File child : files) {
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
            }
        } finally {
            res.close();
        }
    }

    /**
     * @see #zipFolderContents(File, File)
     */
    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while (true) {
            int readCount = in.read(buffer);
            if (readCount < 0) {
                break;
            }
            out.write(buffer, 0, readCount);
        }
    }

    /**
     * @see #zipFolderContents(File, File)
     */
    private static void copy(File file, OutputStream out) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            copy(in, out);
        }
    }

    public static void safeDelete(File file) {
        try {
            file.delete();
        } catch (Exception e) {
            Platform.attemptLogErrorToCurrentMod("Deleting file " + file.getAbsolutePath() + " failed.");
        }
    }

    public static void safeDeleteDirectory(File file) {
        try {
            FileUtils.deleteDirectory(file);
        } catch (Exception e) {
            Platform.attemptLogErrorToCurrentMod("Deleting directory " + file.getAbsolutePath() + " failed.");
        }
    }

    public static void createFolder(File file) {
        if (!file.exists() && !file.mkdirs()) {
            Platform.attemptLogErrorToCurrentMod("Couldn't create folder called: " + file.getName());
        }
    }

    public static String readFileToString(File file) {
        String string = null;
        try {
            string = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch (IOException e) {
            Platform.attemptLogExceptionToCurrentMod(e);
        }
        return string;
    }

    public static void writeStringToFile(String string, File file) {
        boolean exists = file.exists();
        if (!exists) {
            try {
                file.getParentFile().mkdirs();
                exists = file.createNewFile();
            } catch (IOException e) {
                Platform.attemptLogExceptionToCurrentMod(e);
            }
        }
        if (exists) {
            try {
                FileUtils.writeStringToFile(file, string, Charset.defaultCharset());
            } catch (IOException e) {
                Platform.attemptLogExceptionToCurrentMod(e);
            }
        } else {
            Platform.attemptLogErrorToCurrentMod("Couldn't create File: " + file.getName());
        }
    }

    public static void createFile(File file) {
        file.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            Platform.attemptLogErrorToCurrentMod("Couldn't create File " + file.getName());
        }
    }
}
