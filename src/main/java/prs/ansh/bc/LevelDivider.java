package prs.ansh.bc;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Divide an int value to a pair of values each at a different
 * level. One example is based on a place-value notation
 *
 */
public interface LevelDivider {

	Pair<Integer, Integer> divide(int value);
}
