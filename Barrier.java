class Barrier implements BarrierI{

    private Semaphore[] bs = new Semaphore[9];
    private boolean isOpen = false;
    private int counter, nowThreshold, temp;

    public Barrier() {
        nowThreshold = 8;
        temp = nowThreshold;
        counter = 0;
        for (int i = 0; i < 9; i++){
            bs[i] = new Semaphore(0);
        }
    }

    public void sync() {
        //System.out.println(isOpen);
        if (isOpen){
            try {
                counter++;
                //System.out.println(counter+" cars already bar sync");
                if (counter >= nowThreshold) {
                    reset();
                }else {
                    bs[counter-1].P();    
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }

    public void on() {
        isOpen = true;
        updateNextRound();
    }

    public void reset() {
        updateNextRound();
        for (int i = 0; i < 9; i++){
            bs[i].V();
        }
        for (int i = 0; i < 9; i++){
            bs[i] = new Semaphore(0);
        }
        counter = 0;
        //tempSema.V();
    }
 
    public void off() {
        updateNextRound();
        isOpen = false;
        for (int i = 0; i < 9; i++){
            bs[i].V();
        }
        for (int i = 0; i < 9; i++){
            bs[i] = new Semaphore(0);
        }
        counter = 0;
        //tempSema.V();
    }

    public boolean set(int k) {
        if (k <= nowThreshold) {
            //System.out.println(nowThreshold+" changed to "+k);
            nowThreshold = k;
            temp = k;
            if (counter >= nowThreshold) {
                reset();
            }
            return false;
        }else { //increase threshold
            //System.out.println(nowThreshold+" changed to "+k);
            temp = k;
            return true;
        }
    }
    
    public void updateNextRound() {
        nowThreshold = temp;
    }


}