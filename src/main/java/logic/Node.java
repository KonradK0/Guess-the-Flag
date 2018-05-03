package logic;

import java.util.List;
import java.util.function.Predicate;

public class Node {
    public String question;
    public Predicate<Flag> predicate;
    public boolean used;

    public Node(String question, Predicate<Flag> predicate) {
        this.question = question;
        this.predicate = predicate;
        this.used = false;
    }

    public double calcSplitRatio(List<Flag> flags) {
        if (!used) {
            double withPredicate = flags.stream().filter(predicate).count();
            double size = flags.size();
            double retVal = Math.abs(0.5 - (withPredicate / size));
            return retVal;
        }
        return Double.MAX_VALUE;
    }

}
