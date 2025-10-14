public class AttackAPI implements Runnable{
    
    private String password;

    public AttackAPI(String password){
        
        this.password = password;

    }
    
    public void run(){
        System.out.println(password);
    }

}
