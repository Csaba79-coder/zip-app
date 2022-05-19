package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Getter
@Setter
@NoArgsConstructor
public class ZipImpl implements IZip {

    private static final int BUFFER_SIZE = 4096;
    private boolean isSuccess = true;

    @Override
    public boolean unzip(String txtFilePath, LinkedHashSet<String> lines, String zipFilePath, String destDirectory) {

        try {
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDirectory, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        try {
                            throw new IOException("Failed to create directory " + newFile);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            return !isSuccess;
                        }
                    }
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        try {
                            throw new IOException("Failed to create directory " + parent);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            return !isSuccess;
                        }
                    }

                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(newFile);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return !isSuccess;
        }
        return isSuccess;
    }

    private File newFile(String destDirectory, ZipEntry zipEntry) {

        File destFile = new File(destDirectory, zipEntry.getName());
        String destFilePath = null;
        try {
            destFilePath = destFile.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (false) {
                throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
            }

            return destFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile;
    }
}
