package undo;

public class OrderMenuMemento implements MenuMemento{
    private Integer state;

    public Object getState(){
        return state;
    }
    
    public void setState(Object state){
        this.state = (Integer)state;
    }
}