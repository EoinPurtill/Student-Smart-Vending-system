package undo;

public class DealMenuMemento implements Memento{
    private Integer state;

    public Object getState(){
        return state;
    }
    
    public void setState(Object state){
        this.state = (Integer)state;
    }
}