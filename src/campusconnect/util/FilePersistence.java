package campusconnect.util;
import campusconnect.generics.CustomArrayList;
import campusconnect.model.Student;

import java.io.*;
public class FilePersistence {
    public static void saveStudents(CustomArrayList<Student> students, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                String line = student.getId() + "," + student.getName() + "," + student.getContactNumber() + "," +  student.getRoomNumber() + "," + student.getAdmissionYear();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
    public static CustomArrayList<Student> loadStudents(String filename)
    {
        CustomArrayList<Student> students=new CustomArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int admission = Integer.parseInt(parts[4]);
                Student s = new Student(parts[0], parts[1], parts[2], parts[3], admission);
                students.add(s);
            }
        }catch(IOException e){
            System.err.println("Error message loading: " +e.getMessage());
        }
        return students;
    }
}
