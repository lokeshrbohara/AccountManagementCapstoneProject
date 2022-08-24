package com.example.accountManagement.validators;

public class TransactionResult {
    private final boolean first;
    private final String second;

    public TransactionResult(boolean first, String second) {
        this.first = first;
        this.second = second;
    }

    public boolean getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}
