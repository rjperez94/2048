import ecs100.*;

public class Game2048withUndo implements UIButtonListener, UIKeyListener {

    Board2048withUndo game = null;

    private boolean hasReachedTarget = false;

    public Game2048withUndo () {
        UI.addButton("Restart", this);
        UI.addButton("Up", this);
        UI.addButton("Down", this);
        UI.addButton("Left", this);
        UI.addButton("Right", this);
        UI.addButton("Undo", this);

        UI.setKeyListener(this);

        startGame();
        UI.setImmediateRepaint(false);
    }

    /** Respond to button presses */
    public void buttonPerformed(String button) {
        if (button.equals("Restart")) startGame();
        else if (button.equals("Undo")) undo ();
        else move (button);
    }

    /** Respond to key actions */
    public void keyPerformed(String key) {
        switch (key) {
            case "Space" -> startGame();
            case "Left", "Right", "Up", "Down" -> move(key);
            case "u" -> undo();
        }
    }

    private void startGame() {
        UI.clearText();
        UI.println("Move the tiles with the arrow keys but click on the graphics pane first.");
        UI.println("or use the Left/Right/Up/Down buttons");
        UI.println("Press SPACE to restart and U to undo");
        UI.println("Each time 2 tiles with the same number touch, the numbers are added and the two tiles merge.");
        UI.println("Produce the magic number of 2048.");

        int SIZE = 5;
        game = new Board2048withUndo (SIZE);
        game.insertRandomTile();
        game.redraw();
    }

    private void move(String direction) {
        if (game == null) {
            UI.println("Game need to be restarted");
            return;
        }

        switch (direction) {
            case "Left" -> game.left();
            case "Right" -> game.right();
            case "Up" -> game.up();
            case "Down" -> game.down();
        }
        game.undoStack();
        game.redraw();

        // Only display the message the first time
        if (! hasReachedTarget && game.hasReachedTarget()) {
            hasReachedTarget = true;
            UI.println("Game won!!!");
            UI.println("you can restart a new game or carry this game");
        }

        // Insert a new random tile
        UI.sleep(100);
        game.insertRandomTile();
        game.redraw();

        // Check if game is over
        if (game.isGameOver()) {
            UI.println("Game OVER!!!");
            game = null;
        }
    }
    
    private void undo () {
        game.undo();
        game.redraw();
    }

    public static void main(String[] arguments){
        new Game2048withUndo();
    }
}