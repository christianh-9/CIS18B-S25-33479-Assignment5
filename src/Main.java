import java.util.ArrayList;
import java.util.List;

interface Observer<T> {
    void update(Notification<T> notification);
}

class EmailObserver implements Observer<String> {
    @Override
    public void update(Notification<String> notification) {
        System.out.println("Email sent: " + notification.getContent());
    }
}

class SMSObserver implements Observer<String> {
    @Override
    public void update(Notification<String> notification) {
        System.out.println("SMS sent: " + notification.getContent());
    }
}

interface NotificationFactory<T> {
    Notification<T> createNotification(T content);
}

public class Main {
    public static void main(String[] args) {

        //Abstract Factory
        NotificationFactory<String> emailFactory = new EmailNotificationFactory();
        Notification<String> email = emailFactory.createNotification("Welcome to MarketBridge!");

        NotificationFactory<String> smsFactory = new SMSNotificationFactory();
        Notification<String> sms = smsFactory.createNotification("You will now receive MarketBridge text messages!");

        //Builder
        NotificationBuilder<String> builder = new NotificationBuilder<String>()
                .setContent("You have been added to the list!");
        Notification<String> email2 = builder.buildEmail();
        email2.display();
        System.out.println();

        //Observer
        System.out.println("Now we create Email and SMS observers");
        Observer<String> emailObserver = new EmailObserver();
        Observer<String> smsObserver = new SMSObserver();

        //Bounded wildcard
        List<Observer<? super String>> observers = new ArrayList<>();
        observers.add(emailObserver);
        observers.add(smsObserver);

        //Separate lists
        List<Observer<String>> emailObservers = new ArrayList<>();
        List<Observer<String>> smsObservers = new ArrayList<>();

        emailObservers.add(emailObserver);
        smsObservers.add(smsObserver);

        //Iterate through emails
        System.out.println("Notify Email Observers:");
        for (Observer<String> observer : emailObservers) {
            observer.update(email);
            observer.update(email2);
            System.out.println();
        }

        //Iterate through SMS
        System.out.println("Notify SMS Observers:");
        for (Observer<String> observer : smsObservers) {
            observer.update(sms);
            System.out.println();
        }

        //Generics Restriction
        System.out.println("Something like Notification<T>[] does not work due to type erasure");

        Notification<?>[] notifications = new Notification<?>[2];
        notifications[0] = email;
        notifications[1] = sms;

        for (Notification<?> noti : notifications) {
            noti.display();
        }
    }
}