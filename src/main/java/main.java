public class main {
    public static void main(String[] args) {
        POST post = new POST("datakomm.work", 80);
        post.postAuthorisation("samuelh@stud.ntnu.no", 47224458);
    }
}
