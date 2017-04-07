package com.teamacronymcoders.base.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionHelper {

    /**
     * Converts a string into a byte[]
     *
     * @param uncompressedString String to be compressed
     * @return byte[] of compressed String
     */
    public static byte[] compressStringToByteArray(String uncompressedString) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);


            gzipOutputStream.write(uncompressedString.getBytes("UTF-8"));
            gzipOutputStream.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    /**
     * Decompresses a String from a byte[]
     *
     * @param compressedString Byte[] to be decompressed
     * @return String of decompressed byte[]
     */
    public static String decompressStringFromByteArray(byte[] compressedString) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedString));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzipInputStream, "UTF-8"));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
