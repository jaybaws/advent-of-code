package org.jaybaws.adventofcode.y2019.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FeedbackThruster implements Thruster {

    private static final int FirstAmpInputValue = 0;
    private Amp[] amps;

    public FeedbackThruster(int[] program, int[] phaseSettings) {
        amps = new Amp[phaseSettings.length];
        Amp first = Amp.create("first", program, phaseSettings[0], FirstAmpInputValue);
        amps[0] = first;

        for (int i = 1; i < phaseSettings.length - 1; i++) {
            int phase = phaseSettings[i];
            String ampName = String.format("intermediate %d", i);
            amps[i] = Amp.create(ampName, program, phase, amps[i - 1].getOutput());
        }

        Amp last = Amp.create("last", program, phaseSettings[phaseSettings.length - 1], amps[amps.length - 2].getOutput());
        last.setOutput(first.getInput());
        amps[amps.length - 1] = last;
    }

    public FeedbackThruster(int[] phaseSettings) {
        this(Amp.defaultProgram, phaseSettings);
    }

    public int getOutput()  {
        int returnValue = -1;

        List<CompletableFuture<Integer>> futures = new ArrayList<CompletableFuture<Integer>>();
        for (Amp a : amps) {
            futures.add(
                    CompletableFuture.supplyAsync(() -> a.compute())
            );
        }

        try {
            for (Future<Integer> f : futures)
                returnValue = f.get();
        } catch (Throwable t) {
            throw new RuntimeException("Shit!", t);
        }

        return returnValue;
    }

    // https://www.baeldung.com/java-runnable-callable
}
