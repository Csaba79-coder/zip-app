package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

public class FileHandler {

    public LinkedHashSet<String> readTxt(String path) {

        LinkedHashSet<String> lineSet = new LinkedHashSet<>();

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (String line; (line = bufferedReader.readLine()) != null  && !line.equals("");) {
                lineSet.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return lineSet;
    }
}
