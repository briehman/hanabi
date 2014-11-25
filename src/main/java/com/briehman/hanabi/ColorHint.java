package com.briehman.hanabi;

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

    @Override
    public String toString() {
        return String.format("-> %s: %s", receiver, type());
    }
}
