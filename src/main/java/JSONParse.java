import org.json.JSONObject;

public class JSONParse {
    private POST post;
    private GET get;

    public JSONParse(String host, int port) {
        post = new POST(host, port);
        get = new GET(host, port);
    }

    public int getSessionId(String email, String phone){
        String response = post.postAuthorisation(email, phone);
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getInt("sessionId");
    }
}
