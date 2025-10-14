public class WarpDevMain{

    public static void generate_file(){
        PopulateFile populateFileObj = new PopulateFile();
        populateFileObj.populateFile();    
    }

    public void run_attack(){
        
    }
    
    public static void main(String[] args){
        if (args[0].equals("generate_file")){
            generate_file();
        }

        else if(args[0].equals("run_attack")){

        }

        else{

        }
    }

}