package prs.ansh.bc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import prs.ansh.bc.CountSequencer;
import prs.ansh.bc.LevelDivider;

public class LevelRecombiner<T> implements CountSequencer<T> {

	private final LevelDivider levelDivider;
	private final CountSequencer<T> numSequencer;
	private final CountSequencer<T> denomSequencer;
	private final T separator;
	private final int outputSequenceSize;

	public LevelRecombiner(int base, List<T> numSeq, T numFiller, T separator, List<T> denomSeq, T denomFiller) {
		levelDivider = new LevelDividerImpl(base);
		if (base - 1 != denomSeq.size()) {
			throw new IndexOutOfBoundsException("Sequence size expected: " + (base - 1) + " was: " + denomSeq.size());
		}

		this.outputSequenceSize = numSeq.size() + base + 1;
		numSequencer = new BoundedCountSequencerImpl<T>(numSeq, numFiller);
		denomSequencer = new BoundedCountSequencerImpl<T>(denomSeq, denomFiller);
		this.separator = separator;
	}

	@Override
	public List<T> generateSequence(int count) throws IndexOutOfBoundsException {
		Pair<Integer, Integer> divPair = levelDivider.divide(count);
		List<T> genList = new ArrayList<T>(outputSequenceSize);
		genList.addAll(numSequencer.generateSequence(divPair.getLeft()));
		if (genList.size() > 0) {
			genList.add(separator);
		}

		genList.addAll(denomSequencer.generateSequence(divPair.getRight()));

		return genList;
	}

}
