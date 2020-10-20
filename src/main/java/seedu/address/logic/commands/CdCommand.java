package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHILD_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PATH;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.currentpath.CurrentPath;
import seedu.address.model.tag.FileAddress;

import java.io.File;

/**
 * Sets the current path of the file explorer.
 */
public class CdCommand extends Command {

    public static final String COMMAND_WORD = "cd";
    public static final String CD_COMMAND_USAGE = COMMAND_WORD
            + ": Changes the current path of the file explorer\n"
            + "Parameters: "
            + "\n\t(1)" + PREFIX_FILE_ADDRESS + "ABSOLUTE PATH"
            + "\n\t(2)" + PREFIX_CHILD_PATH + "CHILD FOLDER NAME"
            + "\n\t(3)" + PREFIX_PARENT_PATH
            + "\nExamples: "
            + "\n\t(1)" + COMMAND_WORD + " " + PREFIX_FILE_ADDRESS + "F:\\OneDrive\\CS2013T "
            + "\n\t(2)" + COMMAND_WORD + " " + PREFIX_CHILD_PATH + "OneDrive"
            + "\n\t(3)" + COMMAND_WORD + " " + PREFIX_PARENT_PATH;
    public static final String MESSAGE_SUCCESS = "Current path set to '$s'";
    public static final String MESSAGE_PATH_NOT_FOUND = "Cannot find '$s'";
    public static final String MESSAGE_PATH_INVALID = "Cannot set the path to '$s'";
    public static final String MESSAGE_BLANK_PATH = "The path given cannot be blank!";
    public static final String MESSAGE_PARENT_PATH_NOT_BLANK = "../ does not accept any arguments";
    public static final String MESSAGE_UNKNOWN_ADDRESS_TYPE = "The address type being proceed is unknown";

    private AddressType addressType;
    private String addressString;

    /**
     * Creates a CdCommand to change the current path of the file explorer to the given {@code FileAddress}.
     */
    public CdCommand(AddressType addressType, String addressString) {
        this.addressType = addressType;
        this.addressString = addressString;
    }

    /**
     * Sets the current path of the file explorer to the input file address.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if the input file address is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CurrentPath currentPath = model.getCurrentPath();
        String absolutePathString;
        switch (addressType) {
        case ABSOLUTE:
            absolutePathString = addressString;
            break;
        case CHILD:
            absolutePathString = currentPath.getAddress().value + "\\" + addressString;
            break;
        case PARENT:
            absolutePathString = currentPath.getParentAddress();
            break;
        default:
            throw new CommandException(MESSAGE_UNKNOWN_ADDRESS_TYPE);
        }

        File pathToSet = new File(absolutePathString);
        if (!pathToSet.exists()) {
            throw new CommandException(String.format(MESSAGE_PATH_NOT_FOUND, addressString));
        }

        if (!pathToSet.isDirectory()) {
            throw new CommandException(String.format(MESSAGE_PATH_INVALID, absolutePathString));
        }

        FileAddress newPath = new FileAddress(absolutePathString);
        currentPath.setAddress(newPath);

        return new CommandResult(String.format(MESSAGE_SUCCESS, absolutePathString));
    }
}
