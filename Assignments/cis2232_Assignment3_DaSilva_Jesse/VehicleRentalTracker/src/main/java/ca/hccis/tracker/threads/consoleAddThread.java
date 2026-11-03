package ca.hccis.tracker.threads;
import ca.hccis.tracker.Controller;
public class consoleAddThread implements Runnable{

    @Override
    public void run(){
        Controller.addRental();
    }
}
