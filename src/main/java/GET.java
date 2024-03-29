import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 */
public class GET
{
    private String BASE_URL;

    /**
     *
     * @param host
     * @param port
     */
    public GET(String host, int port)
    {
        BASE_URL = "http://" + host + ":" + port + "/";
    }

    /**
     *
     * @param task
     * @param sessionId
     * @return
     */
    public String getTask(int task, int sessionId) {
        String request = "dkrest/gettask/" + task + "?sessionId=" + sessionId;
        return sendGet(request);
    }

    /**
     *
     * @param sessionId
     */
    public void recieveFeedback(int sessionId)
    {
        String request = "dkrest/results/" + sessionId;
        sendGet(request);
    }

    /**
     * Sends a http GET.
     *
     * @param path
     */
    private String sendGet(String path)
    {
        String responseBody = "";
        try
        {
            String url = BASE_URL + path;
            URL urlObj = new URL(url);
            System.out.println("Sending HTTP GET to " + url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("GET");

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
        catch (ProtocolException e)
        {
            System.out.println("Protocol is not supported by the server");
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return responseBody;
    }
}