package undo;

import java.lang.Integer;

public class OrderMenuOriginator implements MenuOriginator{
    private int value;

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public MenuMemento createMemento(){
        OrderMenuMemento dmMemento = new OrderMenuMemento();
        dmMemento.setState(Integer.valueOf(this.value));
        return dmMemento;
    }

    public void restore(MenuMemento memento){
        OrderMenuMemento dmMemento = (OrderMenuMemento)memento;
        this.value = ((Integer)dmMemento.getState()).intValue();
    }
}