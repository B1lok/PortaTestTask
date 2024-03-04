package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    private final String fileName;
    private final List<Long> largestIncreasingSequence = new ArrayList<>();
    private final List<Long> largestDecreasingSequence = new ArrayList<>();
    private final List<Long> increasingSequence = new ArrayList<>();
    private final List<Long> decreasingSequence = new ArrayList<>();

    public FileProcessor(String fileName) {
        this.fileName = fileName;
    }

    public ProcessedFile process() {
        try {
            List<Long> numbers = readFromFile();
            return processFileData(numbers);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Incorrect file path");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid file format: can not parse all numbers");
        } catch (NoSuchFileException e) {
            throw new RuntimeException("Such file doesn't exist");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    private ProcessedFile processFileData(List<Long> numbers) {
        long maxNum = numbers.get(0);
        long minNum = numbers.get(0);
        long sumOfElems = 0;
        for (Long number : numbers) {
            maxNum = Math.max(maxNum, number);
            minNum = Math.min(minNum, number);
            sumOfElems += number;
            updateSequence(increasingSequence, number, largestIncreasingSequence, true);
            updateSequence(decreasingSequence, number, largestDecreasingSequence, false);
        }
        return ProcessedFile.builder()
                .maxNumber(maxNum)
                .minNumber(minNum)
                .arithmeticMeanValue((double) sumOfElems / numbers.size())
                .medianValue(findMedianValue(numbers.size(), numbers))
                .largestIncreasingSequence(largestIncreasingSequence)
                .largestDecreasingSequence(largestDecreasingSequence)
                .build();
    }

    public void updateSequence(List<Long> sequence, Long number, List<Long> largestSequence, boolean increasing) {
        if (sequence.isEmpty() || (increasing && number >= sequence.get(sequence.size() - 1))
                || (!increasing && number <= sequence.get(sequence.size() - 1))) {
            sequence.add(number);
        } else {
            if (sequence.size() >= largestSequence.size()) {
                largestSequence.clear();
                largestSequence.addAll(sequence);
                sequence.clear();
            } else {
                sequence.clear();
                sequence.add(number);
            }
        }
    }

    public List<Long> readFromFile() throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(Long::parseLong)
                .toList();
    }

    public double findMedianValue(int numOfElems, List<Long> numbers) {
        if (numOfElems % 2 == 0) {
            int lowerMedian = numOfElems / 2;
            return 0.5 * (numbers.get(lowerMedian - 1) + numbers.get(lowerMedian));
        } else {
            return numbers.get(numOfElems / 2);
        }
    }
}