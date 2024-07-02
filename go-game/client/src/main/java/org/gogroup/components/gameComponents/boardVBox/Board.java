package org.gogroup.components.gameComponents.boardVBox;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Board {
    private int size;
    private int layoutX;
    private int layoutY;
    private double containerWidth;
    private double containerHeight;
    private Color lineColor = Color.BLACK;
    private FieldGroup group;


    public Board(int size, int layoutX, int layoutY) {
        this.size = size;
        this.layoutY = layoutY;
        this.layoutX = layoutX;
        this.group = new FieldGroup(size);
        this.containerHeight = (this.layoutY-size)*(this.size-1);
        this.containerWidth = (this.layoutX-size)*(this.size-1);
        setBoard();
    }

    private void addVerticalLines() {
        for (int i = 0; i < this.size; i++) {
            int x = (int) (this.containerWidth / (this.size-1)) * (i);

            Line verticalLine = new Line(x, 0, x, this.containerHeight);
            verticalLine.setStrokeWidth(2);
            verticalLine.setStroke(this.lineColor);

            group.getChildren().add(verticalLine);
        }
    }

    private void addHorizontalLines() {
        for (int i = 0; i < this.size; i++) {
            int y = (int) (this.containerHeight / (this.size-1))* (i);

            Line horizontalLine = new Line(0, y, this.containerWidth, y);
            horizontalLine.setStrokeWidth(2);
            horizontalLine.setStroke(this.lineColor);

            group.getChildren().add(horizontalLine);
        }
    }

    private void addFields() {
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++)
            {
                int x = (int) (this.containerWidth / (this.size-1)) * (i);
                int y = (int) (this.containerHeight / (this.size-1))* (j);

                this.group.addToGroup(i, j, x, y);
            }
        }
    }

    private void setBoard()
    {
        addHorizontalLines();

        addVerticalLines();

        addFields();
    }

    public FieldGroup getGroup()
    {
        return group;
    }

    public int getSize() {
        return size;
    }

    public void updateField(int i, int j, Color color) {
        group.updateField(i, j, color);
    }
}
