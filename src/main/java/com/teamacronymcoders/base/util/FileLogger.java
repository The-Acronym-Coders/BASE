//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.teamacronymcoders.base.util;

import java.io.*;

public class FileLogger {
    private final Writer writer;
    private final PrintWriter printWriter;

    public FileLogger(File output) {
        try {
            this.writer = new OutputStreamWriter(new FileOutputStream(output), "utf-8");
            this.printWriter = new PrintWriter(this.writer);
        } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException("What the heck?");
        } catch (FileNotFoundException var4) {
            throw new RuntimeException("Could not open log file " + output);
        }
    }

    public void logCommand(String message) {
        try {
            this.writer.write(message + "\n");
            this.writer.flush();
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public void logInfo(String message) {
        try {
            this.writer.write("INFO: " + message + "\n");
            this.writer.flush();
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public void logWarning(String message) {
        try {
            this.writer.write("WARNING: " + message + "\n");
            this.writer.flush();
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public void logError(String message) {
        this.logError(message, (Throwable)null);
    }

    public void logError(String message, Throwable exception) {
        try {
            this.writer.write("ERROR: " + message + "\n");
            if(exception != null) {
                exception.printStackTrace(this.printWriter);
            }

            this.writer.flush();
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }
}
