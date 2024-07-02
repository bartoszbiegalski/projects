package org.gogroup.components.gameComponents.boardVBox;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class FieldGroup extends Group {
    private int size;
    private Field[][] fields;

    public FieldGroup(int size)
    {
        this.size = size;
        this.fields = new Field[size][size];
    }

    public void setField(int i, int j, int x, int y, Color color)
    {
        if(this.findField(i, j) != null) {
            findField(i, j).setFill(color);
        }
    }

    public void updateField(int i, int j, Color color)
    {
        this.findField(i, j).setFill(color);
    }

    public Field findField(int i, int j) {
        for (Node node : getChildren()) {
            if (node instanceof Field) {
                Field fieldNode = (Field) node;
                if (fieldNode.getI() == i && fieldNode.getJ() == j) {
                    return fieldNode;
                }
            }
        }
        return null;
    }

    public void addToGroup(int i, int j, int x, int y)
    {
        fields[i][j] = new Field(i, j, x, y);
        getChildren().add(fields[i][j]);
    }
}
