package exceptions;

import evolutionaryAlgorithmComponents.evaluation.permutation.TSP.TSPReader;

/**
 * This exception should be thrown whenever the input file has an extension that is not
 * supported by the {@link TSPReader}.
 */
public class FileFormatNotSupportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5962819491187694985L;

	public FileFormatNotSupportedException() {
		// TODO Auto-generated constructor stub
	}

	public FileFormatNotSupportedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileFormatNotSupportedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public FileFormatNotSupportedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileFormatNotSupportedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
