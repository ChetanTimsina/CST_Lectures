package Packager.packagers;
public class CheckLocation {
    int x;
    int y;
    int played;
    boolean right;
    CheckLocation(int x,int y,boolean right,int played){
    if(played == 1){
        if(x == 191 && y == 51){
            this.x=131;
            this.y=351;
            this.right=true;
        }
        else if(x == 611 && y == 111){
            this.x=551;
            this.y=291;
            this.right=false;
        }
        else if(x == 371 && y == 171){
            this.x=251;
            this.y=291;
            this.right=false;
        }
        else if(x == 431 && y == 231){
            this.x=371;
            this.y=351;
            this.right=true;
        }
        else if(x == 491 && y == 291){
            this.x=671;
            this.y=411;
            this.right=false;
        }
        else if(x == 251 && y == 351){
            this.x=251;
            this.y=531;
            this.right=false;
        }
        else if(x == 131 && y == 411){
            this.x=251;
            this.y=591;
            this.right=true;
        }
        else if(x == 491 && y == 471){
            this.x=371;
            this.y=591;
            this.right=true;
        }
        ///////////////////////////////////////
        else if(x == 491 && y == 171){
            this.x=611;
            this.y=51;
            this.right=false;
        }
        else if(x == 191 && y == 231){
            this.x=131;
            this.y=111;
            this.right=true;
        }
        else if(x == 191 && y == 351){
            this.x=251;
            this.y=231;
            this.right=true;
        }
        else if(x == 671 && y == 351){
            this.x=611;
            this.y=231;
            this.right=true;
        }
        else if(x == 551 && y == 411){
            this.x=611;
            this.y=351;
            this.right=true;
        }
        else if(x == 551 && y == 531){
            this.x=431;
            this.y=351;
            this.right=true;
        }
        else if(x == 311 && y == 591){
            this.x=371;
            this.y=471;
            this.right=true;
        }
        ////////////////////////////////////////////
        else {
            this.x=x;
            this.y=y;
            this.right=right;
        }
    }else if(played == 2){
        if(x == 161 && y == 51){
            this.x=101;
            this.y=351;
            this.right=true;
            
        }
        else if(x == 581 && y == 111){
            this.x=521;
            this.y=291;
            this.right=false;
        }
        else if(x == 341 && y == 171){
            this.x=221;
            this.y=291;
            this.right=false;
        }
        else if(x == 401 && y == 231){
            this.x=341;
            this.y=351;
            this.right=true;
        }
        else if(x == 461 && y == 291){
            this.x=641;
            this.y=411;
            this.right=false;
        }
        else if(x == 221 && y == 351){
            this.x=221;
            this.y=531;
            this.right=false;
        }
        else if(x == 101 && y == 411){
            this.x=221;
            this.y=591;
            this.right=true;
        }
        else if(x == 461 && y == 471){
            this.x=341;
            this.y=591;
            this.right=true;
        }
        ////////////////////////////////////////////Done
        else if(x == 461 && y == 171){
            this.x=581;
            this.y=51;
            this.right=false;
        }
        else if(x == 161 && y == 231){
            this.x=101;
            this.y=111;
            this.right=true;
        }
        else if(x == 161 && y == 351){
            this.x=221;
            this.y=231;
            this.right=true;
        }
        else if(x == 641 && y == 351){
            this.x=581;
            this.y=231;
            this.right=true;
        }
        else if(x == 521 && y == 411){
            this.x=581;
            this.y=351;
            this.right=true;
        }
        else if(x == 521 && y == 531){
            this.x=401;
            this.y=351;
            this.right=true;
        }
        else if(x == 281 && y == 591){
            this.x=341;
            this.y=471;
            this.right=true;
        }
        /////////////////////////////////////
        else {
            this.x=x;
            this.y=y;
            this.right=right;
        }
    }
    else if(played == 3){
        if(x == 191 && y == 21){
            this.x=131;
            this.y=321;
            this.right=true;
        }
        else if(x == 611 && y == 81){
            this.x=551;
            this.y=261;
            this.right=false;
        }
        else if(x == 371 && y == 141){
            this.x=251;
            this.y=261;
            this.right=false;
        }
        else if(x == 431 && y == 201){
            this.x=371;
            this.y=321;
            this.right=true;
        }
        else if(x == 491 && y == 261){
            this.x=671;
            this.y=381;
            this.right=false;
        }
        else if(x == 251 && y == 321){
            this.x=251;
            this.y=501;
            this.right=false;
        }
        else if(x == 131 && y == 381){
            this.x=251;
            this.y=561;
            this.right=true;
        }
        else if(x == 491 && y == 441){
            this.x=371;
            this.y=561;
            this.right=true;
        }
        ////////////////////////////////////// Done
        else if(x == 491 && y == 141){
            this.x=611;
            this.y=21;
            this.right=false;
        }
        else if(x == 191 && y == 201){
            this.x=131;
            this.y=81;
            this.right=true;
        }
        else if(x == 191 && y == 321){
            this.x=251;
            this.y=201;
            this.right=true;
        }
        else if(x == 671 && y == 321){
            this.x=611;
            this.y=201;
            this.right=true;
        }
        else if(x == 551 && y == 381){
            this.x=611;
            this.y=321;
            this.right=true;
        }
        else if(x == 551 && y == 501){
            this.x=431;
            this.y=321;
            this.right=true;
        }
        else if(x == 311 && y == 561){
            this.x=371;
            this.y=441;
            this.right=true;
        }
        //////////////////////////////////////////////////
        else {
            this.x=x;
            this.y=y;
            this.right=right;
        }
    }
    else if(played == 4){
        if(x == 161 && y == 21){
            this.x=101;
            this.y=321;
            this.right=true;
        }
        else if(x == 581 && y == 81){
            this.x=521;
            this.y=261;
            this.right=false;
        }
        else if(x == 341 && y == 141){
            this.x=221;
            this.y=261;
            this.right=false;
        }
        else if(x == 401 && y == 201){
            this.x=341;
            this.y=321;
            this.right=true;
        }
        else if(x == 461 && y == 261){
            this.x=641;
            this.y=381;
            this.right=false;
        }
        else if(x == 221 && y == 321){
            this.x=221;
            this.y=501;
            this.right=false;
        }
        else if(x == 101 && y == 381){
            this.x=221;
            this.y=561;
            this.right=true;
        }
        else if(x == 461 && y == 441){
            this.x=341;
            this.y=561;
            this.right=true;
        }
        //////////////////////////////////////////////////////// Done
        else if(x == 461 && y == 141){
            this.x=581;
            this.y=21;
            this.right=false;
        }
        else if(x == 161 && y == 201){
            this.x=101;
            this.y=81;
            this.right=true;
        }
        else if(x == 161 && y == 321){
            this.x=221;
            this.y=201;
            this.right=true;
        }
        else if(x == 641 && y == 321){
            this.x=581;
            this.y=201;
            this.right=true;
        }
        else if(x == 521 && y == 381){
            this.x=581;
            this.y=321;
            this.right=true;
        }
        else if(x == 521 && y == 501){
            this.x=401;
            this.y=321;
            this.right=true;
        }
        else if(x == 281 && y == 561){
            this.x=341;
            this.y=441;
            this.right=true;
        }
        ///////////////////////////////////////////////////
        else {
            this.x=x;
            this.y=y;
            this.right=right;
        }
    }
        
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public boolean getright(){
        return right;
    }
}
