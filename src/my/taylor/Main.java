package my.taylor;

import java.util.stream.DoubleStream;

public class Main {

    public static void main(String[] args) {
        
	    Taylor t1 = new Taylor(FuncName.EXP);
	    Taylor t2 = new Taylor(FuncName.SIN);
        Taylor t3 = new Taylor(FuncName.COS);

        try{
            t1.t.join();
            t2.t.join();
            t3.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
