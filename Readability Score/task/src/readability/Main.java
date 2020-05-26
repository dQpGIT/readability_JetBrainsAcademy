package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static double averageAge = 0;

    public static void main(String[] args) {

        String filename = args[0];
        File file = new File(filename);
        int words = 0;
        int sentences = 0;
        int characters = 0;
        double score;
        int syllables = 0;
        int polysyllables = 0;

        int age = 0;

        double averageChars;
        double averageSentences;


        try {
            Scanner scanner = new Scanner(file);
            Scanner inputScanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] lineArgs = line.split("\\s+");
                words += lineArgs.length;


                for (String lineArg : lineArgs) {
                    characters += lineArg.length();
                    int temp = countSyllables(lineArg);
                    syllables += temp;

                    if (temp > 2) {
                        polysyllables++;
                    }
                    if (lineArg.matches(".*[.!?]")) {
                        sentences++;
                    }
                }
                if (!lineArgs[lineArgs.length - 1].matches(".*[.!?]")) {
                    sentences++;
                }
            }

            System.out.println("Words: " + words);
            System.out.println("Sentences: " + sentences);
            System.out.println("Characters: " + characters);
            System.out.println("Syllables: " + syllables);
            System.out.println("Polysyllables: " + polysyllables);
            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");

            String method = inputScanner.next();


            switch (method) {

                case "ARI":
                    printARI(characters, words, sentences);
                    break;

                case "FK":
                    printFK(words, sentences, syllables);
                    break;

                case "SMOG":
                    printSMOG(sentences, polysyllables);
                    break;

                case "CL":
                    averageChars = characters / (1.0 * words) * 100;
                    averageSentences = sentences / (1.0 * words) * 100;
                    printCL(averageChars, averageSentences);
                    break;


                case "all":
                    printARI(characters, words, sentences);

                    printFK(words, sentences, syllables);
                    printSMOG(sentences, polysyllables);
                    averageChars = characters / (1.0 * words) * 100;
                    averageSentences = sentences / (1.0 * words) * 100;
                    printCL(averageChars, averageSentences);

                    averageAge /= 4;
                    break;

                default:
                    break;
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("this text should be understood in average by %.2f year olds.", averageAge));
    }


//        System.out.println(String.format("The score is: %.2f", score));
//        System.out.println(!(hi == -1) ? "This text should be understood by " + lo + "-" + hi + " year olds."
//                : "This text should be understood by " + lo + "+ year olds.");


    private static void printARI(int characters, int words, int sentences) {
        double score;
        int age;
        score = calculateIndexScore(characters, words, sentences);
        age = ageRangeTable((int) Math.ceil(score))[1];
        averageAge += age;
        System.out.println(String.format("Automated Readability Index: %.2f (about %d year olds).", score, age));
    }

    private static void printFK(int words, int sentences, int syllables) {
        double score;
        int age;
        score = fleschKincaid(words, sentences, syllables);
        age = ageRangeTable((int) Math.ceil(score))[1];
        averageAge += age;
        System.out.println(String.format("Flesch–Kincaid readability tests: %.2f (about %d year olds).", score, age));
    }

    private static void printSMOG(int sentences, int polysyllables) {
        double score;
        int age;
        score = smogIndex(sentences, polysyllables);
        age = ageRangeTable((int) Math.ceil(score))[1];
        averageAge += age;
        System.out.println(String.format("Simple Measure of Gobbledygook: %.2f (about %d year olds).", score, age));
    }

    private static void printCL(double averageChars, double averageSentences) {
        double score;
        int age;
        score = clIndex(averageChars, averageSentences);
        age = ageRangeTable((int) Math.ceil(score))[0];
        averageAge += age;
        System.out.println(String.format("Coleman–Liau index: %.2f (about %d year olds).", score, age));

    }


    private static double calculateIndexScore(int numCharacters, int numWords, int numSentences) {
        return 4.71 * (numCharacters / (numWords * 1.0)) + 0.5 * (numWords / (numSentences * 1.0)) - 21.43;

    }

    private static double smogIndex(int numSentences, int numPolySyllables) {
        return 1.043 * Math.sqrt(numPolySyllables * 30 / (numSentences * 1.0))+ 3.1291;


    }

    private static double clIndex(double averageChars, double averageSentences) {
        return 0.0588 * averageChars - 0.296 * averageSentences - 15.8;


    }

    private static double fleschKincaid(int numWords, int numSentences, int numSyllables) {
        return 0.39 * (numWords / (numSentences * 1.0)) + 11.8 * (numSyllables / (numWords * 1.0)) - 15.59;


    }

    private static int countSyllables(String word) {
        int count = 0;
        boolean wasVowel = false;
        String[] chars = word.split("");
        for (String ch : chars) {
            if (ch.matches("[aeiouy]")) {
                if (!wasVowel) {
                    count++;
                    wasVowel = true;
                }

            } else {
                wasVowel = false;
            }
        }

        if (chars[chars.length - 1].equals("e")) {
            count--;
        }


        return count == 0 ? 1 : count;
    }


    private static int[] ageRangeTable(int score) {
        int[] res = new int[2];
        switch (score) {
            case 1:
                res[0] = 5;
                res[1] = 6;
                break;
            case 2:
                res[0] = 6;
                res[1] = 7;
                break;
            case 3:
                res[0] = 7;
                res[1] = 9;
                break;
            case 4:
                res[0] = 9;
                res[1] = 10;
                break;
            case 5:
                res[0] = 10;
                res[1] = 11;
                break;
            case 6:
                res[0] = 11;
                res[1] = 12;
                break;
            case 7:
                res[0] = 12;
                res[1] = 13;
                break;
            case 8:
                res[0] = 13;
                res[1] = 14;
                break;
            case 9:

                res[0] = 14;
                res[1] = 15;
                break;
            case 10:
                res[0] = 15;
                res[1] = 16;
                break;
            case 11:
                res[0] = 16;
                res[1] = 17;
                break;

            case 12:
                res[0] = 17;
                res[1] = 18;
                break;
            case 13:
                res[0] = 18;
                res[1] = 24;
                break;
            case 14:
                res[0] = 24;
                res[1] = -1;
                break;
            default:
                break;


        }
        return res;
    }
}
