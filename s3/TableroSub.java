package s3;

import java.util.ArrayList;

public class TableroSub {
    private Submarino[][] tableroSub;
    ArrayList<Submarino> submarinos;
    public TableroSub() {
        tableroSub = new Submarino[8][8];
        submarinos = new ArrayList<Submarino>();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                tableroSub[i][j] = null;
            }
        }
    }
}
