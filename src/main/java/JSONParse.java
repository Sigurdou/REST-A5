import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        for(int i = 0; i<jsonArray.length(); i++) {
            sum *= jsonArray.getInt(i);
        }
        return sum;
    }

    /**
     *
     * @param md5
     * @return
     */
    public Integer getTextFromMd5(String md5) {
        Integer password = 0;
        try {
            String md5Hash = md5;
            String passwordMd5 = "";

            while (md5Hash != passwordMd5) {
                if(password < 9999) {
                    password += 1;
                    passwordMd5 += password.toString();
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(passwordMd5.getBytes());

                    byte byteData[] = md.digest();

                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < byteData.length; i++) {
                        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    if (md5Hash.equals(sb.toString())) {
                        break;
                    }
                    else {
                        passwordMd5 = "";
                    }
                }
            }
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("Something went wrong:" + e.getMessage());
        }
        return password;
    }
}
