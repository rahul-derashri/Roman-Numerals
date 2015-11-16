package com.roman_numerals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 * @author RahulDerashri
 * This class is used for adding Roman Numerals
 *
 */
public class RomanNumeralsManipulation {

	// Holding values such as 1 -> I
	public static Map<Integer, Character> numeralsMap = new HashMap<Integer, Character>();

	// Holding values of all numerals (7 elements)
	private static RomanNumerals[] numeralsArray = new RomanNumerals[7];
	
	// Map to support numeralsArray (I -> index)
	public static Map<Character, Integer> numeralsIndexMap = new HashMap<Character, Integer>();
	
	// Map containing 6 possible subtractive forms (Large form -> small form)
	private static Map<String, String> substractiveFormsLS = new LinkedHashMap<String, String>();
	
	// Map containing 6 possible subtractive forms (Small form -> Large form)
	private static Map<String, String> substractiveFormsSL = new LinkedHashMap<String, String>();
	
	// Static block to initial all the maps and arrays for basic information
	
	static{
		RomanNumerals numeral = new RomanNumerals(1 , 'I');
		int counter = 0;
		numeralsArray[counter] = numeral;
		numeralsIndexMap.put(numeral.getSymbol(), counter);
		numeralsMap.put(1, 'I');
		
		numeral = new RomanNumerals(5 , 'V');
		counter++;
		numeralsArray[counter] = numeral;
		numeralsIndexMap.put(numeral.getSymbol(), counter);
		numeralsMap.put(5, 'V');
		
		numeral = new RomanNumerals(10 , 'X');
		counter++;
		numeralsArray[counter] = numeral;
		numeralsIndexMap.put(numeral.getSymbol(), counter);
		numeralsMap.put(10, 'X');
		
		numeral = new RomanNumerals(50 , 'L');
		counter++;
		numeralsArray[counter] = numeral;
		numeralsIndexMap.put(numeral.getSymbol(), counter);
		numeralsMap.put(50, 'L');
		
		numeral = new RomanNumerals(100 , 'C');
		counter++;
		numeralsArray[counter] = numeral;
		numeralsIndexMap.put(numeral.getSymbol(), counter);
		numeralsMap.put(100, 'C');
		
		numeral = new RomanNumerals(500 , 'D');
		counter++;
		numeralsArray[counter] = numeral;
		numeralsIndexMap.put(numeral.getSymbol(), counter);
		numeralsMap.put(500, 'D');
		
		numeral = new RomanNumerals(1000 , 'M');
		counter++;
		numeralsArray[counter] = numeral;
		numeralsIndexMap.put(numeral.getSymbol(), counter);
		numeralsMap.put(1000, 'M');
		
		
		substractiveFormsLS.put("VIIII", "IX");
		substractiveFormsLS.put("IIII", "IV");

		substractiveFormsLS.put("LXXXX", "XC");
		substractiveFormsLS.put("XXXX", "XL");
		
		substractiveFormsLS.put("DCCCC", "CM");
		substractiveFormsLS.put("CCCC", "CD");
		
		substractiveFormsSL.put("IX", "VIIII");
		substractiveFormsSL.put("IV", "IIII");

		substractiveFormsSL.put("XC", "LXXXX");
		substractiveFormsSL.put("XL", "XXXX");
		
		substractiveFormsSL.put("CM", "DCCCC");
		substractiveFormsSL.put("CD", "CCCC");
		
		
	}
	
	
	
