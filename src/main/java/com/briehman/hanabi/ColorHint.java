package com.briehman.hanabi;

public class ColorHint extends Hint {
    private String TYPE = "COLOR";
    private Color color;

    public ColorHint(Player sender, Player receiver, Color color) {
        super(sender, receiver);
        this.color = color;
    }

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s: %s", sender, receiver, type());
    }
}
