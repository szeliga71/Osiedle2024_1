package Items;

import java.util.UUID;

public abstract class Item {

    private final UUID id;

    private final long size;

    public Item(long size) {

        this.id=UUID.randomUUID();
        this.size=size;
    }


    public UUID getId() {
        return id;
    }

    public long getSize() {
        return size;
    }

}

