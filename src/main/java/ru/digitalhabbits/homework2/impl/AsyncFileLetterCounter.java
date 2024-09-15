package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCounter;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReader fileReader;
    private final LetterCounter letterCounter;
    private final LetterCountMerger letterCountMerger;
    private final ExecutorService executorService;

    public AsyncFileLetterCounter() {
        this.fileReader = new SimpleFileReader();
        this.letterCounter = new SimpleLetterCounter();
        this.letterCountMerger = new SimpleLetterCountMerger();
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public AsyncFileLetterCounter(FileReader fileReader,
                                  LetterCounter letterCounter,
                                  LetterCountMerger letterCountMerger) {
        this.fileReader = fileReader;
        this.letterCounter = letterCounter;
        this.letterCountMerger = letterCountMerger;
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public Map<Character, Long> count(File input) {
        try {
            return fileReader.readLines(input)
                    .map(line -> CompletableFuture.supplyAsync(() -> letterCounter.count(line), executorService))
                    .collect(Collectors.toList())
                    .stream()
                    .map(CompletableFuture::join)
                    .reduce(Collections.emptyMap(), letterCountMerger::merge);
        } finally {
            executorService.shutdown();
        }
    }
}
