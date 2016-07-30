package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.Administrator;
import finalproject.entity.users.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * CommandFactory return current command where value request value to parameter
 * 'action' equal in one of 3 three Maps
 *
 * @author Andrew
 * @version 1.0  23/07/2016
 */

public class CommandFactory {

     private static final CommandFactory instance = new CommandFactory();

     /**
      *HashMap that include admin and abonent commands
      */

     private Map<String, Command> abstractCommands = new HashMap<>();

     /**
      *HashMap that include admin commands
      */

     private Map<String, Command> adminCommands = new HashMap<>();

     /**
      *HashMap that include abonent commands
      */

     private Map<String, Command> abonentCommands = new HashMap<>();

     {
          abstractCommands.put(LoginCommand.COMMAND, new LoginCommand());
          abstractCommands.put(ExitCommand.COMMAND, new ExitCommand());
          abstractCommands.put(MainCommand.COMMAND, new MainCommand());
          abstractCommands.put(ViewMyAccountCommand.COMMAND, new ViewMyAccountCommand());
          adminCommands.put(RegisterCommand.COMMAND, new RegisterCommand());
          adminCommands.put(ViewAllUsersAdminCommand.COMMAND, new ViewAllUsersAdminCommand());
          adminCommands.put(ViewPaymentsAdminCommand.COMMAND, new ViewPaymentsAdminCommand());
          abonentCommands.put(AbonentPaymentsCommand.COMMAND, new AbonentPaymentsCommand());
          abonentCommands.put(MyDataCommand.COMMAND, new MyDataCommand());
          abonentCommands.put(FillUpBalanceCommand.COMMAND, new FillUpBalanceCommand());
          abonentCommands.put(PayServiceCommand.COMMAND, new PayServiceCommand());
          abonentCommands.put(AddServiceCommand.COMMAND, new AddServiceCommand());
          abonentCommands.put(RemoveServiceCommand.COMMAND, new RemoveServiceCommand());
          adminCommands.put(BlockUserCommand.COMMAND, new BlockUserCommand());
          abstractCommands.put(ChangeLanguageCommand.COMMAND, new ChangeLanguageCommand());
     }

     private CommandFactory() {

     }

     public static CommandFactory getInstance() {
          return instance;
     }

     /**
      * Method prepares to get from controller
      *
      * @param wrapper
      *            the wrapper of HttpServletRequest object that contains the client's
      *            request
      * @return command to parameter action
      */

     public Command getCommand(IRequestWrapper wrapper) {
          HttpSession session = wrapper.getSession();
          User user = (User) session.getAttribute("user");
          String action = wrapper.getParameter("action");
          Command currentCommand = null;

          if (user != null && user instanceof Administrator) {
               currentCommand = adminCommands.get(action);
          }

          if (user != null && user instanceof Abonent) {
               currentCommand = abonentCommands.get(action);
          }

          if (currentCommand == null) {
               currentCommand = abstractCommands.get(action);
          }

          if (currentCommand == null) {
               currentCommand = new ErrorCommand();
          }

          return currentCommand;
     }

}
