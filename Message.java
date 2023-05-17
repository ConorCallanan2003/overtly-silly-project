import java.io.Serializable;

public class Message implements Serializable {
    
    private int origin;
    
    private String message;
    
    public Message(String message) {
        this.message = message;
    }
    
    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getOrigin() {
        return origin;
    }

    public String getMessage() {
        return message;
    }

}
