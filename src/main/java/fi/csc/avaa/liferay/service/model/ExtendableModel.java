/**
 * 
 */
package fi.csc.avaa.liferay.service.model;

/**
 * To be used when liferay's service builder generated models are not enough to describe the
 * relations between database entities.
 * 
 * @author jmlehtin
 *
 */
@FunctionalInterface
public interface ExtendableModel<T extends com.liferay.portal.model.PersistedModel> {

	public void extend(T originalModel) throws ModelExtendError;
}
