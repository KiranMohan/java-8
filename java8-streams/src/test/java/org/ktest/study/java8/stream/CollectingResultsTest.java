package org.ktest.study.java8.stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class CollectingResultsTest {
    
    @BeforeClass
    public static void prepareWordList() {
        // 1. filter
        try (Stream<String> lines = Files.lines(Paths.get("../alice.txt"))) {
            
            wordList = lines.flatMap(t -> Stream.of(t.split("\\P{L}")))
                            .filter(w -> w.length() > 0)
                            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void collectAll(){
        
        // iterator
        Iterator<String> itr = wordList.stream().iterator(); 
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
        
        // collect to Set
        Set<String> wordSet = wordList.stream().collect(Collectors.toSet());
        System.out.println("Set " + wordSet);

        // collect to a specific Set Implementation
        HashSet<String> wordHashSet = wordList.stream().collect(Collectors.toCollection(HashSet<String>::new));
        System.out.println("HashSet " + wordHashSet);

        TreeSet<String> wordTreeSet = wordList.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println("TreeSet " + wordTreeSet);

    }
    
    @Test
    public void joiningStrings() {
        
        List<Integer> ints = Stream.iterate(1, x -> ++x)
                .limit(10)
                .collect(Collectors.toList());
        
        System.out.println("--- joining to a single string ---");

        System.out.println("Joining to single string with delimiter : "
                + ints.stream().map(x -> x.toString()).collect(Collectors.joining(":")));
        
        System.out.println("Joining to single string with delimiter, prefix, suffix : "
                + ints.stream().map(x -> x.toString()).collect(Collectors.joining(":", "[", "]")));
        
        System.out.println("--- ---");
    }
    
    @Test
    public void summaryStatistics() {
        // Summary Statistics of length of words in alice.txt 
        System.out.println("---  ---");
        IntSummaryStatistics stats = wordList.stream()
                .collect(Collectors.summarizingInt(String::length));
                                  //summarizingLong
                                  //summarizingDouble
        
        System.out.println("sum     : " + stats.getSum());
        System.out.println("average : " + stats.getAverage());
        System.out.println("Max     : " + stats.getMax());
        System.out.println("Min     : " + stats.getMin());
        System.out.println("count   : " + stats.getCount());
        System.out.println("--- ---");
    }

    @Test
	public void collectToMap() {

		// Collecting to Map
		System.out.println("--- Collecting to Map ---");
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(1, "P1"));
		persons.add(new Person(2, "P2"));
		persons.add(new Person(3, "P3"));
		persons.add(new Person(4, "P4"));

		// key   = id, 
		// value = name
		Map<Integer, String> idNameMap = persons.stream()
		        .collect(Collectors.toMap(Person::getId, Person::getName));
		assertThat(idNameMap.get(4), is("P4"));
		
		// key   = id, 
        // value = Person
		// example for Function.identity()
        Map<Integer, Person> idPersonMap = persons.stream()
                .collect(Collectors.toMap(
                                          Person::getId, 
                                          Function.identity()   // same as (x) -> { return x; }
                                         )
                         );
        assertThat(idPersonMap.get(4).getName(), is("P4"));
        

		// key   = language in us_en locale, 
		// value = language in localized name 
        // example for resolving conflict if key is already in map
		Map<String, String> localNameOfLanguageMap = 
		        Stream.of(Locale.getAvailableLocales())
		              .collect(
		                       Collectors.toMap(
		                               Locale::getDisplayLanguage, 
		                               l -> l.getDisplayLanguage(l), 
		                               (oldVal, newVal) ->  oldVal) // resolve conflict if key 
		                                                            // is already in map.
		                      );
		//System.out.println("localNameOfLanguage" + localNameOfLanguageMap);
		System.out.println("Hindi " + localNameOfLanguageMap.get("Hindi"));
		assertThat(localNameOfLanguageMap.get("Hindi"), is("हिंदी"));
		assertThat(localNameOfLanguageMap.get("French"),is("français"));
		assertThat(localNameOfLanguageMap.get("German"),is("Deutsch"));

		// key   = country 
		// value = languages spoken
		// complicated. Easier way to do this in GroupingAndPartitioningTest.java
		Map<String, Set<String>> countryLanguagesMap = Stream.of(Locale.getAvailableLocales()).collect(
		        Collectors.toMap(
		        		Locale::getDisplayCountry, 
		        		t -> { Set<String> s = new HashSet<String>();
		        			s.add(t.getDisplayLanguage());
		        			return s;
		        		}, 
		        		(t, u) -> {	
		        			t.addAll(u);
						    return t;
						})
		        );
		
		System.out.println("countryLanguagesMap : " + countryLanguagesMap);

		System.out.println("--- ---");

	}
	
    private static List<String> wordList;

}

class Person {
	private int Id;
	private String name;

	public Person(int id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [Id=" + Id + ", name=" + name + "]";
	}

}