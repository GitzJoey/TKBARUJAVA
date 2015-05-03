package com.tkbaru.common;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

public class RandomProvider {
	public RandomProvider() {
		aRandom = new Random();
	}
	
	public RandomProvider(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		
		aRandom = new Random();
	}
	
	public RandomProvider(int alphaNumericLength) {
		this.alphaNumericLength = alphaNumericLength;
	}
	
	private Random aRandom;
	private int lowerBound = 1;
	private int upperBound = 100;
	
	private int alphaNumericLength;
	
	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}
	
	public int getAlphaNumericLength() {
		return alphaNumericLength;
	}

	public void setAlphaNumericLength(int alphaNumericLength) {
		this.alphaNumericLength = alphaNumericLength;
	}

	public int generateRandom() {
	    if (this.lowerBound > this.upperBound) {	
	        throw new IllegalArgumentException("Lowerbound cannot exceed upperbound.");
	    }

	    long range = (long)this.lowerBound - (long)this.upperBound + 1;
	    
	    long fraction = (long)(range * aRandom.nextDouble());

	    int randomNumber =  (int)(fraction + this.lowerBound);
	    
	    return Math.abs(randomNumber);
	}
	
	public String generateRandomInString() {
		return String.valueOf(generateRandom());
	}
	
	public String generateRandomString() {
		return "";
	}
	
	public String generateAlphaNumericRandom() {
		return RandomStringUtils.randomAlphanumeric(this.alphaNumericLength).toUpperCase();
	}	
}
