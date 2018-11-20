class Alley implements AlleyI{

    private Semaphore CCW, CW, enterAlley;
    private int carsEntered = 0;

    public Alley() {
        CW = new Semaphore(1);
        CCW = new Semaphore(1);
        enterAlley = new Semaphore(1);

    }

    public void enter(int no) {
        System.out.println("Car no. "+no+" tried to enter alley");
        try {
            if (carsEntered == 0) {
                enterAlley.P();
                System.out.println("Car number " + no + " has taken enterAlley");
                if (no < 5) {
                    whichfirst(CW, CCW);
                }else {
                    whichfirst(CCW, CW);
                }
                enterAlley.V();
            } else {
                if (no < 5) {
                    CW.P();
                    carsEntered++;
                    System.out.println("Car number " + no + " has taken CW of "+CW);
                }else {
                    CCW.P();
                    carsEntered++;
                    System.out.println("Car number " + no + " has taken CCW of "+CCW);
                }
            }
        } catch (InterruptedException e) {
            //TODO: handle exception
        }
    }

    private void whichfirst(Semaphore first, Semaphore second) {
        try {
            first.P();
            System.out.println("First car has taken CW or CCW ");
            carsEntered++;
            System.out.println("now is "+first+" turn");
            second.P();
/*             first.V();
            System.out.println("First car has released CW");         */    
        } catch (InterruptedException e) {
            //TODO: handle exception
        }
    }
 
    public void leave(int no) {
        System.out.println("Car no. "+no+" leaved alleys");
        System.out.println("No. of cars entered: "+carsEntered);
        if (carsEntered == 4) {
            if (no < 5) {
                secondcomes(CCW, CW); //CW is still not released
            } else {
                secondcomes(CW, CCW); //CCW is still not released
            }
        } else{
            if (no < 5) {
                CW.V();
                System.out.println("Car number " + no + " has released CW of "+CW);
            } else {
                CCW.V();
                System.out.println("Car number " + no + " has released CCW of CCW"+CCW);
            }
        }
    }

    private void secondcomes(Semaphore second, Semaphore first) {
        System.out.println("now change, is "+second+" turn");
        carsEntered = 0;
        second.V();
        
    } 
 }