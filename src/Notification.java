//Abstract class for Email and SMS
public abstract class Notification<T> {
    private T content;

    public Notification(T content) {
        this.content = content;
    }
    public T getContent() {
        return content;
    }

    abstract void display();
}