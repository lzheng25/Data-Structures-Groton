import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Queue;
import java.util.Stack;

/*
 * Thought Questions
 * 
 * 1. Prove that with n number of disks, the best solution requires 2^n -1 moves.
 * 	  Base case: let n = 1. 261 - 1 = 2 - 1 = 1
 * 	  It takes 1 move to move one disk from the first to the last peg, so our base case is proven.
 * 	  Inductive Case: Assume it is true for 1 <= n <= k that a problem with n disks takes 2^n - 1 moves to solve.
 * 	  Show it is true for n = k + 1, that it takes 2^(k+1) - 1 moves to solve.
 * 	  First we need to move k disks from the first peg to the middle, which takes 2^k - 1 moves. Then we move the
 *    remaining disk on the first peg to the end, which takes 1 move. Finally, we move the k disks in the middle to
 *    the end, taking 2^k - 1 moves. So, in total, we spend 2^k - 1 + 1 + 2^k - 1 moves, which simplifies to
 *    2^k + 2^k - 1. This can be rewritten as 2(2^k) - 1, and by the product rule, we can can add the exponents
 *    of the two 2's, which is 1 + k. The final form of the equation is 2^(k+1) - 1.
 *    By showing that a game with k+1 disks takes 2^(k+1) - 1 moves to complete, we have proved by induction that
 *    a game with n disks requires 2^n - 1 moves, for any n greater than or equal to 0.
 *    
 * 2. We were not able to reuse any code between the graphical and command line versions. Our Peg, Disk, and 
 * 	  Main classes contained intertwined game logic and UI logic (for example, the game logic reading the state of 
 *    a UI element), and this interdependency prohibited the separation of the two while going about making a CLI. 
 *    To be able to reuse code, perhaps abstracting the game logic into one class and having UI/CLI classes extend 
 *    the game logic classes could work.
 *    
 * 3. ABACABA is the order in which you need to move the disks in a Towers of Hanoi game containing 3 disks labeled 
 *    A, B, and C, smallest to largest. To beat a game with 3 disks with minimal moves, you move A to last peg, move 
 *    B to middle peg, then A onto middle peg. Then move C to the end. Then move A to first peg, B to last peg, and 
 *    finally A to the last peg to complete the puzzle. This is the ABACABA pattern. It is cool because it can be
 *    extended recursively to a game with any number of disks. In a game with 4 disks, we need to move disks A, B, C 
 *    to the middle (ABACABA), then disk D to the end, and then A, B, C to the end again (ABACABA). In total, that is
 *    ABACABADABACABA. Continuing this pattern, the move order for 5 disks is ABACABADABACABAEABACABADABACABA.
 * 
 */


public class TowersOfHanoi extends WindowController implements KeyListener {

    // Constants for the window
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 800;
    private static final double PEGH = 500;
    private static final double PEGW = 20;
    // Space bw pegs, value is 246.667
    private static final double XOFFSET = 120;
    private static final double XSPACE = (WIDTH - XOFFSET * 2 - 3 * PEGW) / 2.0;
    // Space above pegs, value is 250
    private static final double YSPACE = (HEIGHT - PEGH) / 2.0;
    // Width of largest disk
    private static final double DISKW = 200;
    // Total height of disks
    private static final double DISKTH = 2.0 * PEGH / 3.0;
    // Width difference between each disk
    private static final double DISKBW = 25;

    // bicolor mode on or off
    private boolean bi;

    private Disk selectedDisk;
    private Location selectedDiskInitialLocation;
    private Peg selectedPeg;
    private static final int NUMPEGS = 3;

    private int numDisks;

    private TurnRecord turn;
    private boolean show = true;

    protected Text undoBT;
    protected FramedRect undoB;
    protected Text resetBT;
    protected FramedRect resetB;
    protected Text saveBT;
    protected FramedRect saveB;
    protected Text autoBT;
    protected FramedRect autoB;
    protected Text bicolorBT;
    protected FramedRect bicolorB;
    protected static final int BUTTON_WIDTH = 100;
    protected static final int BUTTON_HEIGHT = 40;

    protected Text movesT;
    private int moves;

    protected Text victory;

    protected Text changeDisks;

    protected Text suggestion;

    protected Text suboptimal;

    private Peg[] pegs;
    private Disk[] disks;
    private Disk[] disksbi1;
    private Disk[] disksbi2;

    private Stack<TurnRecord> turns;

    private AutoPlay ap;

    private LoadGame loadedGame;

    private Queue<TurnRecord> optimal;
    private Stack<TurnRecord> optimalS;
    private Slide slide;

