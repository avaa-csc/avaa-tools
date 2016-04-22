package fi.csc.avaa.vaadin.email;

/**
 * Functional interface to be used as a Java Supplier.
 * 
 * @author jmlehtin
 *
 * @param <T>
 */
@FunctionalInterface
public interface Action<T> {

	public T doAction();
}
