package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Threads(value = 8)
public class AddOperationBenchmark {

    private LinkedList<Integer> linkedList;
    private SortedLinkedList<Integer> sortedLinkedList;
    private Random random;

    @Param({"10", "100", "1000"})
    private int listSize;

    @Setup
    public void init() {
        random = new Random();
        linkedList = new LinkedList<>();
        sortedLinkedList = new SortedLinkedList<>();
        List<Integer> randomIntegers = random.ints(0, listSize)
                .limit(listSize)
                .boxed()
                .toList();

        for (int i = 0; i < listSize; i++) {
            linkedList.add(randomIntegers.get(i));
            sortedLinkedList.add(randomIntegers.get(i));
        }
    }

    @Benchmark
    public void linkedListAdd() {
        linkedList.add(random.nextInt(Integer.MAX_VALUE));
    }

    @Benchmark
    public void sortedLinkedListAdd() {
        sortedLinkedList.add(random.nextInt(Integer.MAX_VALUE));
    }
}
