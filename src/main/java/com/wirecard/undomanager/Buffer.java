package com.wirecard.undomanager;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class Buffer {

    private final Deque<Change> undoDeque = new LinkedList<>();

    private final Deque<Change> redoDeque = new LinkedList<>();

    private final int capacity;

    Buffer(int capacity) {
        this.capacity = capacity;
    }

    void add(Change change) {
        if (Objects.isNull(change)) {
            throw new IllegalArgumentException("Value must not be null");
        }
        undoDeque.offer(change);
        removeObsolete(undoDeque);
        redoDeque.clear();
    }

    Change getChangeForUndo() {
        if (!hasUndo()) {
            throw new IllegalStateException("No changes to undo");
        }
        final Change result = undoDeque.pollLast();
        redoDeque.add(result);
        removeObsolete(redoDeque);
        return result;
    }

    Change getChangeForRedo() {
        if (!hasRedo()) {
            throw new IllegalStateException("No changes to redo");
        }
        final Change result = redoDeque.poll();
        undoDeque.add(result);
        removeObsolete(undoDeque);
        return result;
    }

    boolean hasUndo() {
        return !undoDeque.isEmpty();
    }

    boolean hasRedo() {
        return !redoDeque.isEmpty();
    }

    private void removeObsolete(Deque<Change> deque) {
        if (deque.size() > capacity) {
            deque.removeFirst();
        }
    }
}
