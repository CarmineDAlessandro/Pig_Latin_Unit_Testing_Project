package it.unisa.pig_latin;

public class Piglatin {

	private String phrase;
	
	public Piglatin(String phrase) throws InvalidPhraseException {
		
		if(!isValidPhrase(phrase) && phrase.length() > 0) {
		
			throw new InvalidPhraseException();
		}
		
		this.phrase = phrase;
	}

	private boolean isValidPhrase(String phrase) {
		
		// Format
		if(phrase.contains("  ") ||
				phrase.startsWith(" ") ||
				phrase.endsWith(" ") ||
				!phrase.matches("^[ A-Za-z]+$") )
			return false;
		
		//Upper-case
		/*
		 * ALL UPPERCASE, pass
		 * Title Case, pass
		 * all lower case, pass
		 * 
		 * oNe uppercase, fail
		 * Also ThIs, fail
		 * */
		String[] words = phrase.split("\\s+");
		for(String word: words) {
			char characters [] = word.toCharArray();
			
			if(Character.isUpperCase(characters[0]) ) {
				boolean hasLowerCaseCharacter = false;
				
				for(int i = 1; i < characters.length; i ++) {
					if (Character.isLowerCase(characters[i]) ) 
						hasLowerCaseCharacter = true;
					if (Character.isUpperCase(characters[i]) && hasLowerCaseCharacter)
						return false;
				}
				
			} else {
				for(int i = 1; i < characters.length; i ++)
					if (Character.isUpperCase(characters[i]) ) 
						return false;
			}
		}
		
		return true;
	}
	
	public String translate() {
		
		if (phrase.isEmpty())
			return "";
		
		//Split the phrase into an array of words to perform further analysis
		String[] words = phrase.split("\\s+");
		String newPhrase = "";
		boolean isUppercase = false;
		boolean isTitlecase = false;
		
		
		for(int i = 0; i < words.length; i ++) {
			
			//Analysis
			isTitlecase = titlecaseAnalysis(words[i]);
			if(isTitlecase)
				isUppercase = uppercaseAnalysis(words[i]);
			
			//Turn lower-case ( to avoid side effects) and translation
			String translatedWord = translateWord(words[i].toLowerCase());
			
			//Restore the original state
			translatedWord = restoreProperties (translatedWord, isTitlecase, isUppercase);
			
			//Connect to restore the original phrase
			newPhrase = newPhrase + translatedWord + ' ';
		}
		
		//Trim to remove the last space character
		return newPhrase.trim();
	}
	
	private String restoreProperties(String translatedWord, boolean isTitlecase, boolean isUppercase) {
		if (isUppercase)
			return translatedWord.toUpperCase();
		
		if (isTitlecase) {
			String firstLetter = translatedWord.substring(0, 1);
			firstLetter = firstLetter.toUpperCase();
			
			String theRest = translatedWord.substring(1);
			
			return firstLetter + theRest;
		}
		
		return translatedWord;
	}
	
	private boolean titlecaseAnalysis(String word) {
		if (Character.isUpperCase(word.toCharArray()[0]) )
			return true;
		return false;
	}
	
	private boolean uppercaseAnalysis(String word) {
		char wordCharacters[] = word.toCharArray();
		for (int i = 0; i < wordCharacters.length; i ++) {
			if(Character.isLowerCase(wordCharacters[i]) )
				return false;
		}
		return true;
	}

	/*
	 * Translate a lower-case word
	 * 
	 * */
	private String translateWord(String word) {
		int prefixSize;
		int lettersCluster;
		
		if (startsWithVowelCluster(word) ) {
			
			prefixSize = 0;
			
		} else if ((lettersCluster = consonantClusterSize(word)) > 0) {
			
			prefixSize = lettersCluster;
			
		} else {
			//Starts with vowel
			prefixSize = 0;
			
		}
			
		if (prefixSize > 0) {
			String firstLetters = word.substring(0, prefixSize);
			word = word.substring(prefixSize); 
			word = word + firstLetters;
		}
		
		
		word = word + "ay";
		return word;
	}

	private boolean startsWithVowelCluster(String word) {
		if (word.length() < 2)
			return false;
		
		char firstCharacter = word.toCharArray()[0];
		char secondCharacter = word.toCharArray()[1];
		
		if (firstCharacter == 'x' && secondCharacter == 'r')
			return true;
		
		return false;
	}

	private int consonantClusterSize(String word) {
		
		char characterWord [] = word.toCharArray();
		int consonantCounter = 0;
		
		for (int i = 0; i < characterWord.length; i ++) {
			if (isAConsonant(characterWord[i]) )
				consonantCounter++;
			else
				return consonantCounter;
		}
		
		return consonantCounter;
	}

	private boolean isAConsonant(char startingCharacter) {
		if (startingCharacter == 'a' ||
			startingCharacter == 'e' ||
			startingCharacter == 'i' ||
			startingCharacter == 'o' ||
			startingCharacter == 'u' )
			return false;
		return true;
	}
	
	public String getPhrase() {
		return phrase;
	}
	
}
