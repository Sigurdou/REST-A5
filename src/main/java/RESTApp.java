/**
 *
 */
public class RESTApp {

    //Host and port
    private static String host = "datakomm.work";
    private static int port = 80;

    //User email and phone number
    private static String email = "samuelh@stud.ntnu.no";
    private static String phone = "47224458";

    public static void main(String[] args) {
        POST post = new POST(host, port);
        GET get = new GET(host, port);
        JSONParse jsonParse = new JSONParse(host, port);

        //Task 1
        int sessionId = jsonParse.getSessionId(email, phone);
        get.getTask(1, sessionId);
        post.sendMsg(sessionId,"Hello");

        //Task 2
        String argument = jsonParse.getArg(2, sessionId);
        post.sendMsg(sessionId, argument);

        //Task 3
        int arraySum = jsonParse.getArraySum(3, sessionId);
        post.sendResult(sessionId, arraySum);

        //Task 4
        String md5 = jsonParse.getArg(4, sessionId);
        int md5Text = jsonParse.getTextFromMd5(md5);
        post.sendPinMsg(sessionId, md5Text);

        //Receive feedback
        get.recieveFeedback(sessionId);

    }
}
