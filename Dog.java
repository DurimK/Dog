//Jonas Levin jole3800

import java.util.ArrayList;

public class Dog {

    private static final double DACHSHUND_TAIL_LENGTH = 3.7;

    // Inkluderar alla typer av taxar, angivet i uppgiften.
    private static final ArrayList<String> DACHSHUND_TRANSLATIONS = new ArrayList<>(){{
        add("tax");
        add("dachshund");
        add("mäyräkoira");
        add("teckel");
    }};

    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private Owner owner;


    public Dog(String name, String breed, int age, int weight) {
        this.name = name.toUpperCase();
        this.breed = breed.toUpperCase();
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        double tailLength = (age * weight) / 10d;

        if (DACHSHUND_TRANSLATIONS.contains(breed.toLowerCase()))
            tailLength = DACHSHUND_TAIL_LENGTH;

        return tailLength;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        if (getOwner() == null)
            return "Name: " + name + " Age: " + age + " Breed: " + breed + " Weight: " + weight + "kg" + " Tail length: " + getTailLength() + " Owner: ";
        else
            return "Name: " + name + " Age: " + age + " Breed: " + breed + " Weight: " + weight + "kg" + " Tail length: " + getTailLength() + " Owner: " + getOwner().getName();
    }

    public boolean setOwner(Owner newOwner) {
        if (owner == null && newOwner != null) {
            owner = newOwner;
            newOwner.addDog(this);
            return true;
        }  else if (owner != null && newOwner == null) {
            owner.removeDog(this);
            owner = null;
            return true;
        }

        return false;

    }


    public void increaseAge() {
        if (age < Integer.MAX_VALUE)
            age++;
    }

}
