package game_logic;

import java.util.Objects;
import java.util.Set;

public final class Position {
    private final int width;
    private final int height;

    public Position(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException(
                    "Width and height must be positive.");
        }
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    boolean isNeighbourOf(Position other) {
        return dimensionRange(width).contains(other.width) &&
                dimensionRange(height).contains(other.height);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Position position = (Position) other;
        return width == position.width &&
                height == position.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "Position{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    private Set<Integer> dimensionRange(int dimension) {
        return Set.of(dimension - 1, dimension, dimension + 1);
    }
}
