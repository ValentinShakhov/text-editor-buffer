package undo;

/**
 * An abstraction of a document to be used with the {@link UndoManager}.
 *
 */
public interface Document {

	/**
	 * Deletes a string from the document.
	 * 
	 * @param pos The position to start deletion.
	 * @param s The string to delete.
	 * @throws IllegalStateException If the document doesn't have <code>s</code>
	 * 			as <code>pos</code>.  
	 */
	void delete(int pos, String s);
	
	/**
	 * Inserts a string into the document.
	 * 
	 * @param pos The position to insert the string at.
	 * @param s The string to insert.
	 * @throws IllegalStateException If <code>pos</code> is an illegal position 
	 * 			(that is, if document is shorter than that). 
	 */
	void insert(int pos, String s);
	
	/**
	 * Sets the dot (cursor) position of the document. 
	 * 
	 * @param pos The dot position to set.
	 * @throws IllegalStateException If <code>pos</code> is an illegal position 
	 * 			(that is, if document is shorter than that). 
	 */
	void setDot(int pos);
}
