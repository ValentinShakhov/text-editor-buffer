package undo;

/**
 * An abstraction of a change that can be registered with an
 * {@link UndoManager} and applied to a {@link Document}. 
 *
 */
public interface Change {
	
	/**
	 * Apply this change to the given document.
	 * 
	 * @param doc The document to apply the change to.
	 * @throws IllegalStateException If the change cannot be applied to <code>doc</code>
	 * 			(that is if the document refuses the application of the change). 
	 */
	void apply(Document doc);

	/**
	 * Reverts this change in the given document.
	 * 
	 * @param doc The document to revert the change in.
	 * @throws IllegalStateException If the change cannot be reverted in <code>doc</code>
	 * 			(that is if the document refuses the reversion of the change). 
	 */
	void revert(Document doc);

}
