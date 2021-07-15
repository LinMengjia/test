package com.example.demo.Thread;

public class TestVolatile {
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo demo = new ThreadDemo();
        Thread thread = new Thread(demo);
        thread.start();

        while (true){
//          System.out.println("打开这句之后，会进入if条件中，程序可正常结束");
//          或者打开下面这句
//          Thread.sleep(1);
            if(demo.isFlag()){
                System.out.println("************");
                break;
            }
        }
    }
}
