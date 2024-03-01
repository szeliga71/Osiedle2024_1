package Environment;

import EstateObjects.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class File {

    UUID roomId;
    LocalDate endRentDate;

    String creationType;

    long limitationPeriod;

    public File(UUID roomId, LocalDate endRentDate) {
        this.roomId = roomId;
        this.endRentDate = endRentDate;
        this.creationType="Both";
    }

    public File(UUID roomId) {
        this.roomId = roomId;
        this.creationType="UUID";
    }

    @Override
    public String toString() {
        return "File{" +
                "roomId=" + roomId +
                ", endRentDate=" + endRentDate +
                ", creationType='" + creationType + '\'' +
                ", limitationPeriod=" + limitationPeriod +
                '}';
    }
}
