//Jonas Levin jole3800

import java.util.Comparator;

public class DogNameComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog firstDog, Dog secondDog) {
        return firstDog.getName().compareTo(secondDog.getName());

    }

}
