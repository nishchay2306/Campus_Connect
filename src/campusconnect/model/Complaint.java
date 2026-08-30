package campusconnect.model;

public class Complaint implements Cloneable{
    Student student;
    String description,status,dateRaised;
    public Complaint(Student student,String description,String status,String dateRaised)
    {
        this.student=student;
        this.description=description;
        this.status=status;
        this.dateRaised=dateRaised;
    }
    @Override
        public Complaint clone() throws CloneNotSupportedException
        {
            Complaint cloned = (Complaint) super.clone();
            cloned.student=this.student.clone();
            return cloned;
        }
    
        public void setStatus(String status) {
            if (status == null || status.trim().isEmpty()) {
                System.out.println("Error: Status cannot be null or empty");
                return;
            }
            // Optional validation: prevent changing status if already resolved
            if ("Resolved".equalsIgnoreCase(this.status) && !"Resolved".equalsIgnoreCase(status)) {
                System.out.println("Warning: Complaint is already resolved. Status change ignored.");
                return;
            }
            this.status = status;
        }
    }
