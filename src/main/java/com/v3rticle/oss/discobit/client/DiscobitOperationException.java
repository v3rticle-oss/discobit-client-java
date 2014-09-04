package com.v3rticle.oss.discobit.client;

/**
 * General exception thrown on discoBit operations.
 * @author jens@v3rticle.com
 *
 */
public class DiscobitOperationException extends Exception {

	private static final long serialVersionUID = 9126015498759968602L;

	public DiscobitOperationException() {
	}

	public DiscobitOperationException(String message) {
		super(message);
	}

	public DiscobitOperationException(Throwable cause) {
		super(cause);
	}

	public DiscobitOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DiscobitOperationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
