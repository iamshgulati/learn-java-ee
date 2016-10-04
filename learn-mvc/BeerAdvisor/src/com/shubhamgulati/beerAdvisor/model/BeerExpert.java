package com.shubhamgulati.beerAdvisor.model;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
	public List<String> getBrands(String color) {
		List<String> brands = new ArrayList<String>();
		if(color.equals("amber")) {
			brands.add("Jack Amber");
			brands.add("Red Moose");
		} else {
			brands.add("Jail Pail Ale");
			brands.add("Gout Scout");
		}
		return brands;
	}
}
