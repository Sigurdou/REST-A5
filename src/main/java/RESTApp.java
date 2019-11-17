
public class RESTApp {

    //Host and port
    private static String host = "datakomm.work";
    private static int port = 80;

    //User email and phone number
    private static String email = "samuelh@stud.ntnu.no";
    private static String phone = "47224458";

    public static void main(String[] args) {
        //Authorize
        POST post = new POST(host, port);
        post.postAuthorisation(email,phone);
    }
}
