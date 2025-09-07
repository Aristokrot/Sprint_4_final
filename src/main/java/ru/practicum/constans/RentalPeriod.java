package ru.practicum.constans;

public enum RentalPeriod {
    ONE_DAY("сутки"),
    TWO_DAYS("двое суток"),
    THREE_DAYS("трое суток"),
    FOUR_DAYS("четверо суток"),
    FIVE_DAYS("пятеро суток"),
    SIX_DAYS("шестеро суток"),
    SEVEN_DAYS("семеро суток");

    private final String displayText;

    RentalPeriod(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
