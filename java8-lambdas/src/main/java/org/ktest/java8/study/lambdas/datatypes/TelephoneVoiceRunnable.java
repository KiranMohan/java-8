/**
 * file       : SexyVoice.java
 * author     : Kiran Mohan
 * created on : 22-Nov-2014
 */
package org.ktest.java8.study.lambdas.datatypes;

/**
 * @author Kiran Mohan
 *
 */
public class TelephoneVoiceRunnable implements Runnable {
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("The number you are dialing does not exist");
	}

}
