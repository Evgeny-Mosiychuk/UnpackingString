package model;

public class SymbolNode extends Node {
    private char value;

    SymbolNode(char ch) {
        value = ch;
    }

    @Override
    public String evaluate() {
        return Character.toString(value);
    }
}