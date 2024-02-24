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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (getSize() != item.getSize()) return false;
        return getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (int) (getSize() ^ (getSize() >>> 32));
        return result;
    }
}

