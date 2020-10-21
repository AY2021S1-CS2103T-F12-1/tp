package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddressType;
import seedu.address.logic.commands.CdCommand;
import seedu.address.logic.commands.CommandTestUtil;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class CdCommandParserTest {
    private CdCommandParser parser = new CdCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CdCommand.CD_COMMAND_USAGE));
    }
}
