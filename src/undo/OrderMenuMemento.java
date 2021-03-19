package undo;

public class OrderMenuMemento implements Memento{
    private Integer state;

    public Object getState(){
        return state;
    }
    
    public void setState(Object state){
        this.state = (Integer)state;
    }
}