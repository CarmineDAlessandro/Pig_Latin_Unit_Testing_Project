package it.unisa.pig_latin;

import static org.junit.Assert.*;

import org.junit.Test;

public class PiglatinTest {

	@Test
	public void testPhrase() throws Exception {
		Piglatin piglatin = new Piglatin("She believed");
		String phrase = piglatin.getPhrase();
		assertEquals("She believed", phrase);
	}
	
	@Test
	public void testPhraseWithSingleWord() throws Exception {
		Piglatin piglatin = new Piglatin("Sbeve");
		String phrase = piglatin.getPhrase();
		assertEquals("Sbeve", phrase);
	}
	
	/*
	 * Invalid sentences
	 * 
	 * */
	
	@Test(expected=InvalidPhraseException.class)
	public void testPhraseWithInvalidDoubleSpace() throws Exception {
		new Piglatin("He     lied");
	}

	@Test(expected=InvalidPhraseException.class)
	public void testPhraseStartsWithSpace() throws Exception {
		new Piglatin(" It aint much but it is a honest work");
	}
	
	@Test(expected=InvalidPhraseException.class)
	public void testPhraseEndsWithSpace() throws Exception {
		new Piglatin("Sometimes you gotta get your hands dirty ");
	}
	
	@Test(expected=InvalidPhraseException.class)
	public void testPhraseWithInvalidCharacters() throws Exception {
		new Piglatin("I'm inevitable");
	}
	
	/*
	 * Word starting with vowel or single consonant
	 * 
	 * */
	
	@Test
	public void testTranslateWordStartingWithVowelA() throws Exception {
		Piglatin piglatin = new Piglatin("abyl");
		String translation = piglatin.translate();
		assertEquals("abylay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithVowelE() throws Exception {
		Piglatin piglatin = new Piglatin("eczema");
		String translation = piglatin.translate();
		assertEquals("eczemaay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithVowelI() throws Exception {
		Piglatin piglatin = new Piglatin("iris");
		String translation = piglatin.translate();
		assertEquals("irisay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithVowelO() throws Exception {
		Piglatin piglatin = new Piglatin("ossimoro");
		String translation = piglatin.translate();
		assertEquals("ossimoroay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithVowelU() throws Exception {
		Piglatin piglatin = new Piglatin("uruguay");
		String translation = piglatin.translate();
		assertEquals("uruguayay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithConsonantB() throws Exception {
		Piglatin piglatin = new Piglatin("bird");
		String translation = piglatin.translate();
		assertEquals("irdbay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithConsonantC() throws Exception {
		Piglatin piglatin = new Piglatin("cook");
		String translation = piglatin.translate();
		assertEquals("ookcay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithConsonanR() throws Exception {
		Piglatin piglatin = new Piglatin("roy");
		String translation = piglatin.translate();
		assertEquals("oyray", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithConsonantY() throws Exception {
		Piglatin piglatin = new Piglatin("yolo");
		String translation = piglatin.translate();
		assertEquals("oloyay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWithConsonantZ() throws Exception {
		Piglatin piglatin = new Piglatin("zorro");
		String translation = piglatin.translate();
		assertEquals("orrozay", translation);
	}
	
	/*
	 * A whole sentence
	 * 
	 * */
	
	@Test
	public void testTranslateEmptySentence() throws Exception {
		Piglatin piglatin = new Piglatin("");
		String translation = piglatin.translate();
		assertEquals("", translation);
	}
	
	@Test
	public void testTranslateSingleLetterSentence() throws Exception {
		Piglatin piglatin = new Piglatin("I");
		String translation = piglatin.translate();
		assertEquals("IAY", translation);
	}
	
	@Test
	public void testTranslateSentence() throws Exception {
		Piglatin piglatin = new Piglatin("a yellow bird");
		String translation = piglatin.translate();
		assertEquals("aay ellowyay irdbay", translation);
	}
	
	/*
	 * Handling Consonant Clusters
	 * 
	 * */
	
	@Test
	public void testHandingEmptyConsonantCluster() throws Exception {
		Piglatin piglatin = new Piglatin("ramingo cho");
		String translation = piglatin.translate();
		assertEquals("amingoray ochay", translation);
	}
	
	@Test
	public void testHandingTwoLettersConsonantCluster() throws Exception {
		Piglatin piglatin = new Piglatin("thoroughly");
		String translation = piglatin.translate();
		assertEquals("oroughlythay", translation);
	}
	
	@Test
	public void testHandingMultipleLettersConsonantCluster() throws Exception {
		Piglatin piglatin = new Piglatin("schtschurowskia");
		String translation = piglatin.translate();
		assertEquals("urowskiaschtschay", translation);
	}
	
	/*
	 * Handling vowel cluster XR
	 * 
	 * */
	
	@Test
	public void testHandlingVowelClusterXR() throws Exception {
		Piglatin piglatin = new Piglatin("xremote");
		String translation = piglatin.translate();
		assertEquals("xremoteay", translation);
	}
	
	/*
	 * Upper-case input
	 * 
	 */

	@Test
	public void testUppercaseSentence() throws Exception {
		Piglatin piglatin = new Piglatin("SCREAM");
		String translation = piglatin.translate();
		assertEquals("EAMSCRAY", translation);
	}
	
	/*
	 * Title-case input
	 * 
	 * */
	
	@Test
	public void testTitlecaseSentence() throws Exception {
		Piglatin piglatin = new Piglatin("Robert");
		String translation = piglatin.translate();
		assertEquals("Obertray", translation);
	}
	
	/*
	 * Invalid Upper-case
	 * 
	 * */
	
	@Test(expected=InvalidPhraseException.class)
	public void testInvalidUppercase() throws Exception {
		new Piglatin("ChIlDrEn");
	}
}

