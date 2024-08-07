//Jonas Levin jole3800

import java.util.Comparator;

public class DogTailNameComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog first, Dog second) {
        int tailComparison = Double.compare(first.getTailLength(), second.getTailLength());
        if(tailComparison != 0) {
            return tailComparison;
        } else {
            return first.getName().compareTo(second.getName());
        }
    }

}