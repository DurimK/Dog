//Jonas Levin jole3800

import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {

    public static int sortDogs(Comparator<Dog>comparator, ArrayList<Dog> dogs) {
        int swap = 0;
        for (int i = 0; i < dogs.size(); i++) {
            int smallestDog = nextDog(comparator, dogs, i);
            if (i != smallestDog) {
                swapDogs(dogs, i, smallestDog);
                swap++;
            }
        }
        return swap;
    }

    private static void swapDogs(ArrayList<Dog> dogs, int a, int b) {
        Dog temp = dogs.get(a);
        dogs.set(a, dogs.get(b));
        dogs.set(b, temp);
    }

    private static int nextDog(Comparator<Dog> comparator,ArrayList<Dog>dogs, int a) {
        int currentMinIndex = a;

        for (int j = a + 1; j < dogs.size(); j++){
            if (comparator.compare(dogs.get(j), dogs.get(currentMinIndex)) < 0) {
                currentMinIndex = j;

            }

        }

        return currentMinIndex;

    }

}
