package com.briehman.hanabi.hint;

import com.briehman.hanabi.Color;
import com.briehman.hanabi.Player;

public class ColorHint extends Hint {
    private String TYPE = "COLOR";
    private Color color;

    public ColorHint(Player receiver, Color color) {
        super(receiver);
        this.color = color;
    }

    @Override
    public String type() {
        return TYPE;
    }

    public Color color() { return color; }

    @Override
    public String toString() {
        return String.format("-> %s: %s", receiver, type());
    }
}
