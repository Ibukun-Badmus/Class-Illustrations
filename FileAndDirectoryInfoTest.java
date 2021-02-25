package com.cohortFive.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FileAndDirectoryInfoTest {
    String filePath;

    @BeforeEach
    void setUp() {
        StringBuilder pathBuilder = new StringBuilder("C:");
        pathBuilder.append(File.separator);
        pathBuilder.append("Semicolon");
        pathBuilder.append(File.separator);
        pathBuilder.append("Pentax.txt");
        filePath = pathBuilder.toString();
//        filePath = "C:\\Semicolon\\pentax.txt";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isValidPathWithNullPath() {
        assertThrows(FileNotFoundException.class,
                () -> FileAndDirectoryInfo.isValidPath(null)
        );
    }

    @Test
    void isValidPath() {
        try {
            boolean result = FileAndDirectoryInfo.isValidPath(filePath);
            assertTrue(result);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    void isValidPathWithInvalidPath() {
        filePath = "C\\Files\\pentax.txt";
        assertThrows(FileNotFoundException.class, () -> FileAndDirectoryInfo.isValidPath(filePath));
    }


    @Test
    void playWithFileSystem() {
        FileSystem defaultFileSystem = FileSystems.getDefault();
        assertNotNull(defaultFileSystem);
        Iterable<Path> rootDirectories = defaultFileSystem.getRootDirectories();
        Iterator<Path> directories = rootDirectories.iterator();
        while (directories.hasNext()) {
            System.out.println(directories.next());
        }
        directories = rootDirectories.iterator();
        Path cDrive = directories.next();
        assertNotNull(cDrive);
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(cDrive);
            for (Path path : directoryStream) {
                if (Files.isDirectory(path) && !Files.isHidden(path))
                    System.out.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    void getFileName() {
        String name = "Pentax.txt";
        try {
            assertEquals(name, FileAndDirectoryInfo.getFileName(filePath));
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println(fileNotFoundException.getMessage());
            fileNotFoundException.printStackTrace();
        }

    }

    @Test
    void createFile() {
        try {
            String fileName = "C:/Semicolon/Clients.txt";
            Formatter output = new Formatter(fileName);
            Path filePath = Paths.get(fileName);
            assertNotNull(filePath);
            assertTrue(Files.exists(filePath));
            output.format("%s", "These are wimps!!!");
            output.format("%d %s %s %.2f%n", 1, "Jo", "Johnson", 50.58);
            output.format("%d %s %s %.2f%n", 5, "Lo", "Wale", 60.58);
            output.format("%d %s %s %.2f%n", 4, "ko", "Tunde", 20.58);
            output.close();


        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("File not found");
        }
    }

    @Test
    void readFile(){
        String fileName = "C:/Semicolon/Clients.txt";
            Path filePath = Paths.get(fileName);
            assertNotNull(filePath);
            assertTrue(Files.exists(filePath));
        try(Scanner input = new Scanner(filePath)){
            System.out.printf("%-10s%-12s%10s%n", "Account", "First name", "Last Name", "Balance");
            while (input.hasNext()){
                System.out.printf("")

            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
