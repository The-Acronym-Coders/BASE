package com.acronym.base.util;

import com.acronym.base.reference.Reference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Created by EwyBoy **/
public class FileHelper {

    public void scanFile(String key, String texture, File base) throws FileNotFoundException {
        Scanner scan = new Scanner(base);
        List<String> content = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.contains("%modid%")) {
                line = line.replace("%modid%", Reference.MODID);
            }
            if (line.contains("%key%")) {
                line = line.replace("%key%", key);
            }
            if (line.contains("%texture%")) {
                line = line.replace("%texture%", texture);
            }
            content.add(line);
        }
        scan.close();
    }

    public void writeBaseFile(File base) throws IOException {
        List<String> content = new ArrayList<>();
        FileWriter write = new FileWriter(base);

        for (String s : content) {
            write.write(s + "\n");
        }
        write.close();
    }
}
