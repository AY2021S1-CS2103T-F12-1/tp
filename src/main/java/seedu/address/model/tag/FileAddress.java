package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag's file address in the Hello File.
 * Guarantees: immutable; is valid as declared in {@link #isValidFileAddress(String)}
 */
public class FileAddress {

    public static final String MESSAGE_CONSTRAINTS = "File address can only take a file path!";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "((((?<!\\w)[A-Z,a-z]:)|(\\.{1,2}\\\\))"
            + "([^\b%\\/\\|:\\n\\\"]*))|(\"\\2([^%\\/\\|:\\n\\\"]*)\")|((?<!\\w)(\\.{1,2})?"
            + "(?<!\\/)(\\/((\\\\\\b)|[^ \b%\\|:\\n\\\"\\\\\\/])+)+\\/?)";

    public final String value;

    /**
     * Constructs an {@code FileAddress}.
     *
     * @param address A valid address.
     */
    public FileAddress(String address) {
        requireNonNull(address);
        checkArgument(isValidFileAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidFileAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FileAddress // instanceof handles nulls
                && value.equals(((FileAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
