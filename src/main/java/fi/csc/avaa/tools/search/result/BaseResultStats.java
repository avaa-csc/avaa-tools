/**
 * 
 */
package fi.csc.avaa.tools.search.result;

import java.util.Collection;

/**
 * @author jmlehtin
 *
 */
public abstract class BaseResultStats<T> {
	
	protected Collection<T> resultList;
	protected int totalResultAmount = 0;
	
	public BaseResultStats(Collection<T> resultList) {
		if(resultList != null && resultList.size() > 0) {
			this.resultList = resultList;
			totalResultAmount = this.resultList.size();
		}
	}
	
	protected abstract void init();

	public abstract void populateStats(boolean... whatToPopulate);

	public int getTotalResultAmount() {
		return totalResultAmount;
	}

	public Collection<T> getResultList() {
		return resultList;
	}
	
}
