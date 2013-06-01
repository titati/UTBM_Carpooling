package fr.utbm.carpooling;

public interface TaskHandler<E> {
		void taskSuccessful(E object);
	    void taskFailed();
}