	/**
	 * This method add two Roman numerals
	 * @param first: First Roman numeral
	 * @param second: Second Roman numeral
	 * @return result containing addition of above given two Roman numerals in String format
	 */
	public static String add(String first, String second){
		//System.out.println("Uncompact form of "+first+" :"+Uncompact(first));
		//System.out.println("Uncompact form of "+second+" :"+Uncompact(second));
		System.out.println("Addition of "+first+" and "+second);
		StringBuilder unCompactForm = Uncompact(first).append(Uncompact(second));
		//System.out.println("Uncompact concatenated: "+unCompactForm.toString());
		Character[] arr = getCharArray(unCompactForm);
		Arrays.sort(arr, new RomanNumeralsComparator());
		StringBuilder builder = groupNumerals(arr);
		//System.out.println("groupNumerals: "+builder.toString());
		
		return substituteSub(builder.reverse());
	}
	
	
	/**
	 * This method uncompact the Roman numeral by converting all subtractive forms to there
	 * regular additive forms
	 * @param num: Roman numeral to be uncompacted
	 * @return Uncompact form of num
	 */
	private static StringBuilder Uncompact(String num){
		String input = num;
		
		for( String key : substractiveFormsSL.keySet() ){
			if( input.contains(key) ){
				input = input.replace(key, substractiveFormsSL.get(key));
			}
		}
		
		return new StringBuilder(input);
	}
	
	
	/**
	 * This method uncompact the Roman numeral by converting all subtractive forms to there
	 * regular additive forms
	 * @param num: Roman numeral to be uncompacted
	 * @return Uncompact form of num
	 */
	/*
	private static StringBuilder Uncompact(String num){
		char[] chArr = num.toCharArray();
		StringBuilder result = new StringBuilder();
		int counter = 0;
		for( counter = 0; counter < chArr.length-1; counter++ ){
			if( numeralsIndexMap.get(chArr[counter]) < numeralsIndexMap.get(chArr[counter+1]) ){
				result.append(getUncompactForm(chArr[counter] , chArr[counter+1]));
				counter = counter + 1;
			}
			else{
				result.append(chArr[counter]);
			}
		}
		//System.out.println(counter);
		if( counter == chArr.length-1 ){
			result.append(chArr[counter]);
		}
		
		return result;
	}
	
	private static StringBuilder getUncompactForm(char ch1, char ch2){
		StringBuilder result = new StringBuilder();
		int start = numeralsIndexMap.get(ch2)-1;
		int value = numeralsArray[numeralsIndexMap.get(ch2)].getValue() - numeralsArray[numeralsIndexMap.get(ch1)].getValue();
	
		RomanNumerals curNumeral = null;
		while( value > 0 ){
			curNumeral = numeralsArray[start];
			if( value < curNumeral.getValue() ){
				start--;
				continue;
			}
			
			result.append(curNumeral.getSymbol());
			value -= curNumeral.getValue();
		}
		
		return result;
	}
	*/
	
	
	/**
	 * This method group together similar RomanNumerals and replace with big values
	 * @param num: Roman numeral
	 * @return Compact form just based on additive representation
	 */
	private static StringBuilder groupNumerals(Character[] arr){
		StringBuilder input = new StringBuilder(getString(arr));
		StringBuilder result = new StringBuilder();
		int count = 1;
		boolean flag = true;
		int counter = 0;
		while(flag){
			flag = false;
			for( counter = 0; counter < input.length()-1; counter++ ){
				char ch = input.charAt(counter);
				if( ch == input.charAt(counter+1) ){
					count++;
					int tempValue = count* numeralsArray[numeralsIndexMap.get(ch)].value;
					if( numeralsMap.containsKey(tempValue) ){
						counter = counter + 2;
						while( counter < input.length() && ch == input.charAt(counter) ){
							result.append(input.charAt(counter));
							counter++;
						}
						result.append(numeralsMap.get(tempValue));
						if( counter < input.length() )
							result.append(input.substring(counter));//
						
						flag = true;
						break;
					}
					else if( counter == input.length()-1 ){
						System.out.println("OK");
					}
				}
				else{
					while(count > 0){
						result.append(ch);
						count--;
					}
					count = 1;
				}
				
			}
			
			if( flag ){
				input = result;
				result = new StringBuilder();
				count = 1;
				//System.out.println(input);
			}
		}
		
		if( counter < input.length()){
			while( count > 0 ){
				result.append(input.charAt(counter));
				count--;
			}
		}
		
		return result;
	}
	
	
	/**
	 * This method find and replace subtractive forms
	 * @param num: Roman numeral
	 * @return 
	 */
	public static String substituteSub(StringBuilder str){
		String input = str.toString();
		
		for( String key : substractiveFormsLS.keySet() ){
			if( input.contains(key) ){
				input = input.replace(key, substractiveFormsLS.get(key));
			}
		}
		
		return input;
	}
	
	
	private static Character[] getCharArray(StringBuilder str){
		Character[] arr = new Character[str.length()];
		int counter = 0;
		while( counter < arr.length ){
			arr[counter] = str.charAt(counter);
			counter++;
		}
		
		return arr;
	}
	
