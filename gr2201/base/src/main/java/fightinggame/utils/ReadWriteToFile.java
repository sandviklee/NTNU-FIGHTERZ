package fightinggame.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The class {@code ReadWriteToFile} is giving other classes the possibility to
 * read and write files.
 * It can let other read files with {@link #readFromFile(String)} where one
 * gives the filename and it gives all
 * the contents of the file as an string.
 * It can let other read files with {@link #storeToFile(String, String)} where
 * one gives the filename and data and it will overwrite
 * the contents to the new data.
 */
public class ReadWriteToFile {
    private String path;

    /**
     * Default constructor for {@code ReadWriteToFile}
     */
    public ReadWriteToFile() {
        this.path = "../base/src/main/resources/";
    }

    /**
     * This constructor will make adjustments so the server will get correct
     * relative path.
     * 
     * @param isServer or not
     */
    public ReadWriteToFile(boolean isServer) {
        if (isServer) {
            this.path = "../../base/src/main/resources/";
        } else {
            this.path = "../base/src/main/resources/";
        }
    }

    /**
     * Will read entire file and give a raw text string
     * 
     * @param filename of the file to read from
     * @return the entire text file
     * @throws IOException on not finding a file with given filename
     */
    public String readFromFile(String filename) throws IOException {
        String info = "";
        File file = new File(this.path + filename);
        if (file.exists()) {
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                info += line;
            }
            fileReader.close();
        } else {
			System.out.println("The file could not be found. +\n\n");
            throw new FileNotFoundException("The file could not be found.");
        }
        return info;
    }

    /**
     * Writes data to file and when no file exist create a new file.
     * 
     * @param filename to write file to
     * @param data     to write to file
     * @throws IOException on not finding a file with given filename
     */
    public void storeToFile(String filename, String data) throws IOException {
        File currentFile = new File(this.path + filename);

        FileWriter currentWriter = new FileWriter(currentFile);
        currentFile.createNewFile();
        currentWriter.write("[" + data + "]");
        currentWriter.close();
    }

    /**
     * Sets the path that this {@code ReadWriteToFile} shall interact with.
     * 
     * @param path to read and write to.
     */
    public void setPath(String path) {
        this.path = path;
    }

}

