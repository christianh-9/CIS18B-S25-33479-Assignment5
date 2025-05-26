public class NotificationBuilder<T> {
    private T content;
    //Optional variables
    private String sender = "MarketBridge";
    private String receiver = "User";
    private String type = "Buyer";

    //Setters
    public NotificationBuilder<T> setContent(T content) {
        this.content = content;
        return this;
    }

    public NotificationBuilder<T> setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public NotificationBuilder<T> setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public NotificationBuilder<T> setType(String type) {
        this.type = type;
        return this;
    }

    //Build methods for email and SMS
    public Notification<T> buildEmail() {
        if (!(content instanceof String)) {
            throw new IllegalArgumentException("Must be a String");
        }
        System.out.println("From: " + sender + ", To: " + receiver + ", Type: " + type);
        return (Notification<T>) new EmailNotification((String) content);
    }


    public Notification<T> buildSMS() {
        if (!(content instanceof String)) {
            throw new IllegalArgumentException("Must be a String");
        }
        System.out.println("From: " + sender + ", To: " + receiver + ", Type: " + type);
        return (Notification<T>) new SMSNotification((String) content);
    }

    //Demonstrate Type Erasure Awareness
    public void demonstrateTypeErasure() {
        //Something like this would not compile
        //if (content instanceOf T) {
        //    System.out.println("Type erasure");
        //}
        //But this would work, opposite to what is used in the method above
        if (content instanceof String) {
            System.out.println("This is a String");
        }
    }
}