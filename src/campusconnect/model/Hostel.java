package campusconnect.model;

import java.util.ArrayList;
import java.util.List;

public class Hostel {
    private String name;
    private List<Room> rooms;
    public Hostel(String name)
    {
        this.name=name;
        this.rooms=new ArrayList<>();
    }
    public void addBlock(Room r)
    {
        rooms.add(r);
    }
    public Room findAvailableRoom()
    {
        for (Room room : rooms) {
            if (!room.isFull()) {
                return room;  // Found an available room for you to occupy...
            }
        }
        return null;
    }
}
