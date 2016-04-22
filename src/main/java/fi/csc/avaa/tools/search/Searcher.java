/**
 * 
 */
package fi.csc.avaa.tools.search;

import java.util.Collection;
import java.util.List;

import fi.csc.avaa.tools.StringTools;
import fi.csc.avaa.tools.logging.AvaaLogger;
import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * @author jmlehtin
 *
 */
public abstract class Searcher<T, U extends SearchBean> {

	protected List<T> searchResults;
	protected U searchBean;
	protected AvaaLogger log = new AvaaLogger(Searcher.class.getName());
	
	public Searcher() {
	}
	
	public List<T> getSearchResults() {
		return searchResults;
	}
	
	protected boolean doesMultiLingualFieldContainValue(Translator translator, String searchStr, String fieldValueTranslationKey) {
		if(StringTools.isEmptyOrNull(fieldValueTranslationKey)) {
			return false;
		}
		return translator.localize(fieldValueTranslationKey).toLowerCase().contains(searchStr);
	}
	
	protected boolean doesFieldContainValue(String searchStr, String fieldValue) {
		if(StringTools.isEmptyOrNull(fieldValue)) {
			return false;
		}
		return fieldValue.toLowerCase().contains(searchStr);
	}
	
	public abstract void queryData(Collection<T> searchDataset, U searchBean);
}
