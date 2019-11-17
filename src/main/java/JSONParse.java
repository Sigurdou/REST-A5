import org.json.JSONArray;
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

    public String getArgument(int task, int sessionId)
    {
        String response = get.getTask(task, sessionId);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("arguments");
        String argument = jsonArray.getString(0);
        return argument;
    }
}
