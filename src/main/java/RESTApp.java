public class RestApp {
    public RestApp(){

        /**AUTHORISATION*/
        POST post = new POST("datakomm.work", 80);
        post.postAuthorisation("samuelh@stud.ntnu.no", 47224458);

        /**GET TASK*/
        GET get = new GET("datakomm.work", 80);
        get.getTask();

        /**SEND ANSWER*/

    }

}
