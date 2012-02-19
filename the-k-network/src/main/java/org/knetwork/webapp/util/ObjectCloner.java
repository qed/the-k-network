package org.knetwork.webapp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class ObjectCloner, which uses serialization instead of reflection in
 * order to avoid reflection problems.
 */
public class ObjectCloner {
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ObjectCloner.class);

	/**
	 * Instantiates a new object cloner.
	 */
	private ObjectCloner() {
	}

	/**
	 * Deep copy, returns a deep copy of an object
	 * 
	 * @param oldObj
	 *            the old obj
	 * @return the object
	 */
	public static Object deepCopy(Object oldObj) {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Object returnObject = null;

		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(oldObj);
			oos.flush();

			ByteArrayInputStream bin = new ByteArrayInputStream(
					bos.toByteArray());
			ois = new ObjectInputStream(bin);

			returnObject = ois.readObject();
		} catch (IOException e) {
			LOG.warn("Problem reading input/output streams" + e, e);
		} catch (ClassNotFoundException e) {
			LOG.error("Class not found: " + e, e);
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				LOG.info("Problem closing object output stream", e);
			}

			try {
				ois.close();
			} catch (IOException e) {
				LOG.info("Problem closing object input stream", e);
			}
		}

		return returnObject;
	}
}
