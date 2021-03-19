package undo;

public interface MenuOriginator{
    public Memento createMemento();
    public void restore();
}