    public static void main(String[] args) {
    	TowersOfHanoi main = new TowersOfHanoi();
        main.startController(WIDTH, HEIGHT);
        if(args.length>0) main.loadGame(args[0]);
    }

    public void begin() {
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);

        // Undo button
        undoB = new FramedRect(WIDTH - 90 - BUTTON_WIDTH / 2, HEIGHT / 7 - 80, BUTTON_WIDTH, BUTTON_HEIGHT, canvas);

        undoBT = new Text("Undo", undoB.getX() + BUTTON_WIDTH / 2, undoB.getY() + BUTTON_HEIGHT / 2, canvas);
        undoBT.setFontSize(20);
        undoBT.move(undoBT.getWidth() / -2.0, undoBT.getHeight() / -2.0);

        // Reset button
        resetB = new FramedRect(WIDTH * 3 / 4 - BUTTON_WIDTH / 2, HEIGHT / 7 - 30, BUTTON_WIDTH, BUTTON_HEIGHT, canvas);

        resetBT = new Text("Reset", resetB.getX() + BUTTON_WIDTH / 2, resetB.getY() + BUTTON_HEIGHT / 2, canvas);
        resetBT.setFontSize(20);
        resetBT.move(resetBT.getWidth() / -2.0, resetBT.getHeight() / -2.0);

        // Save button
        saveB = new FramedRect(WIDTH * 3 / 4 - BUTTON_WIDTH / 2, HEIGHT / 7 - 80, BUTTON_WIDTH, BUTTON_HEIGHT, canvas);

        saveBT = new Text("Save", saveB.getX() + BUTTON_WIDTH / 2, saveB.getY() + BUTTON_HEIGHT / 2, canvas);
        saveBT.setFontSize(20);
        saveBT.move(saveBT.getWidth() / -2.0, saveBT.getHeight() / -2.0);

        // AutoPlay button
        autoB = new FramedRect(WIDTH - 90 - BUTTON_WIDTH / 2, HEIGHT / 7 - 30, BUTTON_WIDTH, BUTTON_HEIGHT, canvas);

        autoBT = new Text("Auto Play", autoB.getX() + BUTTON_WIDTH / 2, autoB.getY() + BUTTON_HEIGHT / 2, canvas);
        autoBT.setFontSize(20);
        autoBT.move(autoBT.getWidth() / -2.0, autoBT.getHeight() / -2.0);

        bicolorB = new FramedRect(WIDTH - 90 - BUTTON_WIDTH / 2, HEIGHT / 7 - 130, BUTTON_WIDTH, BUTTON_HEIGHT, canvas);
        bicolorBT = new Text("Bicolor", bicolorB.getX() + BUTTON_WIDTH / 2, bicolorB.getY() + BUTTON_HEIGHT / 2,
                canvas);
        bicolorBT.setFontSize(20);
        bicolorBT.move(bicolorBT.getWidth() / -2.0, bicolorBT.getHeight() / -2.0);

        victory = new Text("Temporary", 0, 0, canvas);
        victory.setFontSize(30);

        movesT = new Text("Number of moves: " + moves, 80, 50, canvas);

        suboptimal = new Text("Suboptimal move", 200 + movesT.getWidth(), 50, canvas);
        suboptimal.setFontSize(20);
        suboptimal.setColor(Color.RED);
        suboptimal.hide();

        changeDisks = new Text("Type 3-7 to change the number of disks", 0, 0, canvas);
        changeDisks.moveTo(WIDTH / 2.0 - changeDisks.getWidth() / 2.0, HEIGHT * 9 / 10 - 40);

        suggestion = new Text("Drop disk at the top of the peg for best effect", 80, 90, canvas);
        suggestion.setFontSize(20);

        numDisks = 3;

        pegs = new Peg[NUMPEGS];
        for (int i = 0; i < pegs.length; i++) {
            pegs[i] = new Peg(XOFFSET + (XSPACE * (i)), YSPACE, PEGH, PEGW, i, canvas);
        }
        // start in normal mode
        bi = false;

