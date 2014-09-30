package prs.ansh.bc.impl;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import prs.ansh.bc.LevelDivider;

/**
 * Separate an integer to two levels based on the base
 *
 */
public class LevelDividerImpl implements LevelDivider {

	final int base;

	/**
	 * 
	 * @param base the base to be set
	 * 
	 * @throws RuntimeException if base is not a positive integer
	 */
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
