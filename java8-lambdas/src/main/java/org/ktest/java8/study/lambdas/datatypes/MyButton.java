package org.ktest.java8.study.lambdas.datatypes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButton {
	private String label;
	private ActionListener listener;

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	public MyButton(String label) {
		this.label = label;
		listener = null;
	}

	public MyButton() {
		this.label = "";
	}
	
	public void addActionListener(ActionListener listener){
		this.listener = listener; 
	}
	
	public void keyPressed(){
		listener.actionPerformed(null);
	}
	public void keyPressed(ActionEvent e){
		listener.actionPerformed(e);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
	    return "Button [label=" + label + "]";
    }
	

}