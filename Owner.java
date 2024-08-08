//Jonas Levin jole3800

import java.util.ArrayList;

public class Owner implements Comparable<Owner> {

    private final ArrayList<Dog> dogs;

    private String name;

    public Owner(String name) {
        this.name = name.toUpperCase();
        this.dogs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if(dogs.isEmpty()) {
            return name;
        } else {
            StringBuilder dogNames = new StringBuilder();
            for (Dog dog : dogs) {
                dogNames.append(dog.getName()).append(" ");
            }
            dogNames.delete(dogNames.length() - 1, dogNames.length());
            return String.format("%s %s", name, dogNames);
        }
    }

    @Override
    public int compareTo(Owner secondOwner) {
        return this.name.compareToIgnoreCase(secondOwner.getName());
    }

    public boolean addDog(Dog newDog) {
        if (newDog == null) {
            return false;
        } else if (newDog.getOwner() == this) {
            if (dogs.contains(newDog)) {
                return false;
            }
            dogs.add(newDog);
            return true;
        } else if (newDog.getOwner() == null) {
            newDog.setOwner(this);
            return true;
        }

        return false;

    }

    public boolean removeDog(Dog dogToRemove) {
        if (dogToRemove == null) {
            return false;
        }

        for (Dog dog : dogs) {
            if (dog == dogToRemove) {
                dogs.remove(dogToRemove);
                dogToRemove.setOwner(null);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> dogsCopy = new ArrayList<>(dogs);
        DogNameComparator dogNameComparator = new DogNameComparator();
        DogSorter.sortDogs(dogNameComparator, dogsCopy);
        return dogsCopy;
    }

}