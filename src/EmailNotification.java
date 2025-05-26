//Subclasses for Email and SMS
public class EmailNotification extends Notification<String> {
    public EmailNotification(String content) {
        super(content);
    }

    @Override
    void display() {
        System.out.println("Email: " + getContent());
    }
}

class SMSNotification extends Notification<String> {
    public SMSNotification(String content) {
        super(content);
    }

    @Override
    void display() {
        System.out.println("SMS: " + getContent());
    }
}