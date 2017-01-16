package in.codengles.tictactoe;

import android.content.Context;
import android.widget.Button;

/**
 * Created by PaRV on 1/16/2017.
 */
public class TicToeButton extends Button{

    private int player;

    public int getPlayer(){
        return player;
    }
    public void setPlayer(int player){
        this.player = player;
    }

    public TicToeButton(Context context) {
        super(context);
    }
}
