package in.codengles.tictactoe;

import android.view.View;
import android.widget.Toast;

/**
 * Created by PaRV on 1/16/2017.
 */
public class TicToeButtonListener implements View.OnClickListener {
    private MainActivity mainActivity = null;

    TicToeButtonListener(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    public static int PLAYER_ONE = 1;
    public static int PLAYER_TWO =2;
    private String ZERO = "0";
    private String CROSS = "X";

    @Override
    public void onClick(View v) {
        TicToeButton ticToeButton = (TicToeButton)v;
        if(mainActivity.isGameOver()){
            return;
        }
        if(ticToeButton.getPlayer()!=0){
            Toast.makeText(mainActivity,"Invalid move!!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(mainActivity.isPlayerOneTurn()){
            ticToeButton.setPlayer(PLAYER_ONE);
            ticToeButton.setText(ZERO);
        }else {
            ticToeButton.setPlayer(PLAYER_TWO);
            ticToeButton.setText(CROSS);
        }
        mainActivity.checkGameStatus();
    }
}
