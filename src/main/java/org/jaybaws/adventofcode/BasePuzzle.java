package org.jaybaws.adventofcode;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePuzzle implements Puzzle {

    protected final List<String> puzzleInput;

    public BasePuzzle() {
        String resourceName = StringUtils.substringBeforeLast(this.getClass().getCanonicalName(), ".") + ".txt";

        try (InputStreamReader streamReader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            List<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null)
                lines.add(line);

            puzzleInput = ListUtils.unmodifiableList(lines);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        prepare();
    }

    public BasePuzzle(List<String> input) {
        puzzleInput = ListUtils.unmodifiableList(input);
        prepare();
    }

    protected abstract void prepare();

}