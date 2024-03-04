package org.example;

public class FileStatistic {

    public static void printFileStatistic(String fileName) {
        try {
            FileProcessor fileProcessor = new FileProcessor(fileName);
            ProcessedFile processedFile = fileProcessor.process();
            System.out.println("maxNumber: " + processedFile.getMaxNumber());
            System.out.println("minNumber: " + processedFile.getMinNumber());
            System.out.println("medianValue: " + processedFile.getMedianValue());
            System.out.println("arithmeticMeanValue: " + processedFile.getArithmeticMeanValue());
            System.out.println("largestIncreasingSequence: " + processedFile.getLargestIncreasingSequence());
            System.out.println("largestDecreasingSequence: " + processedFile.getLargestDecreasingSequence());
        } catch (RuntimeException e) {
            System.out.println("Error occurred - ".concat(e.getMessage()));
        }
    }

}
