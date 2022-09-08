package helloworldoo;

public class HelloWorldOO {

    private String message;
    public HelloWorldOO() {
        message = "Hello World";
    }

    public String returnMessage() {
        return message;
    }

    public static void main(String[] args) {
        HelloWorldOO myHello = new HelloWorldOO();
        System.out.println(myHello.returnMessage());
    }

}
