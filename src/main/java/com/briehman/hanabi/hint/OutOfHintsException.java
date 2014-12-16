package com.briehman.hanabi.hint;


import com.briehman.hanabi.RuleException;

public class OutOfHintsException extends RuleException {
    public OutOfHintsException() {
        super("You are out of hints! Cannot give another hint");
    }
}
