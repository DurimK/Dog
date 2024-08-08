//Jonas Levin jole3800

import java.util.ArrayList;
import java.util.Collections;

public class DogRegister {

    private static final String EXIT = "EXIT";
    private static final String NEW_OWNER = "REGISTER NEW OWNER";
    private static final String REMOVE_OWNER = "REMOVE OWNER";
    private static final String NEW_DOG = "REGISTER NEW DOG";
    private static final String REMOVE_DOG = "REMOVE DOG";
    private static final String LIST_OWNERS = "LIST OWNERS";
    private static final String LIST_DOGS = "LIST DOGS";
    private static final String INCREASE_AGE = "INCREASE AGE";
    private static final String GIVE_DOG = "GIVE DOG TO OWNER";
    private static final String REMOVE_DOG_FROM_OWNER = "REMOVE DOG FROM OWNER";

    private InputReader input = new InputReader();
    private OwnerCollection ownerCollection = new OwnerCollection();
    private DogCollection dogCollection = new DogCollection();
    //private ArrayList<Owner> ownerArrayList = new ArrayList<>();
    //private ArrayList<Dog> dogArrayList = new ArrayList<>();

    private void start(){
        initilize();
        runCommandLoop();
    }
    private void initilize(){
        System.out.println("COMMANDS: EXIT, REGISTER NEW OWNER, REMOVE OWNER, REGISTER NEW DOG, REMOVE DOG");
        System.out.println("LIST OWNERS, LIST DOGS, INCREASE AGE, GIVE DOG TO OWNER, REMOVE DOG FROM OWNER\n");

    }
    private void runCommandLoop(){

        String command;

        do{
            command = readCommand().trim().toUpperCase();
            handleCommand(command);
        }
        while(!command.equals(EXIT));
    }
    public String readCommand(){
        return input.readLine("Command");
    }
    /*
     * Handles the command chain for the different user inputs.
     * @param command The string command that the user inputs.
     */
    private void handleCommand(String command){
        switch (command) {
            case EXIT:
                System.out.println("Dog register shuts down");
                break;
            case NEW_OWNER:
                registerOwner();
                break;
            case REMOVE_OWNER:
                removeOwner();
                break;
            case NEW_DOG:
                registerDog();
                break;
            case REMOVE_DOG:
                removeDog();
                break;
            case LIST_OWNERS:
                listOwners();
                break;
            case LIST_DOGS:
                listDogs();
                break;
            case INCREASE_AGE:
                increaseDogAge();
                break;
            case GIVE_DOG:
                giveDogToOwner();
                break;
            case REMOVE_DOG_FROM_OWNER:
                removeDogFromOwner();
                break;
            default:
                System.out.println("Error: invalid input, try again.\n");
                break;
        }
    }
    /*
     * Handles the 'REGISTER NEW OWNER' command.
     * Adds the new owner to the arraylist, or sends user back to menu if owner already exists.
     */
    private void registerOwner(){
        String ownerName = getValidOwnerName();
        if(ownerName != null){
            System.out.println("The owner " + ownerName + " has been added to the register.\n");
            ownerCollection.addOwner(new Owner(ownerName));
        }
    }
    /*
     * Handles the 'REMOVE OWNER' command
     * Removes owner from arraylist, or sends user back to menu if owner cant be found.
     */
    private void removeOwner() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register");
            return;
        }

        String ownerName = input.readLine("Enter owner name");
        Owner ownerToRemove = findOwner(ownerName);

        if (ownerToRemove != null) {
            doRemoveOwner(ownerToRemove);
        }
    }

    private void doRemoveOwner(Owner ownerToRemove) {
        if (!ownerToRemove.getDogs().isEmpty()) {
            for (Dog dog : ownerToRemove.getDogs()) {
                ownerToRemove.removeDog(dog);
                dogCollection.removeDog(dog);
            }
        }
        ownerCollection.removeOwner(ownerToRemove);
        System.out.println("The owner " + ownerToRemove + " has been removed");
    }

    /*
     * Handles the 'REGISTER NEW DOG' command
     * Adds new dog to arraylist, or send user back to menu if dog already exists.
     */
    private void registerDog(){

        String dogName = getValidDogName();

        if(dogName != null){  //Make sure that the duplicate Error can exit correctly
            String dogBreed = getValidBreed();
            int dogAge = getValidAge();
            int dogWeight = getValidWeight();

            //Dog successfully added
            System.out.println("The dog " + dogName + " has been added to the register.\n");
            dogCollection.addDog(new Dog(dogName, dogBreed, dogAge, dogWeight));
        }
    }
    /*
     * Handles the 'REMOVE DOG' command
     * Remove dog from the arraylist, or sends user back to menu if dog cant be found.
     */
    private void removeDog(){

        if(dogRegisterIsEmpty()){   //If register is empty
            //errors have been printed and goes back to menu
            return;
        }

        String dogName = input.readLine("Enter dog name");  //Get dog name
        while(dogNameIsBlank(dogName)){
            dogName = input.readLine("Enter dog name");  //Get dog name
        }

        if(!dogCollection.containsDog(dogName)){    //If dog dont exist in list
            System.out.println("Error: The dog " + dogName +" doesn't exist.\n");
            return;
        } else {  //If dog is in register.
            Dog dogToRemove = dogCollection.getDog(dogName); //Find dog with name

            if(dogToRemove.getOwner() != null){ //If dog has owner
                Owner owner = dogToRemove.getOwner();
                owner.removeDog(dogToRemove);
                dogToRemove.setOwner(null);
            }
            dogCollection.removeDog(dogToRemove);   //Remove dog
            System.out.println("The dog " + dogName + " has been removed from the register.\n");
            return;
        }
    }

    /*
     * Handles the 'LIST OWNERS' command
     * Shows a list of all of the owners, or sends user back to menu if there are no owners.
     */
    private void listOwners(){
        if(ownerRegisterIsEmpty()){
            //Sends error and return back to menu
            return;
        }
        else{
            ArrayList<Owner> sortedOwners = new ArrayList<>(ownerCollection.getOwners());
            Collections.sort(sortedOwners);

            System.out.println("\nNAME" + printString(" ", 5) + "DOGS");
            System.out.println(printString("=", "name".length() + "dogs".length() + 5));

            for(Owner owner : sortedOwners){
                System.out.println(owner.toString());
            }
            System.out.println();   //new line
        }
    }
    /*
     * Handles the 'LIST DOGS' command
     * Shows a list of all of the dogs, or send user back to menu if there are no dogs in arraylist.
     */
    private void listDogs(){
        double minTailLength = 0;

        if(dogRegisterIsEmpty()){
            return;
        }
        else{
            minTailLength = input.readDecimal("Enter minimum tail length");

            //Success
            String intro = "NAME BREED AGE WEIGHT TAIL OWNER";
            System.out.println("\n" + intro);
            System.out.println(printString("=", intro.length()));

            //Each dog line in list
            for (Dog dog : dogCollection.getDogsByTailLength(minTailLength)) {
                System.out.println(dog.toString());
            }
            System.out.println();   //new line
        }
    }
    /*
     * Handles the 'INCREASE AGE' command.
     * Increases age of specific dog with the name, sends user back to menu if dog cant be found.
     */
    private void increaseDogAge (){
        if(dogRegisterIsEmpty()){
            return;
        }
        String dogName = input.readLine("Enter dog name");
        if(!dogCollection.containsDog(dogName)){    //if dog isnt in register
            System.out.println("Error: The dog " + dogName + " doesn't exist.\n");
            return;
        }else{
            dogCollection.getDog(dogName).increaseAge();
            System.out.println("The dog " + dogName + " is now one year older.\n");
            return;
        }
    }

    /*
     * Handles the 'GIVE DOG TO OWNER' command
     * Gives a specific dog to a specified owner, sends user back to menu if dog or owner cant be found.
     */
    private void giveDogToOwner() {

        if(dogRegisterIsEmpty() || ownerRegisterIsEmpty()){ //Checks for empty registers
            return;
        }

        String dogName = input.readLine("Enter dog name");
        Dog dog = dogCollection.getDog(dogName);

        if(!dogCollection.containsDog(dog) || dog == null){
            System.out.println("Error: The dog " + dogName + " doesn't exist in register.\n");
            return;
        }
        if(dogCollection.getDog(dogName).getOwner() != null){
            System.out.println("Error: The dog " + dogName + " is already owned by the owner " + dogCollection.getDog(dogName).getOwner() + "\n");
            return;
        }
        String ownerName = input.readLine("Enter owner name");
        Owner owner = ownerCollection.getOwner(ownerName);

        if(!ownerCollection.containsOwner(ownerName)){
            System.out.println("Error: The owner " + ownerName + " doesn't exist in register.\n");
            return;
        }

        //Success
        owner.addDog(dogCollection.getDog(dogName));
        System.out.println("The dog " + dogName + " is now owned by " + ownerName + ".\n");
        return;
    }

    /*
     * Handles the 'REMOVE DOG FROM OWNER'
     * Removes specific dog from specified owner, sends user back to menu if dog or owner cant be found.
     */
    private void removeDogFromOwner() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register");
        }

        else if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register");
        }

        else {
            String dogName = input.readLine("Enter dog name");
            Dog ownedDog = findDog(dogName);

            if (ownedDog != null) {
                if (ownedDog.getOwner() == null) {
                    System.out.println("The dog " + dogName + " has no owner");
                } else {
                    ArrayList<Dog> ownerDogsCopy = new ArrayList<>(ownedDog.getOwner().getDogs());
                    ownedDog.getOwner().removeDog(ownedDog);

                    for (Dog dog : ownerDogsCopy) {
                        System.out.println("The dog " + dog.getName() + " now has no owner");
                    }
                }
            }
        }
    }

    private Dog findDog(String dogName) {
        Dog dog = dogCollection.getDog(dogName);
        if (dog == null) {
            System.out.println("Error: The dog " + dogName + " doesn't exist");
            return null;
        }
        return dog;
    }

    private Owner findOwner(String ownerName) {
        Owner owner = ownerCollection.getOwner(ownerName);
        if (!ownerCollection.containsOwner(ownerName)) {
            System.out.println("Error: The owner " + ownerName + " doesn't exist");
            return null;
        }
        return owner;
    }

    /*
     * Private help methods for reduction of repetition, aswell as readability of code.
     */
    private boolean ownerRegisterIsEmpty(){
        if(ownerCollection.getOwners().isEmpty()){ //If registry lack Owners or Dogs
            System.out.println("Error: No owners in register.\n");
            return true;
        }
        return false;
    }

    private boolean dogRegisterIsEmpty(){
        if(dogCollection.getDogs().isEmpty()){ //If registry lack Owners or Dogs
            System.out.println("Error: No dogs in register.\n");
            return true;
        }
        return false;
    }

    private String getValidOwnerName() {
        while (true) {
            String ownerName = input.readLine("Enter owner name");

            if (ownerName.trim().isEmpty()) {
                System.out.println("Error: A blank string is not allowed, try again.");
                continue;   //Return to next loop for new user input.
            }
            if (ownerCollection.containsOwner(ownerName)) { //If owner already exist
                System.out.println("Error: The owner " + ownerName + " already exists in the register.\n");
                return null;
            } else {
                return ownerName;
            }
        }
    }

    private String getValidDogName() {
        while (true) {
            String dogName = input.readLine("Enter dog name");

            if (dogName.trim().isEmpty()) {
                System.out.println("Error: A blank string is not allowed, try again.");
                continue;
            }
            if (dogCollection.containsDog(dogName)) {   //If dog already exist
                System.out.println("Error: The dog " + dogName + " already exists in the register.\n");
                return null;
            } else {
                return dogName;
            }
        }
    }
    private boolean ownerNameIsBlank(String ownerName) {
        if (ownerName.trim().isEmpty()) {
            System.out.println("Error: A blank string is not allowed, try again.");
            return true;
        }
        return false;
    }
    private boolean dogNameIsBlank(String dogName) {
        if (dogName.trim().isEmpty()) {
            System.out.println("Error: A blank string is not allowed, try again.");
            return true;
        }
        return false;
    }
    private String getValidBreed() {
        while (true) {
            String breed = input.readLine("Enter dog breed");

            if (!breed.trim().isEmpty()) { // If breed is not blank
                return breed;
            }
            System.out.println("Error: A blank string is not allowed, try again.");
        }
    }
    private int getValidAge() {
        while (true) {
            int age = input.readInteger("Enter dog age");
            if (age > 0) {
                return age;
            }
            System.out.println("Error: Age cannot be negative, try again.");
        }
    }
    private int getValidWeight() {
        while (true) {
            int weight = input.readInteger("Enter dog weight");
            if (weight > 0) {
                return weight;
            }
            System.out.println("Error: Weight cannot be negative or 0, try again.");
        }
    }
    private String printString(String x, int times){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < times + 1; i++){
            stringBuilder.append(x);
        }
        return stringBuilder.toString();
    }
    //Main method for start of program.
    public static void main(String[] args){
        new DogRegister().start();
    }
}