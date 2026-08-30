package campusconnect.model;


import campusconnect.exceptions.InvalidPaymentException;
import campusconnect.interfaces.Notifiable;
import campusconnect.interfaces.Payable;

public class Student extends Person implements Payable, Notifiable, Cloneable {
    String roomNumber;
    int admissionYear;
    private double duesAmount;
    public Student(String name, String id, String contactNumber, String roomNumber,int admissionYear)
    {
        super(name, id, contactNumber);
        this.roomNumber=roomNumber;
        this.admissionYear=admissionYear;
    }
    @Override
    public void displayRole()
    {
        System.out.println("I am a Student.");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Admission Year: " + admissionYear);
    }
    @Override
    public double calculateDue()
    {
        return duesAmount;
    }
    @Override
        public void makePayment(double amount) throws InvalidPaymentException {
            if (amount <= 0) {
                throw  new InvalidPaymentException("Payment amount must be positive.");
            }
            if (amount > duesAmount) {
                throw new InvalidPaymentException("Payment amount cannot exceed dues owed.");
            }
            duesAmount -= amount;
            System.out.println("Payment of $" + amount + " successful. Remaining dues: $" + duesAmount);
        }
        @Override
        public Student clone() throws CloneNotSupportedException
        {
            return (Student)super.clone();
        }
    public String getRoomNumber() { return roomNumber; }
    public int getAdmissionYear() { return admissionYear; }

}
