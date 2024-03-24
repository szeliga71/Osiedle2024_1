package Environment;

import EstateObjects.Room;
import Items.Item;
import People.Person;

import java.util.*;

public abstract class Util {


    protected Person person;
    protected Scanner scan;
    protected Set<Person> personsSet;
    protected Set<Room> roomSet;

    protected Set<Item> items;

    protected Map<UUID, String> estate;


    public Util() {


        this.personsSet = new HashSet<>();
        this.roomSet = new HashSet<>();
        this.items = new HashSet<>();
        this.estate = new HashMap<>();
        this.scan = new Scanner(System.in);
    }

    public Person getPerson(String pesel) {

        Person user = null;
        for (Person person : getPersonsSet()) {
            if (pesel.equals(person.getPesel())) {
                user = person;
            }
        }
        return user;
    }

    public Set<Person> getPersonsSet() {
        return personsSet;
    }

    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Map<UUID, String> getEstate() {
        return estate;
    }

    public <T> void show(List<T> list) {
        if (!list.isEmpty()) {
            int i = 1;
            for (T item : list) {
                System.out.println((i++) + ". " + item);
            }
        }
    }


    public Person choosePerson(Set<Person> personsSet) {

        System.out.println(" Prosze podac pesel osoby ktora chcesz wybrac  : " + '\n');
        show(allPersons(personsSet));
        System.out.println();
        String pesel = choose(scan);
        return getPerson(pesel);
    }

    public String choose(Scanner scan) {

        System.out.println(" wybierz odpowiedni numer !");
        return scan.nextLine();
    }

    public List<Person> allPersons(Set<Person> persons) {
        return new ArrayList<>(persons);
    }

    public Optional<Person> chooseOptionalPerson(Set<Person> personsSet) {

        Optional<Person> person = Optional.empty();

        System.out.println(" Prosze podac pesel osoby ktora chcesz wybrac  : " + '\n');
        show(allPersons(personsSet));
        System.out.println();
        String pesel = choose(scan);
        for (Person p : personsSet) {
            if (p.getPesel().equals(pesel)) {
                person = Optional.of(p);
            }
        }
        return person;
    }
}
