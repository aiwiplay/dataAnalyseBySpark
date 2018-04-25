package com.zb.ac.trie;

import java.io.Serializable;

public class TrieConfig implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8060442275219158085L;

	private boolean allowOverlaps = true;

    private boolean onlyWholeWords = false;

    private boolean caseInsensitive = false;

    public boolean isAllowOverlaps() {
        return allowOverlaps;
    }

    public void setAllowOverlaps(boolean allowOverlaps) {
        this.allowOverlaps = allowOverlaps;
    }

    public boolean isOnlyWholeWords() {
        return onlyWholeWords;
    }

    public void setOnlyWholeWords(boolean onlyWholeWords) {
        this.onlyWholeWords = onlyWholeWords;
    }

    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    public void setCaseInsensitive(boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
    }
}
