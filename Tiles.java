class Tiles{

    private Semaphore[][] arrayOfTiles = new Semaphore[12][13];

    public Tiles() {

        for (int i = 0; i < 12; i++){//row
            for (int j = 0; j < 13; j++){//column
                arrayOfTiles[i][j] = new Semaphore(1);
            }
        }
    }

    public void get(Pos position) {
        try {
            arrayOfTiles[position.row][position.col].P();
        } catch (InterruptedException e) {
            //TODO: handle exception
        }
    }

    public void letGo(Pos position) {
        arrayOfTiles[position.row][position.col].V();
    }
 }