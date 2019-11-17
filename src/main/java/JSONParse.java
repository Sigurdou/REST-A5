import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 */
public class JSONParse {
    private POST post;
    private GET get;

    /**
     *
     * @param host
     * @param port
     */
    public JSONParse(String host, int port) {
        post = new POST(host, port);
        get = new GET(host, port);
    }

    /**
     *
     * @param email
     * @param phone
     * @return
     */
    public int getSessionId(String email, String phone){
        String response = post.postAuthorisation(email, phone);
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getInt("sessionId");
    }

    /**
     *
     * @param task
     * @param sessionId
     * @return
     */
    public String getArg(int task, int sessionId) {
        String response = get.getTask(task, sessionId);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("arguments");
        String argument = jsonArray.getString(0);
        return argument;
    }

    /**
     *
     * @param task
     * @param sessionId
     * @return
     */
    public int getArraySum(int task, int sessionId) {
        String response = get.getTask(task, sessionId);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("arguments");
        int sum = 1;
        for(int i = 0; i<jsonArray.length(); i++)
        {
            sum *= jsonArray.getInt(i);
        }
        return sum;
    }
}
