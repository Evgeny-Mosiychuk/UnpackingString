import model.ComplexNode;
import model.Node;
import model.exception.InvalidInputStringException;

public class Main {
    public static void main(String[] args) {
//        String input = "3[xyz]4[xy]z";
        String input = "2[3[]x]y]";

        Node node;

        try {
            node = new ComplexNode(input);
        } catch (InvalidInputStringException ex) {
            throw ex;
        }
        System.out.println(node.evaluate());
    }
}

