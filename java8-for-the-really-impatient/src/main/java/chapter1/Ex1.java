package chapter1;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArraySet;

import org.junit.Test;

import common.Helper;

/*
 * 1. Is the comparator code in the Arrays.sort method called in the same thread
 *  as the call to sort or a different thread?
 */
public class Ex1 {

	@Test
	public void test() {
		CopyOnWriteArraySet<Long> threadIds = new CopyOnWriteArraySet<Long>();
		threadIds.add(Thread.currentThread().getId());
		
		Comparator<String> comparator = (s1, s2) -> {
			threadIds.add(Thread.currentThread().getId());
			return s1.compareToIgnoreCase(s2);
		};
		
		String[] names = Helper.getWordsAsArray("src/main/resources/alice.txt");
		Arrays.sort(names, comparator);
		
		assertThat(threadIds.size(), equalTo(1));
		System.out.println(threadIds.toString());
		
		names = Helper.getWordsAsArray("src/main/resources/alice.txt");
		Arrays.parallelSort(names, comparator);
		
		assertThat(threadIds.size(), greaterThan(1));
		System.out.println(threadIds.toString());
		
	}

}
