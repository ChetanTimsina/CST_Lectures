package Packager.packagers;
public class playerMovement {
    int x,y,turn;
    boolean right;
    public playerMovement(boolean right,int diceRoll,int x,int y,int turn){
        for (int i = 0; i < diceRoll; i++) {
            if (right) {
                if (x + 60 > 700) { 
                    if(y-60 < 20){
                    }else{
                    y = y - 60;
                    }
                    right = false;  
                } else {
                    x = x + 60;  
                }
            } else {
                if (x - 60 < 100) {  
                    if(y-60<20){
                    }else{
                    y = y - 60;
                    }
                    right = true;
                } else {
                    x = x - 60;
                }
            }
        }
        CheckLocation ck;
        if(turn == 1){
            ck = new CheckLocation(x,y,right,1);
            x=ck.getx();
            y=ck.gety();
            right = ck.getright();
        }
        else if(turn == 2){
            ck = new CheckLocation(x,y,right,2);
            x=ck.getx();
            y=ck.gety();
            right = ck.getright();
        }
        else if(turn == 3){
            ck = new CheckLocation(x,y,right,3);
            x=ck.getx();
            y=ck.gety();
            right = ck.getright();
        }
        else if(turn == 4){
            ck = new CheckLocation(x,y,right,4);
            x=ck.getx();
            y=ck.gety();
            right = ck.getright();
        }
        this.x=x;
        this.y=y;
        this.right=right;
    }
    public int getcurrentx(){
        return x;
    }
    public int getcurrenty(){
        return y;
    }
    public boolean getright(){
        return this.right;
    }
}
