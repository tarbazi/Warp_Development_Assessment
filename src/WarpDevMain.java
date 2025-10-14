import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class WarpDevMain{
    
    public static void main(String[] args){

        String methodCall;
        PopulateFile populateFileObj;
        Path filePath;

        try{
            methodCall = args[0];
        }

        catch (Exception e){
            methodCall = "RunDefault";
        }

        try{
            populateFileObj = new PopulateFile(args[1] + ".txt");
            filePath = Path.of("../files/" + args[1] + ".txt");
        }

        catch (Exception e){
            populateFileObj = new PopulateFile();
            filePath = Path.of("../files/dict.txt");
        }

        if (methodCall.equals("generate_file")){

            populateFileObj.populateFile();

        }

        else if(methodCall.equals("run_attack")){
            
            List<String> myPasswords = readFile(filePath);

            for (String password: myPasswords){
                
                Thread attackThread = new Thread(new AttackAPI(password));
                attackThread.start();
                
            }

        }

        else{

            populateFileObj.populateFile();

            List<String> myPasswords = readFile(filePath);


            for (String password: myPasswords){
                
                Thread attackThread = new Thread(new AttackAPI(password));
                attackThread.start();
                
            }

        }
    }

    public static List<String> readFile(Path filePath){

        List<String> myPasswords;

        try{
            
            myPasswords = Files.readAllLines(filePath);
            System.out.println("Passwords read from " + filePath + " file.");

        }

        catch (Exception e){
            myPasswords = null;
            System.out.println(e);
            System.exit(0);
        }

        return myPasswords;

    }

}