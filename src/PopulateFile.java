import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;

public class PopulateFile {

    String tempPassword;
    String fileName;
    
    public PopulateFile(){
        tempPassword = "";
        fileName = "dict.txt";
    }

    public PopulateFile(String fileName){
        tempPassword = "";
        this.fileName = fileName + ".txt";
    }

    public void populateFile(){

        FileWriter myWriter = null;
        Path filePath = Path.of("../files/" + fileName);

        try{
            Files.deleteIfExists(filePath);
            System.out.println("Existing file " + fileName + " deleted.");
        }

        catch (Exception e){

        }

        try{
            Files.createFile(filePath);
            System.out.println("File " + fileName + " created successfully");
        }

        catch (Exception e){
            System.out.println(e);
        }

        try{
            myWriter = new FileWriter(filePath.toFile(), true);
        }

        catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
 
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
                                            System.exit(0);
                                        }
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
        }

        System.out.println(count);
    }
}