package EstateObjects;


import java.time.LocalDate;
import java.util.*;

public abstract class Room  {



    private final UUID id;
    private long area;
    private LocalDate[] startRent;
    private LocalDate[] endRent;

    private String primaryTenantID;

    public Room() {
        id = UUID.randomUUID();

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


    public String getPrimaryTenantID() {
        return primaryTenantID;
    }

    public void setPrimaryTenantID(String primaryTenantID) {
        this.primaryTenantID = primaryTenantID;
    }

    @Override
    public String toString() {
        return " nr " + id +
                "  area " + area +
                "  startRent " + Arrays.toString(startRent) +
                "  endRent " + Arrays.toString(endRent) +
                "  primaryTenant " + primaryTenantID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;

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
