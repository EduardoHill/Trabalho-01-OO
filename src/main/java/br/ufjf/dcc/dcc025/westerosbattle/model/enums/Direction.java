package br.ufjf.dcc.dcc025.westerosbattle.model.enums;

public enum Direction {

    DIREITA(0, 1),
    ESQUERDA(0, -1),
    CIMA(-1, 0),
    BAIXO(1, 0),
    DIREITA_CIMA(-1, 1),
    DIREITA_BAIXO(1, 1),
    ESQUERDA_CIMA(-1, -1),
    ESQUERDA_BAIXO(1, -1);

    private final int lineOffset;
    private final int columnOffset;

    Direction(int lineOffset, int columnOffset) {
        this.lineOffset = lineOffset;
        this.columnOffset = columnOffset;
    }

    public int getLineOffset() {
        return lineOffset;
    }

    public int getColumnOffset() {
        return columnOffset;
    }
}

