//Jonas Levin jole3800

import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {

    public static int sortDogs(Comparator<Dog> comparator, ArrayList<Dog> dog) {
        int totalSwaps = 0;

        for(int i = 0; i < dog.size(); i++) {
            int index = nextDog(comparator, dog, i);

            if(index != i) {
                swapDogs(dog, i, index);
                totalSwaps++;
            }
        }
        //return the amount of swaps made.
        return totalSwaps;
    }

    private static ArrayList<Dog> swapDogs(ArrayList<Dog> arrayList, int firstIndex, int secondIndex) {
        Dog tempDog = arrayList.get(firstIndex);

        arrayList.set(firstIndex, arrayList.get(secondIndex));
        arrayList.set(secondIndex, tempDog);

        return arrayList;
    }

    private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> dog, int startIndex) {
        int index = startIndex;

        for(int i = startIndex + 1; i < dog.size(); i++) {
            if(comparator.compare(dog.get(i), dog.get(index)) < 0) {
                index = i;
            }
        }
        return index;
    }

}
