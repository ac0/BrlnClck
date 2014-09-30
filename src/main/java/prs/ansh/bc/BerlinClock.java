package prs.ansh.bc;

import prs.ansh.bc.exception.BasicParseException;

/**
 * The Berlin clock. Contains interfaces for conversions to/from etc
 *
 */
public interface BerlinClock {

	/**
	 * 
	 * @param hhMmSs time in the format string HH:MM:SS
	 * @return the output string as per spec
	 * 
	 * @throws BasicParseException if the hhMmSs string's format is invalid
	 * @throws IndexOutOfBoundsException if the input causes overflow in the finite output
	 */
	String getDisplayLine(String hhMmSs) throws BasicParseException, IndexOutOfBoundsException;
}
