package prs.ansh.bc.impl;

import java.util.AbstractList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import prs.ansh.bc.BerlinClock;
import prs.ansh.bc.exception.BasicParseException;

@Service
public class BerlinClockImpl implements BerlinClock {

	private static final String SEPARATOR = " ";

	// Basic pattern - intentionally left non-strict
	private static final Pattern PAT_HHMMSS = Pattern.compile("([\\d]{2}):([\\d]{2}):([\\d]{2})");

	private static final List<String> SECS_ELEM_LIST = new AbstractList<String>() {

		@Override
		public String get(int index) {
			return "";
		}

		@Override
		public int size() {
			return 29;
		}
	};
	
	private final CombiningSequencer<String, Integer> combiner = new CombiningSequencer<String, Integer>(SEPARATOR, 
									new LevelRecombiner<String>(2, SECS_ELEM_LIST, null, SEPARATOR, toList("O"), "Y"),
									new LevelRecombiner<String>(5, toList("RRRR"), "O", SEPARATOR, toList("RRRR"), "O"),
									new LevelRecombiner<String>(5, toList("YYRYYRYYRYY"), "O", SEPARATOR, toList("YYYY"), "O"));
	
	@Override
	public String getDisplayLine(String hhMmSs) throws BasicParseException {
		Matcher matcher = PAT_HHMMSS.matcher(hhMmSs);
		if (!matcher.matches()) {
			throw new BasicParseException();
		}

		int secs = Integer.parseInt(matcher.group(3));
		int minutes = Integer.parseInt(matcher.group(2));
		int hours = Integer.parseInt(matcher.group(1));

		return fromList(combiner.generateSequence(secs, hours, minutes));
	}

	private static List<String> toList(final String string) {
		return new AbstractList<String>() {
			public String get(int index) {
				return String.valueOf(string.charAt(index));
			}

			public int size() {
				return string.length();
			}

		};
	}

	private static String fromList(List<String> stringList) {
		StringBuilder bldr = new StringBuilder();
		for (String string : stringList) {
			bldr.append(string);
		}

		return bldr.toString();
	}
}
