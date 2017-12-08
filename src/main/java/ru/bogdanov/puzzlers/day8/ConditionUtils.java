package ru.bogdanov.puzzlers.day8;

class ConditionUtils {

  private ConditionUtils() {
    throw new AssertionError("Shouldn't be instantiated");
  }

  static boolean compare(int conditionValue, String operator, int toCompare){
    switch (operator) {
      case ">":
        if (conditionValue > toCompare)
          return true;
        break;

      case "<":
        if (conditionValue < toCompare)
          return true;
        break;

      case ">=":
        if (conditionValue >= toCompare)
          return true;
        break;
      case "<=":
        if (conditionValue <= toCompare)
          return true;
        break;

      case "==":
        if (conditionValue == toCompare)
          return true;
        break;
      case "!=":
        if (conditionValue != toCompare)
          return true;
        break;
      default:
        throw new RuntimeException("Wrong operator");
    }
    return false;
  }

}
