package game_logic;

class Cell implements Cloneable {
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

    @Override
    public Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
