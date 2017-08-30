package com.company;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final String PATH_TO_COMMON_SET_OF_NUMBERS =
            "C:\\_Data\\JavaProject\\SpecificSortIntNumber\\src\\com\\company\\CommonSetOfNumbers.txt";
    private static final String PATH_TO_ASSORT_INT_NUMBERS_ARRAY =
            "C:\\_Data\\JavaProject\\SpecificSortIntNumber\\src\\com\\company\\AssortIntNumberArray.txt";
    private static final String PATH_TO_SORT_INT_NUMBERS_ARRAY =
            "C:\\_Data\\JavaProject\\SpecificSortIntNumber\\src\\com\\company\\SortIntNumberArray.txt";
    private static final String PATH_TO_TEMP_INT_NUMBERS_ARRAY =
            "C:\\_Data\\JavaProject\\SpecificSortIntNumber\\src\\com\\company\\TempArray.txt";
    private static final Integer INT_NUMBER = 5;


    private static List<Integer> intNumbersArray;
    private static File assortIntNumbersArrayFile = new File(PATH_TO_ASSORT_INT_NUMBERS_ARRAY);
    private static File sortIntNumbersArrayFile = new File(PATH_TO_SORT_INT_NUMBERS_ARRAY);

    public static void main(String[] args) {

        intNumbersArray = transformStringToIntArray(readArrayFromFile(
                new File(PATH_TO_COMMON_SET_OF_NUMBERS)));

//        assortIntNumberArray(intNumbersArray);
//        showIntNumberArray(intNumbersArray);
//        writeArrayIntoFile(assortIntNumbersArrayFile, transformIntArrayToString(intNumbersArray));


//        intNumbersArray = transformStringToIntArray(readArrayFromFile(
//                new File(PATH_TO_ASSORT_INT_NUMBERS_ARRAY)));
//        showIntNumberArray(intNumbersArray);


        intNumbersArray = transformStringToIntArray(readArrayFromFile(
                new File(PATH_TO_TEMP_INT_NUMBERS_ARRAY)));
        showIntNumberArray(intNumbersArray);

        int counter = sortIntNumberArray(intNumbersArray, INT_NUMBER);
        showIntNumberArray(intNumbersArray);
        System.out.println("Length = " + intNumbersArray.size() + " " + "Counter = " + counter);
        writeArrayIntoFile(sortIntNumbersArrayFile, transformIntArrayToString(intNumbersArray));

    }

    private static String readArrayFromFile(File file) {

        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void writeArrayIntoFile(File file, String sNumbersArray) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(sNumbersArray);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> transformStringToIntArray(String sFileContents) {
        List<Integer> intNumberArray = new ArrayList<>();

        String[] sArrayNumber = sFileContents.split(" ");
        for (String sNumber : sArrayNumber) {
            intNumberArray.add(Integer.parseInt(sNumber));
        }

        return intNumberArray;
    }

    private static String transformIntArrayToString(List<Integer> intNumbersArray) {
        StringBuilder sNumbersArray = new StringBuilder();
        for (Integer number : intNumbersArray) {
            sNumbersArray.append(String.valueOf(number)).append(" ");
        }
        return sNumbersArray.toString();
    }

    private static void assortIntNumberArray(List<Integer> intNumbersArray) {

        for (int i = 0; i < intNumbersArray.size(); i++) {
            swapNumbers(i, (int) (Math.random() * (intNumbersArray.size() - i) + i));
        }
    }

    private static int sortIntNumberArray(List<Integer> intNumbersArray, Integer intNumber) {

        int leftPointer = 0;
        int leftCenterPointer = intNumbersArray.size() / 2 - 1;
        int rightCenterPointer = intNumbersArray.size() / 2;
        int rightPointer = intNumbersArray.size() - 1;
        int totalNumbers = 0;
        int counterRepeat = 0;


        while (!(leftCenterPointer < leftPointer || rightCenterPointer > rightPointer)) {
            counterRepeat++;

            if (intNumbersArray.get(leftPointer) < intNumber) {
                leftPointer++;
                totalNumbers++;
            } else if (intNumbersArray.get(leftPointer).equals(intNumber)) {
                swapNumbers(leftPointer, leftCenterPointer);
                leftCenterPointer--;
                totalNumbers++;
            } else {
                swapNumbers(leftPointer, rightPointer);
                rightPointer--;
                totalNumbers++;
            }

            if (leftCenterPointer < leftPointer || rightCenterPointer > rightPointer) {
                break;
            }

            if (intNumbersArray.get(rightPointer) > intNumber) {
                rightPointer--;
                totalNumbers++;
            } else if (intNumbersArray.get(rightPointer).equals(intNumber)) {
                swapNumbers(rightPointer, rightCenterPointer);
                rightCenterPointer++;
                totalNumbers++;
            } else {
                swapNumbers(rightPointer, leftPointer);
                leftPointer++;
                totalNumbers++;
            }
        }

        while (totalNumbers < intNumbersArray.size()) {
            counterRepeat++;
            if (leftCenterPointer < leftPointer) {
                if (intNumbersArray.get(rightPointer) > intNumber) {
                    rightPointer--;
                    totalNumbers++;
                } else if (intNumbersArray.get(rightPointer).equals(intNumber)) {
                    swapNumbers(rightPointer, rightCenterPointer);
                    rightCenterPointer++;
                    totalNumbers++;
                } else {
                    swapNumbers(rightPointer, leftPointer);
                    leftPointer++;
                    swapNumbers(rightPointer, rightCenterPointer);
                    rightCenterPointer++;
                    leftCenterPointer++;
                    totalNumbers++;
                }
            }

            if (!(totalNumbers < intNumbersArray.size())) {
                break;
            }

            if (rightCenterPointer > rightPointer) {
                if (intNumbersArray.get(leftPointer) < intNumber) {
                    leftPointer++;
                    totalNumbers++;
                } else if (intNumbersArray.get(leftPointer).equals(intNumber)) {
                    swapNumbers(leftPointer, leftCenterPointer);
                    leftCenterPointer--;
                    totalNumbers++;
                } else {
                    swapNumbers(leftPointer, rightPointer);
                    rightPointer--;
                    swapNumbers(leftPointer, leftCenterPointer);
                    leftCenterPointer--;
                    rightCenterPointer--;
                    totalNumbers++;
                }
            }
        }
        return counterRepeat;
    }

    private static void swapNumbers(int i, int j) {
        Collections.swap(intNumbersArray, i, j);
    }

    private static void showIntNumberArray(List<Integer> intNumbersArray) {
        intNumbersArray.forEach(str -> System.out.print(str + " "));
        System.out.println();
    }

    private static void printDopolnitelniePointers(int leftPointer, int leftCenterPointer,
                                                   int rightCenterPointer, int rightPointer) {
        StringBuilder sbLinePointers2 = new StringBuilder();
        for (int i = 0; i < intNumbersArray.size(); i++) {
            if (i == leftPointer) {
                sbLinePointers2.append("L");
            } else if (i == leftCenterPointer) {
                sbLinePointers2.append("l");
            } else if (i == rightCenterPointer) {
                sbLinePointers2.append("r");
            } else if (i == rightPointer) {
                sbLinePointers2.append("R");
            } else {
                sbLinePointers2.append(" ");
            }
            sbLinePointers2.append(" ");
        }
        System.out.println(sbLinePointers2.toString());
    }

    private static void showDebugData(int leftPointer, int leftCenterPointer,
                                      int rightCenterPointer, int rightPointer, int totalNumbers) {
        System.out.println("left = " + leftPointer +
                " " + "leftCenter = " + leftCenterPointer +
                " " + "rightCenter = " + rightCenterPointer +
                " " + "right = " + rightPointer +
                "\t" + "total = " + totalNumbers);
    }
}
