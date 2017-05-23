package com.company;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

public class Controller
{

    static Logger log = Logger.getLogger(Controller.class.getName());

    public static void main(String[] args)
    {
        start("/Users/ramakrishnacmanyam/Downloads/code-test/input_files");
    }

    public static void start(String directoryPath)
    {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        List<Person> totalPersonList = new ArrayList<>();

        for (File file : listOfFiles)
        {
            if (file.isFile() && file.getName().endsWith(".txt"))
            {
                try
                {
                    totalPersonList.addAll(parseEachFile(file.getPath()));
                }
                catch (UnsupportedOperationException e)
                {
                    log.info(file.getName() + " has unsupported delimiter");
                }
            }
        }

        if (!totalPersonList.isEmpty())
        {
            // Can be removed if we can have duplicates in the total list
            Set<Person> setList = new LinkedHashSet<>(totalPersonList);
            totalPersonList.clear();
            totalPersonList.addAll(setList);

            SortingImpl sortingImpl = new SortingImpl();
            sortingImpl.sortPersonList(totalPersonList);
        }
    }

    public static List<Person> parseEachFile(String filePath)
    {
        Resolver resolver;
        FileReader fileReader = new FileReader();
        List<String> plainData = fileReader.read(filePath);
        List<Person> listOfPeople = new ArrayList<>();
        if (!plainData.isEmpty())
        {
            String firstLine = plainData.get(0);
            resolver = new Resolver();
            DataParser dataParser = resolver.resolve(firstLine);

            listOfPeople = dataParser.parse(plainData);
        }
        return listOfPeople;
    }

}
