//Jonas Levin jole3800

import java.util.ArrayList;
import java.util.Optional;

public class DogCollection {

    private final ArrayList<Dog> dogs = new ArrayList<>();

    public boolean addDog(Dog dog) {
        if (containsDog(dog.getName()))
            return false;
        return dogs.add(dog);
    }

    public boolean removeDog(Dog dog){
        if(dog != null && containsDog(dog) && dog.getOwner() == null){
            dogs.remove(dog);
            dog.setOwner(null);
            return true;
        }
        return false;
    }

    public boolean removeDog(String name){
        for (Dog dog : dogs) {
            if(dog.getName().equalsIgnoreCase(name)) {
                return removeDog(dog);
            }
        }
        return false;
    }

    public boolean containsDog(String name){
        for(Dog dog : dogs) {
            if(dog.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean containsDog(Dog dog) {
        if(dog == null) {
            return false;
        }
        return containsDog(dog.getName());
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> sortedDogs = new ArrayList<>(dogs);
        DogSorter.sortDogs(new DogNameComparator(), sortedDogs);
        return sortedDogs;
    }

    public Dog getDog(String name) {
        Optional<Dog> dog = dogs.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
        if (!containsDog(name) || dog.isEmpty())
            return null;
        return dog.get();
    }

    public ArrayList<Dog> getDogsByTailLength(double d) {
        if (d == 0)
            DogSorter.sortDogs(new DogTailNameComparator(), dogs);
        else
            DogSorter.sortDogs(new DogNameComparator(), dogs);
        return new ArrayList<>(dogs.stream().filter(dog -> dog.getTailLength() >= d).toList());
    }

}