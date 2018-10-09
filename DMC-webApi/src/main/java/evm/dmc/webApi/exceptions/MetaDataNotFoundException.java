package evm.dmc.webApi.exceptions;

/**
 * Throwed when no MetaData found
 */
public class MetaDataNotFoundException extends NotFoundException {

	/** Defined to avoid problems with serialization */
	private static final long serialVersionUID = -7557190537228887032L;

	public MetaDataNotFoundException(String message) {
		super(message);
	}

	public MetaDataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
