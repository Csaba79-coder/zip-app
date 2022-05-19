package model;

import java.util.LinkedHashSet;

public interface IZip {

    boolean unzip(String txtFilePath, LinkedHashSet<String> lines, String zipFilePath, String destDirectory);
}
