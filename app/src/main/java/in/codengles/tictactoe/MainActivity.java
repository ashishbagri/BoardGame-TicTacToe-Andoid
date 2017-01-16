package in.codengles.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int gridSize = 3;
    private TicToeButton[][] buttonGrid;
    private LinearLayout[] rows;
    private LinearLayout linearLayout = null;
    private boolean playerOneTurn;
    private boolean gameOver;
    private int textSize = 60;
    private int PLAYER_ONW_WINS = 1;
    private  int PLAYER_TWO_WINS = 2;
    private int DRAW = 3;
    private int INCOMPLETE = 4;

    private TicToeButtonListener ticToeButtonListener = null;

    MainActivity(){
        ticToeButtonListener = new TicToeButtonListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_board);

        linearLayout = (LinearLayout)findViewById(R.id.tictactoe_main);

        drawBoard();
    }
    private void intializeGameData(){
        setUpGame();
        setUpBoard();
    }

    private void setUpBoard(){
        linearLayout.removeAllViews();
        buttonGrid = new TicToeButton[gridSize][gridSize];
        rows = new LinearLayout[gridSize];
    }

    private void setUpGame(){
        playerOneTurn = true;
        gameOver = false;
    }

    private void drawBoard() {
        intializeGameData();
        addRows();
        addButtons();

    }
    private void addRows(){

        for (int i = 0; i<gridSize;i++){
            rows[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            params.setMargins(5,5,5,5);
            rows[i].setLayoutParams(params);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.addView(rows[i]);
        }
    }
    private void addButtons(){
        for(int i=0;i<gridSize;i++){
            for(int j=0;j<gridSize;j++){
                buttonGrid[i][j] = new TicToeButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                params.setMargins(5,5,5,5);
                buttonGrid[i][j].setLayoutParams(params);
                buttonGrid[i][j].setOnClickListener(ticToeButtonListener);
                buttonGrid[i][j].setTextSize(textSize);
                rows[i].addView(buttonGrid[i][j]);
            }
        }
    }

    public void checkGameStatus(){
        int status = getGameStatus();
        playerOneTurn = !playerOneTurn;

        if(status==PLAYER_ONW_WINS || status == PLAYER_TWO_WINS){
            Toast.makeText(this, "Player "+status+" wins ", Toast.LENGTH_SHORT).show();
            gameOver = true;

        }
        if(status == DRAW){
            Toast.makeText(this, " Draw", Toast.LENGTH_SHORT).show();
            gameOver = true;
            return;
        }
    }

    public int getGameStatus(){
        /*Rows*/
        for(int i =0;i<gridSize;i++) {
            boolean flag = true;
            for (int j = 1; j < gridSize; j++) {
                if (buttonGrid[i][0].getPlayer() == 0 || buttonGrid[i][0].getPlayer() != buttonGrid[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                if(buttonGrid[i][0].getPlayer()==TicToeButtonListener.PLAYER_ONE){
                    return PLAYER_ONW_WINS;
                }else{
                    return PLAYER_TWO_WINS;
                }
            }
        }
        /*Columns*/
        for(int j = 0;j<gridSize;j++){
            boolean flag = true;
            for(int i=1;i<gridSize;i++){
                if(buttonGrid[0][j].getPlayer()== 0 || buttonGrid[0][j].getPlayer()!=buttonGrid[i][j].getPlayer()){
                    flag = false;
                    break;
                }
            }
            if(flag){
                if(buttonGrid[0][j].getPlayer()==TicToeButtonListener.PLAYER_ONE){
                    return PLAYER_ONW_WINS;
                }else{
                    return PLAYER_TWO_WINS;
                }
            }
        }

        boolean flag = true;
        for(int i = 1; i < gridSize; i++){
            if(buttonGrid[0][0].getPlayer() == 0 || buttonGrid[0][0].getPlayer() != buttonGrid[i][i].getPlayer()){
                flag = false;
                break;
            }
        }
        if(flag){
            if(buttonGrid[0][0].getPlayer() == TicToeButtonListener.PLAYER_ONE){
                return PLAYER_ONW_WINS;
            }else{
                return  PLAYER_TWO_WINS;
            }
        }

        flag = true;
        for(int i = gridSize-1; i >= 0; i--){
            int col = gridSize - 1 - i;
            if(buttonGrid[i][col].getPlayer() == 0 || buttonGrid[gridSize-1][0].getPlayer() != buttonGrid[i][col].getPlayer()){
                flag = false;
                break;
            }
        }
        if(flag){
            if(buttonGrid[gridSize-1][0].getPlayer() == TicToeButtonListener.PLAYER_ONE){
                return PLAYER_ONW_WINS;
            }else{
                return  PLAYER_TWO_WINS;
            }
        }


        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(buttonGrid[i][j].getPlayer() == 0){
                    return  INCOMPLETE;
                }
            }
        }

        return DRAW;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.getMenuInflater().inflate(R.menu.main_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.newGame){
            resetBoard();
        }
        if(id==R.id.boardSize3){
            gridSize = 3;
            drawBoard();
        }

        if(id==R.id.boardSize4){
            gridSize = 4;
            drawBoard();
        }

        if(id==R.id.boardSize5){
            gridSize = 5;
            drawBoard();
        }
        return  true;
    }

    private  void resetBoard(){
        for(int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buttonGrid[i][j].setText(" ");
                buttonGrid[i][j].setPlayer(0);
            }
        }
        setUpGame();
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    public boolean isPlayerOneTurn(){
        return playerOneTurn;
    }

    public void setPlayerOneTurn(boolean playerOneTurn){
        this.playerOneTurn = playerOneTurn;
    }
}
