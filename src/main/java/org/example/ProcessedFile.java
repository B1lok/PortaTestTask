package org.example;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ProcessedFile {
    private long maxNumber;
    private long minNumber;
    private double medianValue;
    private double arithmeticMeanValue;
    private List<Long> largestIncreasingSequence;
    private List<Long> largestDecreasingSequence;
}
