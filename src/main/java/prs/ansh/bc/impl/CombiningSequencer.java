package prs.ansh.bc.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import prs.ansh.bc.CountSequencer;

public class CombiningSequencer<T, V> {

	private final List<CountSequencer<T>> sequencers;
	private final T separator;

	@SafeVarargs
	public CombiningSequencer(T separator, CountSequencer<T>... sequencers) {
		this.sequencers = Arrays.asList(sequencers);
		this.separator = separator;
	}

	List<T> generateSequence(V firstVal, @SuppressWarnings("unchecked") V... restVals) {
		List<T> sequence = new LinkedList<T>();
		Iterator<CountSequencer<T>> seqIterator = sequencers.iterator();
		sequence.addAll(seqIterator.next().generateSequence((Integer) firstVal));
		for (V val : restVals) {
			sequence.add(separator);
			sequence.addAll(seqIterator.next().generateSequence((Integer) val));
		}

		return sequence;
	}
}
