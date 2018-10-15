package my.taylor;

import java.io.FileWriter;
import java.io.IOException;

public class Taylor implements Runnable{

    /**
     * Точность
     */
    final private double eps = 0.01;

    /**
     * Значение переменной
     */
    final private double x;

    /**
     * Поток
     */
    private Thread t;

    /**
     * Выбранная функция
     */
    private final FuncName funcName;

    /**
     * Точное значение
     */
    private double precise;

    /**
     * Результат выполнения
     */
    private double result;

    private int timeExec;

    Taylor(FuncName funcName){

        this.funcName = funcName;

        this.x = Math.random();

        t = new Thread(this, funcName.getFuncDesc());
        t.start();
    }

    private void func1(){  //e(x)
        double sum = 0;
        int i=0;
        while (Math.abs(precise - sum) < eps){
            sum += ( Math.pow(x,i) / (double) factorial(i) );
            i++;
        }
        result = sum;
    }

    private void func2(){  //sin(x)
        double sum = 0;
        int i=0;
        while (Math.abs(precise - sum) < eps){
            sum += (Math.pow(-1, i) * Math.pow(x, 2*i)) / (double) factorial(2*i);
            i++;
        }
        result = sum;
    }

    private void  func3(){  //cos(x)
        double sum = 0;
        int i=0;
        while (Math.abs(precise - sum) < eps){
            sum += (Math.pow(-1, i) * Math.pow(x, 2*i+1)) / (double) factorial(2*i+1);
        }
        result = sum;
    }

    @Override
    public void run() {
        switch (funcName){

            case EXP: {
                precise = Math.exp(x);
                func1();
            }

            case SIN: {
                precise = Math.sin(x);
                func2();
            }

            case COS: {
                precise = Math.cos(x);
                func3();}
        }


    }

    private synchronized void writeToFile(){
        try(FileWriter writer = new FileWriter("output.txt", false)){
            writer.write("Название функции" + funcName.getFuncDesc() +"\n");
            writer.write("Значение переменной" + x + "\n");
            writer.write("Значение функции" + result + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long factorial(int count){

        long fact=1L;
        for(int i=0; i<count; i++){
            fact *= i;
        }
        return fact;
    }




}
