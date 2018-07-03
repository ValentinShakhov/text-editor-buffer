package undo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BufferTest {

    private final static int CAPACITY = 3;

    @Mock
    private Change change;

    @Test
    public void should_add_one_change_and_have_undo() {
        final Buffer buffer = new Buffer(CAPACITY);
        buffer.add(change);
        assert buffer.hasUndo();
    }

    @Test
    public void should_add_one_change_and_have_no_redo() {
        final Buffer buffer = new Buffer(CAPACITY);
        buffer.add(change);
        assert !buffer.hasRedo();
    }

    @Test
    public void should_add_capacity_plus_one_changes_and_have_number_of_undo_equal_to_capacity() {
        final Buffer buffer = new Buffer(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            buffer.add(change);
        }
        buffer.add(change);
        int undoCount = 0;
        while (buffer.hasUndo()) {
            buffer.getChangeForUndo();
            undoCount++;
        }
        assert undoCount == CAPACITY;
    }

    @Test
    public void
    should_add_capacity_plus_one_changes_undo_all_and_have_number_of_redo_equal_to_capacity() {
        final Buffer buffer = new Buffer(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            buffer.add(change);
        }
        buffer.add(change);
        while (buffer.hasUndo()) {
            buffer.getChangeForUndo();
        }
        int redoCount = 0;
        while (buffer.hasRedo()) {
            buffer.getChangeForRedo();
            redoCount++;
        }
        assert redoCount == CAPACITY;
    }

    @Test
    public void should_have_one_redo_after_one_undo() {
        final Buffer buffer = new Buffer(CAPACITY);
        buffer.add(change);
        buffer.getChangeForUndo();
        int redoCount = 0;
        while (buffer.hasRedo()) {
            buffer.getChangeForRedo();
            redoCount++;
        }
        assert redoCount == 1;
    }

    @Test
    public void should_have_one_undo_after_one_redo() {
        final Buffer buffer = new Buffer(CAPACITY);
        buffer.add(change);
        buffer.getChangeForUndo();
        buffer.getChangeForRedo();
        int undoCount = 0;
        while (buffer.hasUndo()) {
            buffer.getChangeForUndo();
            undoCount++;
        }
        assert undoCount == 1;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_on_null_change() {
        final Buffer buffer = new Buffer(CAPACITY);
        buffer.add(null);
    }

    @Test
    @Ignore
    public void multi_thread_test() {
        //TODO
    }
}