        gameInit(bi);
    }

    public void loadGame(String name) {
        loadedGame = new LoadGame(name);
        numDisks = loadedGame.getNumDisks();
        int numTurns = loadedGame.getNumTurns();
        gameInit(false);
        System.out.println(numTurns);
        for (int i = 0; i < numTurns; i++) {
            LoadedRecord loadedTurn = loadedGame.getNextTurn();
            Peg from = pegs[loadedTurn.from];
            Peg to = pegs[loadedTurn.to];
            to.add(from.remove());
            turns.push(new TurnRecord(from, to));
            if (!optimal.isEmpty())
                optimalS.push(optimal.remove());
            turn = optimalS.peek();
            if (!suboptimal.isHidden())
                suboptimal.hide();
            if (show && !turns.peek().equals(turn)) {
                suboptimal.show();
                show = false;
            }
        }
        moves = numTurns;
        movesT.setText("Number of moves: " + moves);
    }

    public void gameInit(boolean bi) {

        moves = 0;
        movesT.setText("Number of moves: " + moves);
        movesT.setFontSize(20);

        victory.hide();

        disks = new Disk[numDisks];
        disksbi1 = new Disk[numDisks];
        disksbi2 = new Disk[numDisks];
        turns = new Stack<TurnRecord>();
        double diskH = 0;
        if (!bi)
            diskH = DISKTH / numDisks;
        else
            diskH = (DISKTH - 100) / numDisks;
        for (int i = 0; i < numDisks; i++) {
            double width = DISKW - (double) i * DISKBW;
            if (!bi) {
                disks[i] = new Disk(numDisks, i, XOFFSET + PEGW / 2.0 - width / 2 + 20,
                        YSPACE + PEGH - (i + 1) * diskH - 70, width, diskH, canvas, false);
            } else {
                disksbi1[i] = new Disk(numDisks, i, XOFFSET + PEGW / 2.0 - width / 2 + 20,
                        YSPACE + PEGH - (i + 1) * diskH - 70, width, diskH, canvas, true);
                disksbi2[i] = new Disk(numDisks, i, XOFFSET + XSPACE + PEGW / 2.0 - width / 2 + 20,
                        YSPACE + PEGH - (i + 1) * diskH - 70, width, diskH, canvas, true);
                if (i % 2 == 0) {
                    disksbi1[i].setColor(Color.RED);
                    disksbi2[i].setColor(Color.BLUE);
                } else {
                    disksbi1[i].setColor(Color.BLUE);
                    disksbi2[i].setColor(Color.RED);
                }
            }
        }
        if (!bi)
            pegs[0].setDisks(disks);
        else {
            pegs[0].setDisks(disksbi1);
            pegs[1].setDisks(disksbi2);
        }

        if (!bi) {
            ap = new AutoPlay(numDisks, pegs[0], pegs[2], pegs[1], movesT);
            optimal = ap.getOptimal();
            optimalS = new Stack<TurnRecord>();

            // System.out.println(optimal);

            for (Peg p : pegs)
                p.reset();

            disks = new Disk[numDisks];
            turns = new Stack<TurnRecord>();
            diskH = DISKTH / numDisks;
            for (int i = 0; i < numDisks; i++) {
                double width = DISKW - (double) i * DISKBW;
                disks[i] = new Disk(numDisks, i, XOFFSET + PEGW / 2.0 - width / 2 + 20,
                        YSPACE + PEGH - (i + 1) * diskH - 70, width, diskH, canvas, false);
            }
            pegs[0].setDisks(disks);

            slide = new Slide(pegs[0], disks[0], moves, show);

            show = true;
        }

    }

    public void onMouseClick(Location l) {
    }

    public void onMousePress(Location point) {
        if (undoB.contains(point)) {
            undo();
            return;
        } else if (resetB.contains(point)) {
            // Clear pegs and restart game
            for (Peg p : pegs)
                p.reset();
            suboptimal.hide();
            gameInit(bi);
            return;
        } else if (autoB.contains(point)) {
            // can't click while auto playing
            if (!bi && !ap.isAutoPlaying()) {
                for (Peg p : pegs)
                    p.reset();
                gameInit(false);
                ap.commence();
            }
        } else if (bicolorB.contains(point)) {
            // Clear pegs and restart game in bicolor mode
            for (Peg p : pegs)
                p.reset();
            suboptimal.hide();
            if (!bi) {
                bi = true;
                bicolorBT.setText("Normal");
            } else {
                bi = false;
                bicolorBT.setText("Bicolor");
            }

            gameInit(bi);
            return;
        }
        else if (saveB.contains(point)) {
            if(!bi) SaveGame.saveToFile(turns, numDisks);
        }
        for (Peg p : pegs) {
            Disk d = p.containsDisk(point);
            if (d != null) {
                selectedDisk = d;
                selectedPeg = p;
                selectedDiskInitialLocation = d.getRect().getLocation();
                return;
            }
        }
    }

    public Peg pegContainsDisk(Disk d) {
        for (Peg p : pegs) {
            if (p.overlaps(d.getRect())) {
                return p;
            }
        }
        return null;
    }

    public void onMouseRelease(Location point) {
        if (selectedDisk != null) {
            Peg targetPeg = pegContainsDisk(selectedDisk);
            Disk below = null;
            if (targetPeg != null)
                below = targetPeg.getTop();
            if (targetPeg != null && targetPeg != selectedPeg
                    && (below == null || below.getWidth() >= selectedDisk.getWidth())) {

                selectedPeg.remove();
                targetPeg.add(selectedDisk, false, slide);
                turns.push(new TurnRecord(selectedPeg, targetPeg));
                movesT.setText("Number of moves: " + ++moves);

                if (!bi) {
                    if (!suboptimal.isHidden())
                        suboptimal.hide();
                    if (!optimal.isEmpty())
                        optimalS.push(optimal.remove());
                    turn = optimalS.peek();
                    if (show && !turns.peek().equals(turn)) {
                        suboptimal.show();
                        show = false;
                    }
                }
            } else {
                selectedDisk.moveTo(selectedDiskInitialLocation);
            }

            if (bi) {
                boolean won = true;
                Peg one = null;
                Peg two = null;
                for (Peg p : pegs) {
                    Stack<Disk> pegDisks = p.getDisks();
                    if (p.getSize() == disksbi1.length) {
                        if (one == null)
                            one = p;
                        else if (two == null)
                            two = p;
                        else
                            break;
                    }
                    if (p.getSize() == 0)
                        break;
                    Stack<Disk> temp = new Stack<Disk>();
                    while (!pegDisks.isEmpty())
                        temp.push(pegDisks.pop());
                    Color c = temp.peek().getColorOne();
                    while (!temp.isEmpty()) {
                        Disk nextDisk = temp.pop();
                        if (!nextDisk.getColorOne().equals(c)) {
                            won = false;
                        }
                        pegDisks.push(nextDisk);
                    }
                }
                // check if won
                if (one != null && two != null && won) {
                    for (Disk d : one.getArrDisks())
                        d.win();
                    for (Disk d : two.getArrDisks())
                        d.win();
                    new VictoryAnimation(one.getArrDisks(), "best");
                    new VictoryAnimation(two.getArrDisks(), "best");
                    victory.setText("Great job! You beat bicolor mode with " + numDisks + " disks!");
                    victory.moveTo(WIDTH / 2 - victory.getWidth() / 2, HEIGHT - 200 - victory.getHeight());
                    victory.show();
                }
            }

            if (!bi && pegs[pegs.length - 1].getSize() == numDisks) {
                for (Disk d : disks)
                    d.win();
                // Check how close to optimal score
                if (moves < Math.pow(2, numDisks)) {
                    victory.setText("Great job! You got the best possible score.");
                    new VictoryAnimation(disks, "best");
                } else if (moves < Math.pow(2, numDisks) + 5) {
                    victory.setText("Nice job! You score is close to optimal.");
                    new VictoryAnimation(disks, "close");
                } else {
                    victory.setText("Victory! Try to improve your score.");
                    new VictoryAnimation(disks, "worst");
                }
                victory.moveTo(WIDTH / 2 - victory.getWidth() / 2, HEIGHT - 200 - victory.getHeight());
                victory.show();
            }

        }
        selectedDisk = null;
        selectedPeg = null;
        selectedDiskInitialLocation = null;
    }

    public void undo() {
        if (!bi && optimalS.isEmpty())
            return;
        if (turns.isEmpty() || slide.isUndoing())
            return;

        if (moves <= optimalS.size() && !bi) {
            if (!show)
                suboptimal.hide();
            optimal.add(optimalS.pop());
            for (int i = 0; i < optimal.size() - 1; i++) {
                optimal.add(optimal.remove());
            }
            if (optimalS.isEmpty())
                show = true;
        }

        // Undo turn by moving the disk from top of "to" to the top of "from"
        TurnRecord lastTurn = turns.pop();
        Peg from = lastTurn.getFrom();
        Peg to = lastTurn.getTo();
        slide = from.add(to.remove(), true, slide);
        movesT.setText("Number of moves: " + --moves);
    }

    public void onMouseDrag(Location point) {
        if (selectedDisk != null) {
            selectedDisk.moveTo(point);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        int newNumDisks = Integer.parseInt(String.valueOf(e.getKeyChar()));
        if (newNumDisks < 3 || newNumDisks > 7)
            return;
        for (Peg p : pegs)
            p.reset();
        numDisks = newNumDisks;
        gameInit(bi);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}