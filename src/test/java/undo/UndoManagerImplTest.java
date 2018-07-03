package undo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UndoManagerImplTest {

    @Mock
    private Change change;

    @Mock
    private Buffer buffer;

    @Mock
    private Document document;

    private UndoManagerImpl undoManager;

    @Before
    public void before() {
        undoManager = new UndoManagerImpl(document, buffer);
    }

    @Test
    public void should_register_change() {
        undoManager.registerChange(change);
        verify(buffer, atLeastOnce()).add(change);
        verify(change, atLeastOnce()).apply(document);
    }

    @Test
    public void should_not_apply_change_on_buffer_failure() {
        doThrow(IllegalArgumentException.class).when(buffer).add(change);
        try {
            undoManager.registerChange(change);
        } catch (Exception ignored) {

        }
        verify(buffer, atLeastOnce()).add(change);
        verify(change, never()).apply(any(Document.class));
    }

    @Test
    public void should_undo_change() {
        doReturn(change).when(buffer).getChangeForUndo();
        undoManager.undo();
        verify(buffer, atLeastOnce()).getChangeForUndo();
        verify(change, atLeastOnce()).revert(document);
    }

    @Test
    public void should_not_revert_change_on_buffer_failure_when_undo() {
        doThrow(IllegalStateException.class).when(buffer).getChangeForUndo();
        try {
            undoManager.undo();
        } catch (Exception ignored) {

        }
        verify(buffer, atLeastOnce()).getChangeForUndo();
        verify(change, never()).revert(any(Document.class));
    }

    @Test
    public void should_redo_change() {
        doReturn(change).when(buffer).getChangeForRedo();
        undoManager.redo();
        verify(buffer, atLeastOnce()).getChangeForRedo();
        verify(change, atLeastOnce()).apply(document);
    }

    @Test
    public void should_not_apply_change_on_buffer_failure_when_redo() {
        doThrow(IllegalArgumentException.class).when(buffer).getChangeForRedo();
        try {
            undoManager.redo();
        } catch (Exception ignored) {

        }
        verify(buffer, atLeastOnce()).getChangeForRedo();
        verify(change, never()).apply(any(Document.class));
    }
}