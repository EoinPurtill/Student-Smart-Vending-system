package undo;

import java.lang.Integer;

public class DealMenuOriginator implements MenuOriginator{
    private int value;

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public MenuMemento createMemento(){
        DealMenuMemento dmMemento = new DealMenuMemento();
        dmMemento.setState(Integer.valueOf(this.value));
        return dmMemento;
    }

    public void restore(MenuMemento memento){
        DealMenuMemento dmMemento = (DealMenuMemento)memento;
        this.value = ((Integer)dmMemento.getState()).intValue();
    }
}