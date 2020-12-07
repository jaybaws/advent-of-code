package org.jaybaws.adventofcode.y2019.day7;

public class LinearThruster implements Thruster {

    private static final int FirstAmpInputValue = 0;
    private Amp[] amps;

    public LinearThruster(int[] program, int[] phaseSettings) {
        amps = new Amp[phaseSettings.length];
        for (int i = 0; i < phaseSettings.length; i++) {
            int phase = phaseSettings[i];
            String ampName = String.format("Amp %d", i);
            if (i == 0) {
                amps[i] = Amp.create(ampName, program, phase, FirstAmpInputValue);
            } else {
                amps[i] = Amp.create(ampName, program, phase, amps[i - 1].getOutput());
            }
        }
    }

    public LinearThruster(int[] phaseSettings) {
        this(Amp.defaultProgram, phaseSettings);
    }

    public int getOutput() {
        for (Amp a : amps) {
            a.process();
        }

        return amps[amps.length - 1].getOutput().peek();
    }

}