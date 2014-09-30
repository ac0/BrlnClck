package prs.ansh.bc;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import prs.ansh.bc.driver.AppConfig;
import prs.ansh.bc.exception.BasicParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={AppConfig.class})
public class TestBerlinClock {

	@Autowired
	private BerlinClock bClck;
	
	@Test(expected = BasicParseException.class)
	public void testWithStringWhichDoesntRepresentTime(){
		bClck.getDisplayLine("somestring");
	}
	
	@Test
	public void testInvocationWithLegalTimeString(){
		bClck.getDisplayLine("13:02:12");
	}
	
	@Test(expected = BasicParseException.class)
	public void testSuccessfulInvocationWithLegalYetInvalidTimeString(){
		bClck.getDisplayLine("13:02");
	}
	
	@Test
	public void invokeAndAssertLegalSecondsDisplay(){
		assertTrue(bClck.getDisplayLine("13:02:12").startsWith("Y"));
		assertTrue(bClck.getDisplayLine("13:02:13").startsWith("O"));
	}
	
	@Test
	public void testUATScenario1(){
		assertEquals("Y OOOO OOOO OOOOOOOOOOO OOOO", bClck.getDisplayLine("00:00:00"));
	}
	
	@Test
	public void testUATScenario2(){
		assertEquals("O RROO RRRO YYROOOOOOOO YYOO", bClck.getDisplayLine("13:17:01"));
	}
	
	@Test
	public void testUATScenario3(){
		assertEquals("O RRRR RRRO YYRYYRYYRYY YYYY", bClck.getDisplayLine("23:59:59"));
	}
	
	@Test
	public void testUATScenario4(){
		assertEquals("Y RRRR RRRR OOOOOOOOOOO OOOO", bClck.getDisplayLine("24:00:00"));
	}
	
	@Test
	public void testInvocationWithValid24plusTimeString(){
		assertEquals("O RRRR RRRR YYROOOOOOOO YYOO", bClck.getDisplayLine("24:17:47"));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvocationWith24plusOverflowingTimeString(){
		bClck.getDisplayLine("25:05:01");
	}
	
	@Test
	public void testInvocationOnValidSecondsBoundary(){
		bClck.getDisplayLine("22:05:59");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvocationOnInValidSecondsBoundary(){
		bClck.getDisplayLine("22:05:60");
	}
	
	@Test
	public void testInvocationOnValidMinutesBoundary(){
		bClck.getDisplayLine("22:59:59");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvocationOnInValidMinutesBoundary(){
		bClck.getDisplayLine("22:60:59");
	}
	
	@Test(expected = BasicParseException.class)
	public void testInvocationOnNegativeHour(){
		bClck.getDisplayLine("-2:05:30");
	}
	
}
