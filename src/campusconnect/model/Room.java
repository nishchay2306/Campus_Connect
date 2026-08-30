package campusconnect.model;

import campusconnect.exceptions.RoomFullException;

import java.util.List;
import java.util.ArrayList;

public class Room {
    private String roomNumber;
    private int capacity;
    private List<Student> occupants;

    public Room(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.occupants = new ArrayList<>();
    }

    public void addStudent(Student s) throws RoomFullException {
        if (capacity <= occupants.size()) {
            throw new RoomFullException("Room is Full");
        } else
            occupants.add(s);
    }

    public void removeStudent(Student s) {
        occupants.remove(s);
    }

    public int getCount() {
        return occupants.size();
    }
    public boolean isFull(){
        return occupants.size() >=capacity;
    }
}




