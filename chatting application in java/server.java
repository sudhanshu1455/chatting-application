// warning : first read the given txt file ...
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

// code by sudhanshu pandey .
public class server {

    // constructor ....
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public server() {
        try {
            server = new ServerSocket(4444);
            System.out.println("waiting ..");
            socket = server.accept();
            InputStreamReader re = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(re);
            out = new PrintWriter(socket.getOutputStream());

            startreading();
            startwrinting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startreading(){
        Runnable r1 = ()-> {
            while(true){
                try{
                    String msg  = br.readLine();
                    if(msg.equals("exit")){
                        System.out.println(" clint has stopped ......");
                        break ;
                    }
                    System.err.println("                                                                : "+ msg);
                }catch(Exception e){
                    System.out.println("connnection closed ");
                }
            }
        };new Thread(r1).start();
    }
    public void startwrinting(){
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
    public static void cls() {

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        } catch (Exception E) {
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
            System.out.println("log in successful ..");

            System.out.println("this is "+id + " going to start chatting ..");

            new server();
        }else {
            System.err.println("wrong password");
        }
    }
}
