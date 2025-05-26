public class EmailNotificationFactory implements NotificationFactory<String> {
    @Override
    public Notification<String> createNotification(String content) {
        return new EmailNotification(content);
    }
}

class SMSNotificationFactory implements NotificationFactory<String> {
    @Override
    public Notification<String> createNotification(String content) {
        return new SMSNotification(content);
    }
}