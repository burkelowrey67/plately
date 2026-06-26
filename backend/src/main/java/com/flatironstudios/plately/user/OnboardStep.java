package com.flatironstudios.plately.user;

public enum OnboardStep {
    WELCOME(0),
    NAME(1),
    HOUSEHOLD(2),
    MEMBERS(3),
    COMPLETED(4);

    private final int value;

    private OnboardStep(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
