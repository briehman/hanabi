package com.briehman.hanabi.deck;

import com.briehman.hanabi.Color;
import com.briehman.hanabi.hint.Hint;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Card {
    private final Color color;
    private final int num;
    private List<Hint> hints;

    public Card(Color color, int num) {
        this.color = color;
        this.num = num;
        this.hints = new ArrayList<>();
    }

    public Color color() { return color; }
    public int number() { return num; }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(color)
                .append(num)
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Card other = (Card) obj;
        return new EqualsBuilder()
                .append(color, other.color)
                .append(num, other.num)
                .isEquals();
    }

    public List<Hint> getHints() {
        return Collections.unmodifiableList(hints);
    }

    public void addHint(Hint hint) {
        hints.add(hint);
    }
}
