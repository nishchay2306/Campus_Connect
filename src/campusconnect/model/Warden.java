package campusconnect.model;

import campusconnect.interfaces.Notifiable;

public class Warden extends Person implements Notifiable {
    protected String assignedBlock;
    public Warden(String name, String id, String contactNumber,String assignedBlock)
    {
        super(name,id,contactNumber);
        this.assignedBlock=assignedBlock;
    }
    @Override
    public void displayRole()
    {
        System.out.println("I am the Warden.");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Assigned Block: " + assignedBlock);
    }
    public void resolveComplaint(Complaint c) {
            c.setStatus("Resolved");
            String notification = "Your complaint regarding \"" + c.description + "\" has been resolved.";
            c.student.sendNotification(notification);
        }
    }


