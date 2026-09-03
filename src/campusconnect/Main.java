package campusconnect;
import campusconnect.exceptions.InvalidPaymentException;
import campusconnect.exceptions.RoomFullException;
import campusconnect.generics.CustomArrayList;
import campusconnect.generics.Repository;
import campusconnect.model.*;
import campusconnect.util.FilePersistence;

import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        Repository<Student> studentRepo=new Repository<>();
        Repository<Complaint> complaintRepo = new Repository<>();
        Repository<Warden> wardenRepo = new Repository<>();
        Hostel hostel = new Hostel("Main Hostel");

        boolean running= true;
        while(running)
        {
            System.out.println("\n--- CampusConnect Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Warden");
            System.out.println("3. Add Room");
            System.out.println("4. Allocate Room to Student");
            System.out.println("5. Make Payment");
            System.out.println("6. Raise Complaint");
            System.out.println("7. Resolve Complaint");
            System.out.println("8. View Defaulters");
            System.out.println("9. Save Students to File");
            System.out.println("10. Load Students from File");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 0:
                    running = false;
                    break;
                case 1: {
                    System.out.println("Enter name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter id: ");
                    String id = sc.nextLine();
                    System.out.println("Enter Contact Number: ");
                    String contactNumber = sc.nextLine();
                    System.out.println("Enter Room Number: ");
                    String roomNumber = sc.nextLine();
                    System.out.println("Enter admission year: ");
                    int admissionYear = sc.nextInt();
                    sc.nextLine();

                    Student s = new Student(name, id, contactNumber, roomNumber, admissionYear);
                    studentRepo.add(s);
                    System.out.println("Student added successfully!");
                    break;
                }
                case 2: {
                    System.out.println("Enter name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter id: ");
                    String id = sc.nextLine();
                    System.out.println("Enter Contact Number: ");
                    String contactNumber = sc.nextLine();
                    System.out.println("Enter Assigned Block: ");
                    String assignedBlock = sc.nextLine();
                    Warden w = new Warden(name, id, contactNumber, assignedBlock);
                    wardenRepo.add(w);
                    System.out.println("Warden added successfully!");
                    break;
                }
                case 3: {
                    System.out.println("Enter Room Number: ");
                    String roomNumber = sc.nextLine();
                    System.out.println("Enter Room capacity: ");
                    int capacity = sc.nextInt();

                    Room r = new Room(roomNumber, capacity);
                    hostel.addBlock(r);
                    break;
                }
                case 4: {
                    System.out.println("Enter Student ID: ");
                    String studentId = sc.nextLine();
                    Student student = studentRepo.findOne(s -> s.getId().equals(studentId));

                    System.out.println("Enter room number: ");
                    String roomNumber = sc.nextLine();
                    Room room = hostel.findRoomByNumber(roomNumber);

                    if (student == null) {
                        System.out.println("No student found with that ID");
                    } else if (room == null) {
                        System.out.println("No room found with that number");
                    } else {
                        try {
                            room.addStudent(student);
                            System.out.println("Room allocated to student successfully!");
                        } catch (RoomFullException e) {
                            System.out.println("Room is fully occupied, no space left.");
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("Enter Student ID: ");
                    String studentId = sc.nextLine();
                    Student student = studentRepo.findOne(s -> s.getId().equals(studentId));
                    System.out.println("enter payment amount: ");
                    if(student==null)
                    {
                        System.out.println("No student found with that ID");
                    }else{
                        double amount=sc.nextDouble();
                        try{
                            student.makePayment(amount);
                            System.out.println("Payment Successful");
                        }catch (InvalidPaymentException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
                case 6:
                {
                    System.out.println("Enter Student ID: ");
                    String studentId = sc.nextLine();
                    Student student = studentRepo.findOne(s -> s.getId().equals(studentId));
                    if(student==null)
                    {
                        System.out.println("No student found with that ID");
                    }else{
                        System.out.println("Enter details :");
                        String description= sc.nextLine();
                        System.out.println("Date");
                        String dateRaised=sc.nextLine();
                        String status="Open";
                        Complaint complaint=new Complaint(student,description,status,dateRaised);
                        complaintRepo.add(complaint);
                    }
                    break;
                }
                case 7:
                {
                    System.out.println("Enter Warden ID: ");
                        String wardenId = sc.nextLine();
                        Warden warden = wardenRepo.findOne(w -> w.getId().equals(wardenId));

                        if (warden == null) {
                            System.out.println("No warden found with that ID.");
                        } else {
                            System.out.println("Enter Student ID (whose complaint to resolve): ");
                            String studentId = sc.nextLine();
                            Complaint complaint = complaintRepo.findOne(c -> c.student.getId().equals(studentId));

                            if (complaint == null) {
                                System.out.println("No complaint found for that student.");
                            } else {
                                warden.resolveComplaint(complaint);
                            }
                        }
                    break;
                }
                case 8:
                {
                    CustomArrayList<Student> defaulters = studentRepo.filter(s -> s.calculateDue() > 0);
                    if (defaulters.isEmpty()) {
                        System.out.println("No defaulters found.");
                    } else {
                        System.out.println("--- Defaulters ---");
                        for (int i = 0; i < defaulters.size(); i++) {
                            Student s = defaulters.get(i);
                            System.out.println(s.getName() + " (ID: " + s.getId() + ") owes $" + s.calculateDue());
                        }
                    }
                    break;
                }
                case 9:
                {
                    FilePersistence.saveStudents(studentRepo.getAll(), "students.txt");
                    System.out.println("Students saved to file.");
                    break;
                }
                case 10:
                {
                    CustomArrayList<Student> loaded = FilePersistence.loadStudents("students.txt");
                    for (int i = 0; i < loaded.size(); i++) {
                        studentRepo.add(loaded.get(i));
                    }
                    System.out.println("Students loaded from file.");
                    break;
                }
            }
        }
        System.out.println("Exiting CampusConnect. Goodbye!");
    }
}
