package ru.bogdanov.puzzlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

  private Utils() {
    throw new AssertionError("Shouldn't be instantiated");
  }

  public static List<String> getAllLines(String path) throws IOException {
    ClassLoader classLoader = Utils.class.getClassLoader();
    File file = new File(classLoader.getResource(path).getFile());
    return Files.readAllLines(file.toPath());
  }

  public static String getLine(String path) throws IOException{
    List<String> allLines = getAllLines(path);
    if(allLines.size() > 1){
      System.out.println(allLines.size());
      for (String allLine : allLines) {
        System.out.println(allLine);
      }
      throw new IllegalArgumentException("Path contains more than 1 string");
    }
    return allLines.get(0);
  }

  public static List<Integer> getAllIntegerLines(String path) throws IOException{
    return getAllLines(path).stream().map(Integer::parseInt).collect(Collectors.toList());
  }

}
