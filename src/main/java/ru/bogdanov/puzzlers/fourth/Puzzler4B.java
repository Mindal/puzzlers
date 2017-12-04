package ru.bogdanov.puzzlers.fourth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * --- Part Two ---
 *
 * For added security, yet another system policy has been put in place. Now, a valid passphrase must
 * contain no two words that are anagrams of each other - that is, a passphrase is invalid if any
 * word's letters can be rearranged to form any other word in the passphrase.
 *
 * For example:
 *
 * abcde fghij is a valid passphrase. abcde xyz ecdab is not valid - the letters from the third word
 * can be rearranged to form the first word. a ab abc abd abf abj is a valid passphrase, because all
 * letters need to be used when forming another word. iiii oiii ooii oooi oooo is valid. oiii ioii
 * iioi iiio is not valid - any of these words can be rearranged to form any other word. Under this
 * new system policy, how many passphrases are valid?
 */
public class Puzzler4B {

  public static void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Paths.get(
        "C:\\Users\\Andrei_Bogdanov\\IdeaProjects\\puzzlers\\src\\main\\java\\ru\\bogdanov\\puzzlers\\fourth\\input4.txt"));
    System.out
        .println(strings.stream().map(Puzzler4B::isValid).filter(aBoolean -> aBoolean).count());
  }

  private static boolean isValid(String s) {
    HashSet<String> set = new HashSet<>();

    for (String string : s.split(" ")) {
      String str1 = clearString(string);
      if (set.contains(str1)) {
        return false;
      }
      set.add(str1);
    }
    return true;
  }

  private static String clearString(String string) {
    char[] chars = string.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }

}
