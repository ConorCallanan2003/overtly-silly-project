import org.junit.Test;

public class MessageTest {
    @Test
    public void testGetMessage() {
        Message message = new Message("message", "person1");
        assert message.getMessage() == "message";
    }

    @Test
    public void testGetOrigin() {
        Message message = new Message("message", "person1");
        message.setOrigin(1234);
        assert message.getOrigin() == 1234;
    }

    @Test
    public void testGetSenderName() {
        Message message = new Message("message", "person1");
        assert message.getSenderName() == "person1";
    }

    @Test
    public void testSetOrigin() {
        Message message = new Message("message", "person1");
        message.setOrigin(4321);
        assert message.getOrigin() == 4321;
    }
}