	private static StringBuilder getString(Character[] chArr){
		StringBuilder result = new StringBuilder();
		
		for( char ch : chArr ){
			result.append(ch);
		}
		
		return result;
	}
	
	
	
	public static int convertToNumeric(String str){
		StringBuilder builder = Uncompact(str);
		
		int value = 0;
		
		for( int counter = 0; counter < builder.length(); counter++ ){
			value += numeralsArray[numeralsIndexMap.get(builder.charAt(counter))].value;
		}
		
		System.out.println("Numeric form of "+str+" :"+value);
		return value;
	}
	
	
	public static String convertToRoman(int value){
		StringBuilder result = new StringBuilder();
		int inputValue = value;
		int counter = numeralsArray.length-1;
		while( inputValue > 0 ){
			int rs = inputValue/numeralsArray[counter].value;
			
			if( rs > 0 ){
				inputValue = inputValue%numeralsArray[counter].value;
			}
			
			while( rs > 0 ){
				result.append(numeralsArray[counter].symbol);
				rs--;
			}
			counter--;
		}
		
		System.out.println("Roman form of "+value+" :"+substituteSub(result));
		return substituteSub(result);
	}
	
	public static void main(String[] args) {
		
		//System.out.println(convertToRoman(2349));
		//System.out.println(convertToNumeric("MMCCCXLIX"));
		
		System.out.println("1");
		System.out.println("Result: "+add("CCCLXIX", "DCCCXLV"));
		
		System.out.println("2");
		System.out.println("Result: "+add("III", "IX"));
		
		System.out.println("3");
		System.out.println("Result: "+add("CLI", "LIX"));
		
		System.out.println("4");
		System.out.println("Result: "+add("XXXVIII", "XXIII"));
		
		System.out.println("5");
		System.out.println("Result: "+add("XXIX", "XIV"));
		
		System.out.println("6");
		System.out.println("Result: "+add("VII", "XVI"));
		
		System.out.println("7");
		System.out.println("Result: "+add("XXXV", "XXXVII"));
		
		System.out.println("8");
		System.out.println("Result: "+add("XXI", "X"));
		
		System.out.println("9");
		System.out.println("Result: "+add("XXXVI", "XXV"));
		
		System.out.println("10");
		System.out.println("Result: "+add("XXII", "XXI"));
		
		System.out.println("11");
		System.out.println("Result: "+add("XXVIII", "XXXIV"));
		
		System.out.println("12");
		System.out.println("Result: "+add("XXXI", "XV"));
		
		System.out.println("13");
		System.out.println("Result: "+add("XXXVII", "XXX"));
		
		System.out.println("14");
		System.out.println("Result: "+add("XXIII", "IV"));
		
		System.out.println("15");
		System.out.println("Result: "+add("XXXVIII", "XXVII"));
		
		System.out.println("16");
		System.out.println("Result: "+add("XXIX", "XIV"));
		
		System.out.println("17");
		System.out.println("Result: "+add("XXVI", "XXIV"));
		
		System.out.println("18");
		System.out.println("Result: "+add("MMMDCCIIII", "MMMDCCIIII"));
		
	}
}
