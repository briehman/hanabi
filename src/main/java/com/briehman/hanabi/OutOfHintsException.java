package com.briehman.hanabi;


public class OutOfHintsException extends RuleException {
    public OutOfHintsException() {
        super("You are out of hints! Cannot give another hint");
    }
}
