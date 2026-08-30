package campusconnect.model;

public class Notice {
    String title,message,postedBy,date;
    public Notice(String title,String message,String postedBy,String date)
    {
        this.title=title;
        this.message=message;
        this.postedBy=postedBy;
        this.date=date;
    }
    public void displayNotice()
    {
            System.out.println("Notice Details:");
            System.out.println("Title: " + title);
            System.out.println("Message: " + message);
            System.out.println("Posted By: " + postedBy);
            System.out.println("Date: " + date);
        }
}
