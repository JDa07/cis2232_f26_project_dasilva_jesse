package ca.hccis.tracker.threads;

import ca.hccis.tracker.Controller;

public class consoleViewThread extends Thread{

    @Override
    public void run() {
        Controller.viewRentals();
    }
}
