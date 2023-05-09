package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class RemoveOperationBenchmark {

    private LinkedList<Integer> linkedList;
    private SortedLinkedList<Integer> sortedLinkedList;

    private List<Integer> randomIntegers;

    private Random random;

    @Param({"10", "100", "1000", "5000"})
    private int listSize;

    @Setup
    public void init() {
        random = new Random();
        linkedList = new LinkedList<>();
        sortedLinkedList = new SortedLinkedList<>();

        // we are using same set of integers for both collections to achieve more precise benchmarking
        randomIntegers = random.ints(0, listSize)
                .limit(listSize)
                .boxed()
                .toList();

        for (int i = 0; i < listSize; i++) {
            linkedList.add(randomIntegers.get(i));
            sortedLinkedList.add(randomIntegers.get(i));
        }
    }

    @Benchmark
    public void linkedListRemove() {
        Object randomValue = randomIntegers.get(random.nextInt(randomIntegers.size()));
        linkedList.remove(randomValue);
    }

    @Benchmark
    public void sortedLinkedListRemove() {
        Integer randomValue = randomIntegers.get(random.nextInt(randomIntegers.size()));
        sortedLinkedList.remove(Integer.valueOf(listSize/2));
    }
}
