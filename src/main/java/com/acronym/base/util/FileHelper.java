package com.acronym.base.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by EwyBoy
 **/
public class FileHelper {

    public List<String> scanFile(String modid, String key, String texture, File base) throws FileNotFoundException {
        Scanner scan = new Scanner(base);
        List<String> content = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            content.add(line.replaceAll("%modid%", modid).replaceAll("%key%", key).replaceAll("%texture%", texture));
        }

        scan.close();

        return content;
    }

    public void writeFile(File base, List<String> content) throws IOException {
        FileWriter write = new FileWriter(base);

        for (String s : content) {
            write.write(s + "\n");
        }

        write.close();
    }
}
