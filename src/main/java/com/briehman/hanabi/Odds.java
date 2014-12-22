package com.briehman.hanabi;

import com.briehman.hanabi.Color;

import java.util.HashMap;
import java.util.Map;

public class Odds {
    private Map<Color, Double> colorOdds;
    private Map<Integer, Double> numberOdds;

    public Odds() {
        colorOdds = new HashMap<>();
        numberOdds = new HashMap<>();
    }

    public void setOdds(Color color, Double percentage) {
        colorOdds.put(color, percentage);
    }

    public void setOdds(Integer num, Double percentage) {
        numberOdds.put(num, percentage);
    }

    public double getOdds(Color color) {
        return colorOdds.get(color);
    }

    public double getOdds(Integer number) {
        return numberOdds.get(number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Color, Double> entry : colorOdds.entrySet()) {
            sb.append(String.format("%s: %s%n", entry.getKey(), entry.getValue()));
        }
        for (Map.Entry<Integer, Double> entry : numberOdds.entrySet()) {
            sb.append(String.format("%s: %s%n", entry.getKey(), entry.getValue()));
        }

        if (sb.length() > 1)
            return sb.substring(0, sb.length() - 1);
        else
            return "No odds";
    }
}
