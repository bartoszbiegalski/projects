package org.gogroup.components.gameComponents.boardVBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Field extends Circle{
    private int i;
    private int j;

    public Field(int i, int j, int x, int y) {
        this.i = i;
        this.j = j;

        setFill(Color.TRANSPARENT);
        setRadius(10);

        setLayoutX(x);
        setLayoutY(y);
    }


    public int getI() { return i; }
    public int getJ() { return j; }


}
