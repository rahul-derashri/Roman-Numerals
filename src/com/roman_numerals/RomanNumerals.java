package com.roman_numerals;



/**
 * @author RahulDerashri
 * Class representing Roman Numerals
 */
public class RomanNumerals {
	
	int value;
	char symbol;
	
	
	public RomanNumerals() {
	}
	
	public RomanNumerals(int value, char symbol) {
		this.value = value;
		this.symbol = symbol;
	}

	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
}
