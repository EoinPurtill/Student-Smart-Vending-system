package undo;

public interface MenuOriginator{
    public MenuMemento createMemento();
    public void restore(MenuMemento memento);
}