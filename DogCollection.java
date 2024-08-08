//Jonas Levin jole3800

import java.util.ArrayList;

public class DogCollection {

    private final ArrayList<Dog> dogs = new ArrayList<>();

    public boolean addDog(Dog dog) {
        if (containsDog(dog.getName()))
            return false;
        return dogs.add(dog);
    }

    public boolean removeDog(Dog dog) {
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

    public boolean containsDog(String currentDog){
        currentDog = currentDog.toUpperCase();
        for (Dog dog : dogs){
            if (dog.getName().equals(currentDog)){
                return true;
            }
        }

        return false;

    }

    public boolean containsDog(Dog currentDog) {
        for (Dog dog : dogs){
            if (dog.getName().equals(currentDog.getName())){
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

    public Dog getDog(String selectedDog) {
        selectedDog = selectedDog.toUpperCase();
        for (Dog dog : dogs){
            if (dog.getName().equals(selectedDog)){
                return dog;
            }
        }

        return null;

    }

    public ArrayList<Dog> getDogsByTailLength(double d) {
        if (d == 0)
            DogSorter.sortDogs(new DogTailNameComparator(), dogs);
        else
            DogSorter.sortDogs(new DogNameComparator(), dogs);
        return new ArrayList<>(dogs.stream().filter(dog -> dog.getTailLength() >= d).toList());
    }

}