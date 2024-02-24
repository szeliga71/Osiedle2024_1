package EstateObjects;

import People.Person;

import java.time.LocalDate;
import java.util.Arrays;
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


    @Override
    public String toString() {
        return " nr "+ id +
                "  area " + area +
                "  startRent " + Arrays.toString(startRent) +
                "  endRent " + Arrays.toString(endRent) +
                "  primaryTenant " + primaryTenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (getArea() != room.getArea()) return false;
        return getId().equals(room.getId());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (int) (getArea() ^ (getArea() >>> 32));
        return result;
    }
}
