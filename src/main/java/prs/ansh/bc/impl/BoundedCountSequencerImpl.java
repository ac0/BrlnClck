package prs.ansh.bc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import prs.ansh.bc.CountSequencer;

/**
 * Converts a count to a fixed size sequence. The sequence is formed
 * by prefixing part of a pre-cached sequence and filling the rest with
 * a supplied filler element. How much of the pre-cached sequence is
 * used depends on the count passed in.
 * 
 * @param <T> type of sequence-element
 */
public class BoundedCountSequencerImpl<T> implements CountSequencer<T> {

	/**  The pre-cached sequence */
	final List<T> reserve;
	/**  Filler element */
	final T fillerElement;

	public BoundedCountSequencerImpl(List<T> reserve, T fillerElement) {
		this.reserve = reserve;// ideally create immutable copy at this point
		this.fillerElement = fillerElement;
	}

	/**
	 * Output a fixed-size <code>reserve.size()</code> sequence taking the first
	 * <b>count</b> elements of the pre-cached sequence and filling the rest with
	 * the fillerElement. If the fillerElement was set as null, return an empty
	 * sequence
	 */
	@Override
	public List<T> generateSequence(final int count) {
		int n = reserve.size();
		if(count > n){
			throw new IndexOutOfBoundsException("Overflow: out of range");
		}
		
		if (fillerElement == null) {
			return Collections.emptyList();
		}

		List<T> genList = new ArrayList<>(n);
		genList.addAll(reserve.subList(0, count));
		n -= count;
		while (n-- > 0) {
			genList.add(fillerElement);
		}

		return genList;
	}

}
