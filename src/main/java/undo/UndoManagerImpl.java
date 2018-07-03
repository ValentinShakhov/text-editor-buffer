package undo;

public class UndoManagerImpl implements UndoManager {

    private final Document doc;

    private final Buffer buffer;

    UndoManagerImpl(Document doc, Buffer buffer) {
        this.doc = doc;
        this.buffer = buffer;
    }

    @Override
    public void registerChange(Change change) {
        buffer.add(change);
        change.apply(doc);
    }

    @Override
    public boolean canUndo() {
        return buffer.hasUndo();
    }

    @Override
    public void undo() {
        final Change change = buffer.getChangeForUndo();
        change.revert(doc);
    }

    @Override
    public boolean canRedo() {
        return buffer.hasRedo();
    }

    @Override
    public void redo() {
        final Change change = buffer.getChangeForRedo();
        change.apply(doc);
    }
}