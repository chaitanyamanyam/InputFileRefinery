package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ramakrishnacmanyam on 5/21/17.
 */
public class FileReader {

    public List<String> read(String filePath){

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File is closed " + e.getMessage());
        }
        return null;
    }
}
