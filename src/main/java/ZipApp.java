import model.IZip;
import model.ZipImpl;
import util.FileHandler;

import java.util.LinkedHashSet;

public class ZipApp {

    public static void main(String[] args) {

        boolean exit = false;

        if (args.length == 3) {

            IZip zip = new ZipImpl();
            FileHandler fileHandler = new FileHandler();
            String txtDirectory = args[0];
            String zipDirectory = args[1];
            String targetDirectory = args[2];

            LinkedHashSet<String> lines = fileHandler.readTxt(txtDirectory);

            System.out.println(lines.size());

            zip.unzip(txtDirectory, lines, zipDirectory, targetDirectory);

        } else {
            System.out.println("Args are missing!");
            exit = true;
        }
    }
}
