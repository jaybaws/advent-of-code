package org.jaybaws.adventofcode.y2020.day24;
import junit.framework.Assert;
import org.junit.Test;
import java.util.*;

public class Y2020D24PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "sesenwnenenewseeswwswswwnenewsewsw",
            "neeenesenwnwwswnenewnwwsewnenwseswesw",
            "seswneswswsenwwnwse",
            "nwnwneseeswswnenewneswwnewseswneseene",
            "swweswneswnenwsewnwneneseenw",
            "eesenwseswswnenwswnwnwsewwnwsene",
            "sewnenenenesenwsewnenwwwse",
            "wenwwweseeeweswwwnwwe",
            "wsweesenenewnwwnwsenewsenwwsesesenwne",
            "neeswseenwwswnwswswnw",
            "nenwswwsewswnenenewsenwsenwnesesenew",
            "enewnwewneswsewnwswenweswnenwsenwsw",
            "sweneswneswneneenwnewenewwneswswnese",
            "swwesenesewenwneswnwwneseswwne",
            "enesenwswwswneneswsenwnewswseenwsese",
            "wnwnesenesenenwwnenwsewesewsesesew",
            "nenewswnwewswnenesenwnesewesw",
            "eneswnwswnwsenenwnwnwwseeswneewsenese",
            "neswnwewnwnwseenwseesewsenwsweewe",
            "wseweeenwnesenwwwswnew"
    } );

    @Test
    public void part1_sample_validationTest() { Assert.assertEquals(10, new Y2020D24Puzzle(sample1).solution1()); }

    @Test
    public void part1_actual_validationTest() { Assert.assertEquals(230, new Y2020D24Puzzle().solution1()); }

    @Test
    public void part2a_sample_validationTest() { Assert.assertEquals(15, new Y2020D24Puzzle(sample1).solution2(1)); }

    @Test
    public void part2b_sample_validationTest() { Assert.assertEquals(12, new Y2020D24Puzzle(sample1).solution2(2)); }

    @Test
    public void part2c_sample_validationTest() { Assert.assertEquals(25, new Y2020D24Puzzle(sample1).solution2(3)); }

    @Test
    public void part2d_sample_validationTest() { Assert.assertEquals(37, new Y2020D24Puzzle(sample1).solution2(10)); }

    @Test
    public void part2e_sample_validationTest() { Assert.assertEquals(2208, new Y2020D24Puzzle(sample1).solution2(100)); }

    @Test
    public void part2_actual_validationTest() { Assert.assertEquals(3565, new Y2020D24Puzzle().solution2()); }

}