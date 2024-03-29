import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 */
public class POST
{
    private String BASE_URL;

    public POST(String host, int port) {
        BASE_URL = "http://" + host + ":" + port + "/";
    }

    /**
     *
     * @param email
     * @param phone
     * @return
     */
    public String postAuthorisation(String email, String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("phone", phone);
        String response = sendPost("dkrest/auth",jsonObject);
        return response;
    }

    /**
     *
     * @param sessionId
     * @param msg
     */
    public void sendMsg(int sessionId, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", sessionId);
        jsonObject.put("msg", msg);
        sendPost("dkrest/solve", jsonObject);
    }

    /**
     *
     * @param sessionId
     * @param pin
     */
    public void sendPinMsg(int sessionId, int pin)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", sessionId);
        jsonObject.put("pin", pin);
        sendPost("dkrest/solve", jsonObject);
    }
    /**
     *
     * @param sessionId
     * @param result
     */
    public void sendResult(int sessionId, int result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", sessionId);
        jsonObject.put("result", result);
        sendPost("dkrest/solve", jsonObject);
    }

    /**
     * Sends a http POST.
     *
     * @param path
     * @param jsonData
     */
    public String sendPost(String path, JSONObject jsonData) {
        String responseBody = "";
        try
        {
            String url = BASE_URL + path;
            URL urlObj = new URL(url);
            System.out.println("Sending HTTP POST to " + url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(jsonData.toString().getBytes());
            os.flush();

            int responseCode = con.getResponseCode();
            if (responseCode == 200)
            {
                System.out.println("Server reached");

                // Response was OK, read the body (data)
                InputStream stream = con.getInputStream();
                InputStreamHandle streamHandler = new InputStreamHandle();
                responseBody = streamHandler.convertStreamToString(stream);
                stream.close();
                System.out.println("Response from the server:");
                System.out.println(responseBody);
            }
            else
            {
                String responseDescription = con.getResponseMessage();
                System.out.println("Request failed, response code: " + responseCode + " (" + responseDescription + ")");
            }
        }
        catch (ProtocolException e) {
            System.out.println("Protocol is not supported by the server");
        }
        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return responseBody;
    }
}
