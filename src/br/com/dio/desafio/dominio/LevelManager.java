package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelManager {

    private static final int MAX_LEVEL = 10;
    private static final int RATIO_LEVEL = 2;
    private double xpToNextLevel = 10;
    private double xp = 0;
    private int level = 1;

    public LevelManager() {

    }

    public void updateXP(double xp) {
        this.xp = this.xp + xp;
        updateLevel();
    }

    private void updateLevel() {
        while (xpToNextLevel < this.xp && level < MAX_LEVEL) {
            xpToNextLevel = xpToNextLevel * RATIO_LEVEL;
            level++;
        }
    }

    /***
     * Create a geometric progression based in parameters
     * 
     * @param initalValue first value of the set
     * @param items       number of itens to generate
     * @param ratio       ratio of geometric progression
     * @return Set of Integers
     */
    private Set<Integer> calcGeometricProgression(int initalValue, int items, int ratio) {
        Set<Integer> geometricProgresssion = new LinkedHashSet<>();
        geometricProgresssion.add(0); // Level 1 = zero to BASE_XP_LEVEL
        for (int i = 1; i < items; i++) {
            geometricProgresssion.add(initalValue);
            initalValue = initalValue * ratio;
        }
        return geometricProgresssion;
    }

    @Override
    public String toString() {
        int complete = (int) ((xp * 10) / xpToNextLevel);
        String progressBar = level + " |" +
                Stream.generate(() -> "#").limit(10).collect(Collectors.joining()) +
                "| -\nCONGRATUATIONS, you've reached the max level!!\n";
        if (level < MAX_LEVEL) {
            progressBar = level + " |" +
                    Stream.generate(() -> "#").limit(complete).collect(Collectors.joining()) +
                    Stream.generate(() -> "-").limit(10 - complete).collect(Collectors.joining()) +
                    "| " + (level + 1) + "\n";
        }
        return "Current level: " + level + "\n" +
                progressBar +
                xp + "/" + xpToNextLevel;
    }

    public double getXp() {
        return xp;
    }

    public double getXpToNextLevel() {
        return xpToNextLevel;
    }

    public int getLevel() {
        return level;
    }

}
