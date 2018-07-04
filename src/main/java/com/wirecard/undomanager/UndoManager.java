package com.wirecard.undomanager;

/**
 * A manager for getChangeForUndo and redo operations to {@link Document}s, based
 * on {@link Change} objects.
 *
 */
public interface UndoManager {

	/**
	 * Registers a new change in this getChangeForUndo manager. If the buffer
	 * size of the getChangeForUndo manager is filled, replace the oldest change
	 * with the one provided to this method.
	 * 
	 * @param change The change to register.
	 */
	void registerChange(Change change);
	
	/**
	 * Returns <code>true</code> if there is currently a change that 
	 * can be undone, and <code>false</code> otherwise. 
	 */
	boolean canUndo();
	
	/**
	 * Performs the getChangeForUndo operation of the current change.
	 * 
	 * @throws IllegalStateException If the manager is in a state that 
	 * 			does not allow an getChangeForUndo (that is if either {@link #canUndo()}
	 * 			would have returned <code>false</code>, or the application
	 * 			of the change failed).
	 */
	void undo();
	
	/**
	 * Returns <code>true</code> if there is currently a change that 
	 * can be redone, and <code>false</code> otherwise. 
	 */
	boolean canRedo();
	
	/**
	 * Performs the redo operation of the current change.
	 * 
	 * @throws IllegalStateException If the manager is in a state that 
	 *  		does not allow an redo (that is if either {@link #canRedo()} 
	 *  		would have returned <code>false</code>, or the application
	 *  		of the change failed).
	 */
	void redo();
	
}
