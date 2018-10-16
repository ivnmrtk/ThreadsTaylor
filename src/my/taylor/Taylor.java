package my.taylor;

import java.io.FileWriter;
import java.io.IOException;

public class Taylor implements Runnable{

    /**
     * Точность
     */
    final private double eps = 0.00000000000000001;

    /**
     * Значение переменной
     */
    final private double x;

    /**
     * Поток
     */
    Thread t;

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

    /**
     * Время выполнения
     */
    private long timeExec;

    Taylor(FuncName funcName){

        this.funcName = funcName;

        this.x = Math.random();

        t = new Thread(this, funcName.getFuncDesc());
        t.start();
    }

    private void func1(){  //e(x)
        double sum = 0;
        int i=0;
        long t1 = System.currentTimeMillis();
        while (Math.abs(precise - sum) > eps){
            sum += ( Math.pow(x,i) / (double) factorial(i) );
            i++;
        }
        long t2 = System.currentTimeMillis();
        result = sum;
        timeExec = t2 - t1;
    }

    private void func2(){  //sin(x)
        double sum = 0;
        int i=0;
        long t1 = System.currentTimeMillis();
        while (Math.abs(precise - sum) > eps){
            sum += ((Math.pow(-1, i) * Math.pow(x, 2*i)) / (double) factorial(2*i));
            i++;
        }
        long t2 = System.currentTimeMillis();
        result = sum;
        timeExec = t2 - t1;
    }

    private void  func3(){  //cos(x)
        double sum = 0;
        int i=0;
        long t1 = System.currentTimeMillis();
        while (Math.abs(precise - sum) > eps){
            sum += ((Math.pow(-1, i) * Math.pow(x, 2*i+1)) / (double) factorial(2*i+1));
            i++;
        }
        long t2 = System.currentTimeMillis();
        result = sum;
        timeExec = t2 - t1;
    }

    @Override
    public void run() {
        switch (funcName){

            case EXP: {
                precise = Math.exp(x);
                func1();
                break;
            }

            case SIN: {
                precise = Math.sin(x);
                func2();
                break;
            }

            case COS: {
                precise = Math.cos(x);
                func3();
                break;
            }
        }
        writeToFile();
    }

    private synchronized void writeToFile(){
        try(FileWriter writer = new FileWriter("output.txt", true)){
            writer.write("===================================================\n");
            writer.write("Название функции = " + funcName.getFuncDesc() +"\n");
            writer.write("Значение переменной = " + x + "\n");
            writer.write("Значение функции = " + result + "\n");
            writer.write("Точное значение = " + precise + "\n");
            writer.write("Время выполнения =" + timeExec + "\n");

            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long factorial(int num) {
        long fact = 1;
        for (; num > 0; fact *= num--);
        System.out.println(fact);
        return fact;
    }





}
