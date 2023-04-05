package seedu.parser;


import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.caloriecommands.CaloriesCommand;
import seedu.commands.workoutcommands.HelpWorkoutCommand;
import seedu.exceptions.InvalidSyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandName>\\S+)(?<arguments>" +
            ".*)");
    private boolean isDayEntered;

    public Command processCommand(String userInput) throws InvalidSyntaxException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new InvalidSyntaxException("user input");
        }

        String commandName = matcher.group("commandName");
        String arguments = matcher.group("arguments");

        switch (commandName.toLowerCase()) {
        case "/wday":
            Command.setIsDayEntered(true);
            Command.setIsWorkoutEntered(false);
            return CheckInputs.processDay(arguments);
        case "/wstart":
            Command.setIsWorkoutEntered(true);
            return CheckInputs.processStart(arguments);
        case "/wadd":
            return CheckInputs.processAdd(arguments);
        case "/wdelete":
            return CheckInputs.processDelete(arguments);
        case "/wlist":
            return CheckInputs.processList(arguments);
        case "/wview":
            return CheckInputs.processView(arguments);
        case "/wcount":
            return CheckInputs.processSetsRepsCount(arguments);
        case "/cday":
            CaloriesCommand.setDateEntered(true);
            return CheckCaloriesInput.processDayCalories(arguments);
        case "/cadd":
            return CheckCaloriesInput.processAddCalories(arguments);
        case "/clist": //list the total daily calories consumption
         //   return CheckCaloriesInput.processViewCalories(arguments);
        case "/cview": // list all the food calories that been entered for a day
        case "/cdelete": //delete calories for a specific day for one food follow /cdelete date food name
        case "/exit":
            return new ExitCommand();
        case "/help":
            return new HelpWorkoutCommand();
        default:
            return new InvalidCommand(commandName);
        }
    }
}

