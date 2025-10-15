import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;

public class PopulateFile {

    String tempPassword;
    String fileName;
    
    public PopulateFile(){
        /* Default constructor. */
        tempPassword = "";
        fileName = "dict.txt";
    }

    public PopulateFile(String fileName){
        /* PopulateFile constructor. */
        tempPassword = "";
        this.fileName = fileName;
    }

    public void populateFile(){

        /* Method populating the dict file with passwords. */
        
        FileWriter myWriter = null;
        Path filePath = Path.of("../files/" + fileName);

        try{
            Files.deleteIfExists(filePath);
        }

        catch (Exception e){
            System.out.println("File " + fileName + " does not currently exist.");
        }   /* Deletes a file with a similar filename should it exist in directory in preparation to create a new one. */  

        try{
            Files.createFile(filePath);
            System.out.println("File " + fileName + " created successfully");
        }

        catch (Exception e){
            System.out.println(e);
            System.out.println("Exiting Application.");
            System.exit(0);
        }   /* Attempt to create the dict file. */

        try{
            myWriter = new FileWriter(filePath.toFile(), true);
        }

        catch (Exception e){
            System.out.println(e);
            System.out.println("Exiting Application.");
            System.exit(0);
        }   /* Attempt to create a writer object for the file. */
 
        int count = 0;

        for (String p: new String[] {"P", "p"}){
            for (String a: new String[] {"A", "a", "@"}){
                for (String s1: new String[] {"S", "s", "5"}){
                    for (String s2: new String[] {"S", "s", "5"}){
                        for (String w: new String[] {"W", "w"}){
                            for (String o: new String[] {"O", "o", "0"}){
                                for (String r: new String[] {"R", "r"}){
                                    for (String d: new String[] {"D", "d"}){
                                        tempPassword = p + a + s1 + s2 + w + o + r + d;
                                        try{
                                            myWriter.write(tempPassword+"\n");
                                        }
                                        catch(Exception e){
                                            System.out.println(e);
                                            System.out.println("Exiting Application.");
                                            System.exit(0);
                                        }   /* Attempt to write the respective password combination value on the file. */
                                        count += 1;
                                    }
                                }                          
                            }
                        }
                    }
                }
            }
        }

        try{
            myWriter.close();
        }
        
        catch (Exception e){
            System.out.println(e);
            System.out.println("Exiting Application.");
            System.exit(0);
        }   /* Attempt to close file. */

        System.out.println(count + " combinations created.\n");

    }
}