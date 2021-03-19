package model;

public class BracketNode extends Node {
    private int repeatsCount;
    private Node subNode;

    @Override
    public String evaluate() {
        return subNode.evaluate().repeat(repeatsCount);
    }

    BracketNode(int repeatsCount, String subNode) {
        this.repeatsCount = repeatsCount;
        this.subNode = new ComplexNode(subNode);
    }
}