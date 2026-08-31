package campusconnect.interfaces;

public interface Notifiable {
    default void sendNotification(String message)
    {
        System.out.println("[Notification] " + message);
    }
}
