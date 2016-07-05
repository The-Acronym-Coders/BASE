package com.acronym.base.util;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionHelper {

    /**
     * Converts a string into a byte[]
     * @param uncompressedString String to be compressed
     * @return byte[] of compressed String
     */
    public static byte[] compressStringToByteArray(String uncompressedString) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream;
        try {
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(uncompressedString.getBytes("UTF-8"));
            gzipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Decompresses a String from a byte[]
     * @param compressedString Byte[] to be decompressed
     * @return String of decompressed byte[]
     */
    public static String decompressStringFromByteArray(byte[] compressedString) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedString));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipInputStream, "UTF-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
