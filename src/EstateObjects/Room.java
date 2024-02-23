package EstateObjects;

import People.Person;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Room {



    private final UUID id;
    private long area;
    private LocalDate[] startRent;
    private LocalDate[] endRent;
    private Person primaryTenant;

    public Room(){
        id=UUID.randomUUID();

    }
    public void setArea(long area) {
        this.area = area;
    }

    public UUID getId() {
        return id;
    }

    public long getArea() {
        return area;
    }

    public LocalDate[] getStartRent() {
        return startRent;
    }

    public void setStartRent(LocalDate[] startRent) {
        this.startRent = startRent;
    }

    public LocalDate[] getEndRent() {
        return endRent;
    }

    public void setEndRent(LocalDate[] endRent) {
        this.endRent = endRent;
    }

    public Person getPrimaryTenant() {
        return primaryTenant;
    }

    public void setPrimaryTenant(Person primaryTenant) {
        this.primaryTenant = primaryTenant;
    }





}
