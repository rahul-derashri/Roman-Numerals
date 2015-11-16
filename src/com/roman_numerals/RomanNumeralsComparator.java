package com.roman_numerals;

import java.util.Comparator;


/**
 * @author RahulDerashri
 * Comparator to sort the Roman Numerals based on preferences specific to Roman Numerals
 */
public class RomanNumeralsComparator implements Comparator<Character>{

	@Override
	public int compare(Character o1, Character o2) {
		return RomanNumeralsManipulation.numeralsIndexMap.get(o1).compareTo(RomanNumeralsManipulation.numeralsIndexMap.get(o2));
	}

}
