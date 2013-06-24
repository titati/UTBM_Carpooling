package fr.utbm.carpooling.utils;

/**
 * Represent the result of an asynchronous task.
 * If the task is successful then we'll call taskSuccessful
 * and if the task is not successful then we'll call taskFailed
 */
public interface TaskHandler<E> {
	public void taskSuccessful(E object);
	public void taskFailed();
}
