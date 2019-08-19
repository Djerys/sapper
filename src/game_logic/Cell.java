package game_logic;

class Cell {
    boolean isRevealed = false;
    boolean hasMine = false;
    boolean hasFlag = false;
    int nearMinesCount = 0;

    @Override
    public String toString() {
        return "Cell{" +
                "isRevealed=" + isRevealed +
                ", hasMine=" + hasMine +
                ", hasFlag=" + hasFlag +
                ", nearMinesCount=" + nearMinesCount +
                '}';
    }
}
