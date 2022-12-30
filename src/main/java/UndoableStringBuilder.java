/**
 * imports:
 */
import java.util.Stack;

/**
* This class take the standard StringBuilder class and make it undoable.
* @authors Aaron Luchan & Itamar Kuznitsov.  
* @version 1.0 , status: running.
*/
public class UndoableStringBuilder {
	
	/** Constants:	
	 */
	private StringBuilder sb;
	private Stack<StringBuilder> stack;

	/** Constructor: 
	 */
	public UndoableStringBuilder() {
		this.sb = new StringBuilder();
		this.stack = new Stack<StringBuilder>();
	}
		
	/** Functions:
	* 
	* This function appends the specified string to the character sequence.
	* @param	str - the functions string 
	* @return	appended character sequence.
	* @throws   IndexOutOfBoundsException
	*/
	public UndoableStringBuilder append(String str) {
		try {
			stack.push(new StringBuilder(sb));
			sb.append(str);
			return this;
		}
		
		catch(IndexOutOfBoundsException e){
			System.out.println(e);	
		}
		return null;
	}
	
	/**
	* This function removes the characters in a substring of this sequence.
	* @param	start - the first functions int
	* @param	end - the second functions int 
	* @return	removed characters.
	* @throws   StringIndexOutOfBoundsException
	*/
	public UndoableStringBuilder delete(int start, int end) {
		try {
			stack.push(new StringBuilder(sb));
			sb.delete(start, end);
			return this;
		}
		
		catch(StringIndexOutOfBoundsException e) {
			System.out.println(e);	
		}
		return null;
	}
	
	/**
	* This function inserts the string into this character sequence.
	* @param	offset - the functions int
	* @param	str - the functions string 
	* @return	inserted character sequence.
	* @throws   StringIndexOutOfBoundsException
	*/
	public UndoableStringBuilder insert(int offset, String str) {
		try {
			stack.push(new StringBuilder(sb));
			sb.insert(offset, str);
			return this;
		}
		
		catch(StringIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		return null;
	}	
	
	/**
	* This function replaces the characters in a substring of 
	* 	this sequence with characters in the specified String. 
	* @param	start - the first functions int
	* @param	end - the second functions int
	* @param	str - the functions string 
	* @return	replaced character sequence.
 	* @throws   StringIndexOutOfBoundsException
	*/
	public UndoableStringBuilder replace(int start,int end, String str) {
		try {
			stack.push(new StringBuilder(sb));
			sb.replace(start, end, str);		
			return this;
		}
		
		catch(StringIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		return null;
	}
	
	/**
	* This function causes the character sequence 
	* 	to be replaced by the reverse of the sequence. 
	* @return	reversed sequence.
	*/
	public UndoableStringBuilder reverse() {
		stack.push(new StringBuilder(sb));
		sb.reverse();
		return this;
	 }
	
	/**
	* This function undo your last action 
	* @return	As requested return - void.
	*/
	public void undo() {
		if(!stack.isEmpty()) {
			this.sb = this.stack.pop();	
		}
		else {
			this.sb = null;
		}
	}
	
	/**
	* This function is a standard toString func'. 
	* @return	return object description. - in our case the character sequence.
	*/
	public String toString() {
		return sb.toString();
	}
	
}
