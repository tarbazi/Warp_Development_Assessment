import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class WarpDevMain{
    
    public static void main(String[] args){

        /* Main method for the application. */
        
        String methodCall;
        PopulateFile populateFileObj;
        Path filePath;

        try{
            methodCall = args[0];
        }

        catch (Exception e){
            methodCall = "RunDefault";
        }   /* Attempt to retrieve optional method run parameter. */

        try{
            populateFileObj = new PopulateFile(args[1] + ".txt");
            filePath = Path.of("../files/" + args[1] + ".txt");
        }

        catch (Exception e){
            populateFileObj = new PopulateFile();
            filePath = Path.of("../files/dict.txt");
        }   /* Attempt to retrieve optional file name run parameter. */

        if (methodCall.equals("generate_file")){
            populateFileObj.populateFile();
        }   /* Calls the method to populate dict file with password combinations only. */

        else if(methodCall.equals("run_attack")){
            
            List<String> myPasswords = readFile(filePath);

            for (String password: myPasswords){   
                Thread attackThread = new Thread(new AttackAPI(password));
                attackThread.run();   // .start(); runs attacks in parallel which is more time effecient but sometimes the API returns error 429 "Too Many Requests" error due to API overload.     
            }   /* Calls the method to launch attack on the API using the dict file passwords only. */

        }

        else{

            populateFileObj.populateFile();

            List<String> myPasswords = readFile(filePath);

            for (String password: myPasswords){   
                Thread attackThread = new Thread(new AttackAPI(password));
                attackThread.run();   // .start(); runs attacks in parallel which is more time effecient but sometimes the API returns error 429 "Too Many Requests" error due to API overload.
            }   /* Else clause executes both file population and API attack methods as default. */

        }
    }

    public static List<String> readFile(Path filePath){

        /* Method to help the attack functionality with reading from the dict file. */
        
        List<String> myPasswords;
        
        try{ 
            myPasswords = Files.readAllLines(filePath);
            System.out.println("Passwords read from " + filePath + " file.");
        }

        catch (Exception e){
            myPasswords = null;
            System.out.println(e);
            System.out.println("Exiting Application.");
            System.exit(0);
        }

        return myPasswords;

    }

}