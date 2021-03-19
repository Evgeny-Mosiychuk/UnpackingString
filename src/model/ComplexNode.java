package model;

import model.exception.InvalidInputStringException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComplexNode extends Node {
    private List<Node> nodeList = new ArrayList<>();

    @Override
    public String evaluate() {
        return nodeList.stream().map(Node::evaluate).collect(Collectors.joining());
    }

    public ComplexNode(String value) {
        int currentIndex = 0;
        int repeatsCount = 0;
        EvaluatingType previousType = EvaluatingType.LETTER;
        while(currentIndex < value.length()) {
            char ch = value.charAt(currentIndex);

            if (Character.isLetter(ch)) {
                if (previousType == EvaluatingType.DIGIT) {
                    throw new InvalidInputStringException();
                }

                nodeList.add(new SymbolNode(ch));
                currentIndex++;
                previousType = EvaluatingType.LETTER;
                continue;
            }

            if (Character.isDigit(ch)) {
                if (previousType == EvaluatingType.DIGIT) {
                    throw new InvalidInputStringException();
                }

                repeatsCount = readNumber(value.substring(currentIndex));
                int numberLength = Integer.toString(repeatsCount).length();
                currentIndex += numberLength;
                previousType = EvaluatingType.DIGIT;
                continue;
            }

            if (ch == '[') {
                if (previousType != EvaluatingType.DIGIT) {
                    throw new InvalidInputStringException();
                }

                String subNode = readSubNode(value.substring(currentIndex));
                nodeList.add(new BracketNode(repeatsCount, subNode));
                currentIndex += subNode.length() + 2;
                previousType = EvaluatingType.NODE;
                continue;
            }

            throw new InvalidInputStringException();
        }
    }

    private int readNumber(String value) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (Character.isDigit(ch)) {
                result.append(ch);
            } else {
                break;
            }
        }
        return Integer.parseInt(result.toString());
    }

    private String readSubNode(String value) {
        StringBuilder result = new StringBuilder();
        int bracketsCount = 0;
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);

            if (ch == '[') {
                bracketsCount++;
            }

            if (ch == ']') {
                bracketsCount--;
            }

            result.append(ch);

            if (bracketsCount == 0) {
                break;
            }

        }

        return result.deleteCharAt(result.length() - 1).deleteCharAt(0).toString();
    }
}