package People;

import Environment.File;

import java.util.ArrayList;
import java.util.List;

public class Person {


    private final String pesel;
    private final String firstName;
    private final String lastName;
    private Nation nationality;
    private List<File> files;

    public Person(String pesel,String firstName,String lastName,Nation nationality){
        this.pesel=pesel;
        this.firstName=firstName;
        this.lastName=lastName;
        this.nationality=nationality;

        files=new ArrayList<>();
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Nation getNationality() {
        return nationality;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return  "PESEL : "+pesel + "  , imie    "+firstName +", nazwisko    "+ lastName +", narodowosc    "+ nationality+" "+ getFiles().size()+" "+getFiles()+".";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!getPesel().equals(person.getPesel())) return false;
        if (!getFirstName().equals(person.getFirstName())) return false;
        if (!getLastName().equals(person.getLastName())) return false;
        return getNationality() == person.getNationality();
    }

    @Override
    public int hashCode() {
        int result = getPesel().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getNationality().hashCode();
        return result;
    }
}
