package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import static org.assertj.core.api.Assertions.assertThat;

class BishopBlackTest {

    @Test
    public void isTruePosition() {
        Cell expected = Cell.A1;
        BishopBlack bishopBlack = new BishopBlack(expected);
        assertThat(bishopBlack.position()).isEqualTo(expected);
    }

    @Test
    void way() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        assertThat(bishopBlack.way(Cell.G5))
                .isEqualTo(new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5});
    }

    @Test
    void wayException() {
        Cell startPosition = Cell.C1;
        Cell stopPosition = Cell.G6;
        BishopBlack bishopBlack = new BishopBlack(startPosition);
        ImpossibleMoveException exception =
                Assertions.assertThrows(ImpossibleMoveException.class, () -> {
            bishopBlack.way(stopPosition);
        });
        Assertions.assertEquals(String.format(
                "Could not move by diagonal from %s to %s", startPosition, stopPosition),
                exception.getMessage());
    }

    @Test
    public void fromLeftDownCornerToRightUpCorner() {
        Cell startPosition = Cell.A1;
        Cell stopPosition = Cell.H8;
        BishopBlack bishopBlack = new BishopBlack(startPosition);
        assertThat(bishopBlack.isDiagonal(startPosition, stopPosition)).isTrue();
    }

    @Test
    public void fromLeftUpCornerToRightDownCorner() {
        Cell startPosition = Cell.A8;
        Cell stopPosition = Cell.H1;
        BishopBlack bishopBlack = new BishopBlack(startPosition);
        assertThat(bishopBlack.isDiagonal(startPosition, stopPosition)).isTrue();
    }

    @Test
    public void fromLeftUpToRightDown() {
        Cell startPosition = Cell.C7;
        Cell stopPosition = Cell.F4;
        BishopBlack bishopBlack = new BishopBlack(startPosition);
        assertThat(bishopBlack.isDiagonal(startPosition, stopPosition)).isTrue();
    }

    @Test
    public void isNotDiagonalfromLeftUpToRightDown() {
        Cell startPosition = Cell.B7;
        Cell stopPosition = Cell.E2;
        BishopBlack bishopBlack = new BishopBlack(startPosition);
        assertThat(bishopBlack.isDiagonal(startPosition, stopPosition)).isFalse();
    }

    @Test
    public void isNotDiagonalfromLeftDownCornerToRightUp() {
        Cell startPosition = Cell.A1;
        Cell stopPosition = Cell.H6;
        BishopBlack bishopBlack = new BishopBlack(startPosition);
        assertThat(bishopBlack.isDiagonal(startPosition, stopPosition)).isFalse();
    }

    @Test
    public void isCorrectCopy() {
        Cell startPosition = Cell.A1;
        Cell newPosition = Cell.B2;
        BishopBlack bishopBlack = new BishopBlack(startPosition);
        Figure figureOnNewPosition = bishopBlack.copy(newPosition);
        assertThat(figureOnNewPosition.position()).isEqualTo(newPosition);
    }
}