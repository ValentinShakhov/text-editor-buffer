package com.wirecard.undomanager;

/**
 * A factory for {@link UndoManager}s.
 *
 */
public interface UndoManagerFactory {

	/**
	 * Creates an getChangeForUndo manager for a {@link Document}.
	 * 
	 * @param doc The document to create the {@link UndoManager} for.
	 * @param bufferSize The number of {@link Change}es stored.
	 * @return The {@link UndoManager} created.
	 */
	UndoManager createUndoManager(Document doc, int bufferSize);
	
}
