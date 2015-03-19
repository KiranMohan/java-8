package org.ktest.study.java8.stream;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

import java.util.stream.Stream;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class GroupingAndPartitioningTest {

    // TODO: Refactor
    // TODO: Add Downstream collectors (but produces hard to read code)
   
    @Test
	public void grouping() {
        
		// 1. Grouping 
        // key   = country
        // value = set of all locale available for the country

        //Map<String, List<Locale>> countryLanguagesMap = Stream.of(Locale.getAvailableLocales())
		//       .collect(
		//		        Collectors.groupingBy(Locale::getCountry)); 	// returns value of type List		
		
        Map<String, Set<Locale>> countryLanguagesMap = Stream.of(Locale.getAvailableLocales())
		        .collect(
		                groupingBy(
		                        Locale::getCountry, 
		                        toSet()    // returns value of type Set
		                        )
		                ); 
		
		System.out.println(countryLanguagesMap.get("IN"));
		assertThat(countryLanguagesMap.get("IN"),
		            containsInAnyOrder(new Locale("en", "IN"),new Locale("hi", "IN")));
    }
    
    @Test
    public void partitioning() {
		
		// 2. Partitioning
        // partition all locale to English and other languages 
		Map<Boolean, List<Locale>> englishAndOtherLocales = Stream.of(Locale.getAvailableLocales())
		        .collect(
		                partitioningBy(l -> l.getLanguage().equals("en"))
		                );
		
		List<Locale> englishLocales = englishAndOtherLocales.get(true);
		englishLocales.forEach(System.out::println);
    }
    
    @Test
    public void countOfCollectedItems() {
		
		// 	3. produce count of collected items
        // key   = country
        // value = count of languages available 
		System.out.println("--- produce count of collected items ---");
		Map<String, Long> countryToLocaleCounts = Stream.of(Locale.getAvailableLocales())
		        .collect(
		                groupingBy(
		                        Locale::getCountry, 
		                        counting()
		                        )
		                );
		countryToLocaleCounts.forEach((c, l) -> System.out.println(c + " has " + l + " locale[s]."));
		System.out.println("--- ---");		
	}
    
    @Test
    public void groupingWithMapping() {
        System.out.println("--- groupingWithMapping start ---");
        // key   = country 
        // value = languages spoken
        // Easy way. A tedious way to do it in CollectingResultsTest.java
        Stream<Locale> localeStream = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguagesMap = localeStream.collect(
                                                                            groupingBy(Locale::getDisplayCountry,
                                                                                       mapping(Locale::getDisplayLanguage, 
                                                                                               toSet())
                                                                                       )
                                                                            );
        /*
         * Eclipse bug: Eclipse cannot resolve the return types here correctly
         Map<String, Set<String>> countryLanguagesMap = Stream.of(Locale.getAvailableLocales())
                .collect(
                        groupingBy(Locale::getDisplayCountry,
                           mapping(Locale::getDisplayLanguage, 
                               toSet())
                        )
                );*/
        
        System.out.println("Languages in India " + countryLanguagesMap.get("India"));
        System.out.println("--- groupingWithMapping end ---");
    }


}
