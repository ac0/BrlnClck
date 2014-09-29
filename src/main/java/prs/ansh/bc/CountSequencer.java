package prs.ansh.bc;

import java.util.List;

public interface CountSequencer<T> {
	
	List<T> generateSequence(int count) throws IndexOutOfBoundsException;
}
