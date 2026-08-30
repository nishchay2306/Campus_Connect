package campusconnect.model;

public abstract class Person {

    protected String name;
    protected String id;
    protected String contactNumber;

    public Person(String name, String id, String contactNumber)
    {
        this.name=name;
        this.id=id;
        this.contactNumber=contactNumber;
    }
    public abstract void displayRole();
    public String getName() { return name; }
    public String getId() { return id; }
    public String getContactNumber() { return contactNumber; }

}
