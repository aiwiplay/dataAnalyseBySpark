package com.zb.ac.trie;

import java.io.Serializable;

public class KeyWord implements Comparable<KeyWord> ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 68203578452602062L;
	private String keyword;
	private String labkey;
	public String getKeyword() {
		return keyword;
	}
	public String getLabkey() {
		return labkey;
	}
	
	
	public KeyWord(String keyword, String labkey)
	{
		this.keyword = keyword;
		this.labkey = labkey;
	}
	public int compareTo(KeyWord arg0) {
		return keyword.compareTo(arg0.getKeyword());
	}
	
	public String toString()
	{
		return this.getKeyword();
	}
}
