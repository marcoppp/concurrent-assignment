interface BarrierI {

    public void sync();  // Wait for others to arrive (if barrier active)
 
    public void on();   // Activate barrier
 
    public void off();  // Deactivate barrier 
 
 }
 