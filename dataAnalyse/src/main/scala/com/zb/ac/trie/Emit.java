package com.zb.ac.trie;

import java.io.Serializable;

import com.zb.ac.interval.Interval;
import com.zb.ac.interval.Intervalable;

public class Emit extends Interval implements Intervalable,Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8769108083586670842L;
	private final String keyword, labkey;

    public Emit(final int start, final int end, final String keyword, final String labkey) {
        super(start, end);
        this.keyword = keyword;
        this.labkey = labkey;
    }
    
    public Emit(final int start, final int end, final String keyword) {
        super(start, end);
        this.keyword = keyword;
        this.labkey = keyword;
    }

    
    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public String toString() {
        return super.toString() + "=" + this.keyword + "|" + this.labkey;
    }

    public String getLabkey()
    {
    	return this.labkey;
    }
}
