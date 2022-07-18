import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class clint {
    BufferedReader br;
    PrintWriter out;
    Socket socket;
    public clint() {
        try {
            socket = new Socket("localhost", 4444);
            System.out.println("connection established .. ");
            System.out.println("waiting for message ..");
            InputStreamReader fs = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(fs);
            out = new PrintWriter(socket.getOutputStream());
            startreading();
            startwriting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startreading(){
        Runnable r1 = ()-> {
            System.out.println("");
            while(true){
                try{
                    String msg  = br.readLine();
                    if(msg.equals("exit")){
                        System.out.println("server has stopped ...");
                        break ;
                    }

                    System.err.println("                                                                    : "+ msg);
                }catch(Exception e){
                    System.out.println("connnection closed ");

                }
            }
        };new Thread(r1).start();
    }
    public void startwriting(){
        Runnable r2 = () -> {
            System.out.println("");
            try {
                while(!socket.isClosed()){
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String  content = br1.readLine();
                    out.println(content);
                    out.flush();
                }
            } catch (Exception e) {
                System.out.println("connnection closed ");
            }
        };new Thread(r2).start();
    }
    public static void cls(){

        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();

        }catch (Exception E){
            System.out.println(E);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String id;
        String pass = "sudhanshu";
        String enterpass;
        System.out.print("log in  : ");
        id = sc.next();
        System.out.print("enter pass : ");
        enterpass = sc.next();
        if (enterpass.equals(pass)) {
            cls();
            System.out.println("this is "+id);
            new clint();
        }
    else{
        System.err.println("wrong password ");

    }}

}
