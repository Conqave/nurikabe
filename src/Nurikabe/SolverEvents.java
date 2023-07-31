package Nurikabe;

public class SolverEvents {
    int a = 0;
    int z = 0;
    int c = 0;
    int y = 0;
    int x = 0;

    public SolverEvents() {
    }

    public int check(byte[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == 127)
                    z = 0;

            }
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == 125) {
                    if (i - 1 >= 0) {
                        if (tab[i - 1][j] == 125)
                            a++;
                    }
                    if (i + 1 < tab.length) {
                        if (tab[i + 1][j] == 125)
                            a++;
                    }
                    if (j - 1 >= 0) {
                        if (tab[i][j - 1] == 125)
                            a++;
                    }
                    if (j + 1 < tab.length) {
                        if (tab[i][j + 1] == 125)
                            a++;
                    }

                    if (a == 4) {
                        c = 1;
                    }
                }
            }
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == 125) {
                    if (i - 1 >= 0) {
                        if (tab[i - 1][j] == 125)
                            x++;
                    }
                    if (j + 1 < tab.length) {
                        if (tab[i][j + 1] == 125)
                            x++;
                    }
                    if (i - 1 >= 0 && j + 1 < tab.length) {
                        if (tab[i - 1][j + 1] == 125)
                            x++;
                    }
                    if (x == 3) {
                        c = 0;
                    }
                }
            }
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == 125) {
                    if (i + 1 < tab.length) {
                        if (tab[i + 1][j] == 125)
                            x++;
                    }
                    if (j - 1 >= 0) {
                        if (tab[i][j - 1] == 125)
                            x++;
                    }
                    if (j - 1 >= 0 && i + 1 < tab.length) {
                        if (tab[i + 1][j - 1] == 125)
                            x++;
                    }
                    if (x == 3) {
                        c = 0;
                    }
                }
            }
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == 125) {
                    if (i - 1 >= 0) {
                        if (tab[i - 1][j] == 125)
                            x++;
                    }
                    if (j - 1 >= 0) {
                        if (tab[i][j - 1] == 125)
                            x++;
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        if (tab[i - 1][j - 1] == 125)
                            x++;
                    }
                    if (x == 3) {
                        c = 0;
                    }
                }
            }
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == 125) {
                    if (j + 1 < tab.length) {
                        if (tab[i][j + 1] == 125)
                            x++;
                    }
                    if (i + 1 < tab.length) {
                        if (tab[i + 1][j] == 125)
                            x++;
                    }
                    if (i + 1 < tab.length && j + 1 < tab.length) {
                        if (tab[i + 1][j + 1] == 125)
                            x++;
                    }
                    if (x == 3) {
                        c = 0;
                    }
                }
            }
        }
        if (z == 0 && c == 0) {
            y = 0;
        }
        return y;
    }

}

/**
 * jeśli check zwaraca 0 to jest poprawnie a jeśli 1 to niepoprawnie
 */
