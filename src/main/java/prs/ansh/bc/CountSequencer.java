package prs.ansh.bc;

import java.util.List;

/**
 * Helps to convert a count to a sequence
 * 
 * @param <T> type of an element in the output sequence
 */
public interface CountSequencer<T> {

	/**
	 * Converts a count to a sequence
	 * 
	 * @param count
	 * @return
	 * 
	 * @throws IndexOutOfBoundsException the count is too large - probably the sequence is bounded
	 */
	List<T> generateSequence(int count) throws IndexOutOfBoundsException;
}
