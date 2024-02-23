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
        return  pesel + " "+firstName +" "+ lastName +" "+ nationality ;

    }
}
