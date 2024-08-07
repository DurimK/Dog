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

    public boolean addDog(Dog dog) {
        if (dog != null && (dog.getOwner() == null || dog.getOwner() == this) && !dogs.contains(dog))
            return dogs.add(dog);
        return false;
    }

    public boolean removeDog(Dog dog) {
        return dogs.remove(dog);
    }

    public ArrayList<Dog> getDogs() {
        return new ArrayList<>(dogs);
    }

}