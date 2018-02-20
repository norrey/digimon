package com.norrey.puzzle.io.exception;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 7:51:16 PM
 */
public class InvalidInputException extends RuntimeException {

    private static final long serialVersionUID = -5662414373601135562L;

    public InvalidInputException(final String message) {
        super(message);
    }

    public InvalidInputException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
