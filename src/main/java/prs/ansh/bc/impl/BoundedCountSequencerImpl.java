package prs.ansh.bc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import prs.ansh.bc.CountSequencer;

public class BoundedCountSequencerImpl<T> implements CountSequencer<T> {

	final List<T> reserve;
	final T fillerElement;

	public BoundedCountSequencerImpl(List<T> reserve, T fillerElement) {
		this.reserve = reserve;// ideally create immutable copy at this point
		this.fillerElement = fillerElement;
	}

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
