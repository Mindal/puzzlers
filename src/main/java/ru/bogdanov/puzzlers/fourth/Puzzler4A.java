package ru.bogdanov.puzzlers.fourth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

/**
 * A new system policy has been put in place that requires all accounts to use a passphrase instead
 * of simply a password. A passphrase consists of a series of words (lowercase letters) separated by
 * spaces.
 *
 * To ensure security, a valid passphrase must contain no duplicate words.
 *
 * For example:
 *
 * aa bb cc dd ee is valid. aa bb cc dd aa is not valid - the word aa appears more than once. aa bb
 * cc dd aaa is valid - aa and aaa count as different words. The system's full passphrase list is
 * available as your puzzle input. How many passphrases are valid?
 */
public class Puzzler4A {

  public static void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Paths.get(
        "C:\\Users\\Andrei_Bogdanov\\IdeaProjects\\puzzlers\\src\\main\\java\\ru\\bogdanov\\puzzlers\\fourth\\input4.txt"));
    System.out
        .println(strings.stream().map(Puzzler4A::isValid).filter(aBoolean -> aBoolean).count());
  }

  private static boolean isValid(String s) {
    HashSet<String> set = new HashSet<>();
    for (String string : s.split(" ")) {
      if(set.contains(string)) return false;
      set.add(string);
    }
    return true;
  }

}

