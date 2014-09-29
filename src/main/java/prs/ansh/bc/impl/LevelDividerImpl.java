package prs.ansh.bc.impl;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import prs.ansh.bc.LevelDivider;

public class LevelDividerImpl implements LevelDivider {

	final int base;

	public LevelDividerImpl(int base) {
		if (base < 1) {
			throw new RuntimeException("no point!");
		}

		this.base = base;
	}

	@Override
	public Pair<Integer, Integer> divide(final int value) {
		return new ImmutablePair<Integer, Integer>(value / base, value % base);
	}

}
