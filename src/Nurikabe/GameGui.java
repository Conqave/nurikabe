package Nurikabe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class GameGui extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    /**
     * @param game Obiekt klasy Game.
     * @param x Zmienna przyjmująca wartości x pozycji kursora.
     * @param y Zmienna przyjmująca wartości y pozycji kursora.
     * @param dimensions Obiekt, będący punktem, o wartości wymiarów planszy.
     * @param dimensionsOfRectangle Zmienna przechowująca wymiar kwadraru, czyli jednego pola planszy.
     * @param border Wartość ramek pól.
     * @param checkButton Obiekt będący przyciskiem, wywołujący podpowiedź.
     * @param checkButton Obiekt będący przyciskiem, który resetuje planszę do wersji początkowej.
     */

    private Game game1= new Game();
    private byte[][] board;
    private int x, y;
    private Point dimensions;
    private byte dimensionsOfRectangle = 75;
    private byte border = 5;
    private JButton hintButton = new JButton("Hint");
    private JButton checkButton = new JButton("Check");
    private JButton resetButton = new JButton("Reset");
    private JButton saveButton = new JButton("Save to file");



    public GameGui(Game game) throws FileNotFoundException {
        board = game.getBoard();
        dimensions = game.getDimensions();
        game1 = game;
    }

    /**
     * Metoda ta rozpoczyna wyświetlanie nowego okna z grą.
     */
    public void start(){
        setTitle("Nurikabe game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(dimensions.x*(dimensionsOfRectangle + 3*border), dimensions.y*(dimensionsOfRectangle+3*border)+2*border+200);
        hintButton.setBounds(10, dimensions.y * (dimensionsOfRectangle + (3 * border)), 120, 40);
        checkButton.setBounds(150, dimensions.y * (dimensionsOfRectangle + (3 * border)), 120, 40);
        resetButton.setBounds(10, dimensions.y * (dimensionsOfRectangle + (3 * border))+60, 120, 40);
        saveButton.setBounds(10, dimensions.y * (dimensionsOfRectangle + (3 * border))+120, 120, 40);
        add(hintButton);
        add(resetButton);
        add(checkButton);
        add(saveButton);

        ActionListener reset = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setVisible(false);
                    GameGui gameGui = new GameGui(game1);
                    gameGui.start();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };

        ActionListener hint = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hint hint = new Hint(board, game1);
                try {
                    Point pnt = new Point(hint.getRandomHint());
                    board[pnt.x][pnt.y] = 124;
                    repaint();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };

        ActionListener check = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SolverEvents solver = new SolverEvents();
                if(solver.check(board)==1) {
                System.out.println("Rozwiazanie poprawne");
                }
                else
                    System.out.println("Rozwiazanie niepoprawne");
            }
        };

        ActionListener save = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Saver saver = new Saver();

                JFrame location = new JFrame("Enter save location with filename");
                JTextField write = new JTextField(20);
                JButton save = new JButton("Save");
                location.add(save);
                location.add(write);
                write.setBounds(50, 20, 400, 60);
                save.setBounds(210, 100, 80, 20);

                location.setLayout(null);
                location.setSize(500, 180);
                location.setVisible(true);


                ActionListener saveAction = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        location.setVisible(false);
                        saver.save(board,write.getText());
                        System.out.println("Zapis zakończony");
                    }
                };
                save.addActionListener(saveAction);
            }
        };

        saveButton.addActionListener(save);
        checkButton.addActionListener(check);
        hintButton.addActionListener(hint);
        resetButton.addActionListener(reset);
        setResizable(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        setLayout(null);
        setVisible(true);

        /**
         * ładowanie panelu z planszą.
         */
        GamePanel panel = new GamePanel();
        panel.setBounds(0, 0,1000,1000);
        add(panel);
    }

    /**
     * Obsługa przycisku, odpowiedzialnego za podpowiedź.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Hint hint = new Hint(board, game1);
        try {
            Point pnt = new Point(hint.getRandomHint());
            board[pnt.x][pnt.y]=124;
            repaint();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Tworzony jest komponent, będacy planszą z grą, jest odpowiedzialny za wyświetlanie planszy.
     */
    class GamePanel extends JComponent {
        @Override
        public void paintComponent(Graphics g) {
            int ky = 0;
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, dimensions.x*(dimensionsOfRectangle+2*border), dimensions.y*(dimensionsOfRectangle+2*border));
            g.setColor(Color.white);
            for (int i = 0; i < dimensions.y; i++) {
                int kx = 0;
                for (int j = 0; j < dimensions.x; j++) {
                    if (board[i][j] == 127) {
                        g.setColor(Color.WHITE);
                        g.fillRect(kx, ky, dimensionsOfRectangle, dimensionsOfRectangle);
                    } else if (board[i][j] == 126) {
                        g.setColor(Color.RED);
                        g.fillRect(kx, ky, dimensionsOfRectangle, dimensionsOfRectangle);
                    } else if (board[i][j] == 125) {
                        g.setColor(Color.BLUE);
                        g.fillRect(kx, ky, dimensionsOfRectangle, dimensionsOfRectangle);
                    } else if (board[i][j]==124){
                        g.setColor(Color.GREEN);
                        g.fillRect(kx, ky, dimensionsOfRectangle, dimensionsOfRectangle);
                    } else {
                        Font font = new Font(" ", Font.PLAIN, 40);
                        g.setColor(Color.WHITE);
                        g.setFont(font);
                        g.drawString(String.valueOf(board[i][j]), 50 + kx, 50 + ky);
                    }
                    kx += dimensionsOfRectangle+2*border;
                }
                ky += dimensionsOfRectangle+2*border;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    /**
     * Element odpowiedzialny za obsługę kliknięc na planszy, pozwala zdefiniować wybór użytkownika.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        int ky = 0;
        for(int i = 0; i<dimensions.y; i++){
            int kx = 0;
            for(int j = 0; j<dimensions.x; j++){
                if ((x<kx+dimensionsOfRectangle) && (x>kx+border) && (y<ky+dimensionsOfRectangle) && (y>ky+border)) {

                    if(SwingUtilities.isLeftMouseButton(e)) {
                        board[i][j] = 126;
                    }
                    else if(SwingUtilities.isRightMouseButton(e)) {
                        board[i][j] = 125;
                    }
                }
                kx += dimensionsOfRectangle+2*border;
            }
            ky += dimensionsOfRectangle+2*border;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}