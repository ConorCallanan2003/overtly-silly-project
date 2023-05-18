import java.io.Serializable;

public class Message implements Serializable {
    
    private int origin;
    
    private String message;
    private String senderName;
    
    public Message(String message, String senderName) {
        this.message = message;
        this.senderName = senderName;
    }
    
    public String getSenderName() {
        return senderName;
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
