package mod.quizbot.me;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Commands extends CommandBase {

    private final QuizBot bot;

    public Commands(QuizBot b) {
        bot = b;
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public String getCommandName() {
        return "quizbot";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/quizbot";
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 3) {
            showSyntaxError(sender);
            return;
        }

        try {
            switch (args[0].toLowerCase()) {
                case (" "):
                    bot.setEnabled(!bot.isEnabled());
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "BOT: " + (bot.isEnabled() ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
                    break;
                case ("help"):
                    try {
                        switch (args[1]) {
                            case ("calculator"):
                                calculatorHelp(sender);
                                break;
                            case ("commands"):
                                commandList(sender);
                                break;
                            default:
                                showHelpMessage(sender);
                                break;
                        }
                    } catch (Exception e) {
                        showHelpMessage(sender);
                    }
                    break;
                case ("pit"):
                    try {
                        if(args[1]!=null && args[1]!=""){
                            bot.setDelay(Math.abs(Double.parseDouble(args[1])));
                            showMessage(EnumChatFormatting.LIGHT_PURPLE + "Delay set to: " +  EnumChatFormatting.AQUA + Math.abs(Double.parseDouble(args[1])),sender);
                        }else{
                            bot.setQuickmaffs(!bot.isQuickmaffs());
                            showMessage(EnumChatFormatting.LIGHT_PURPLE + "Hypixel Auto Quick Maths: " + (bot.isQuickmaffs() ? EnumChatFormatting.AQUA + "Enabled" : EnumChatFormatting.RED + "Disabled"), sender);
                        }
                    } catch (Exception e) {
                        bot.setQuickmaffs(!bot.isQuickmaffs());
                        showMessage(EnumChatFormatting.LIGHT_PURPLE + "Hypixel Auto Quick Maths: " + (bot.isQuickmaffs() ? EnumChatFormatting.AQUA + "Enabled" : EnumChatFormatting.RED + "Disabled"), sender);
                    }
                    break;
                case ("stats"):
                    showMessage(EnumChatFormatting.RED + "Hypixel: " + bot.isPickle() + " Is Enabled:" + bot.isEnabled(), sender);
                    break;
                case ("admin"):
                    bot.setAdmin(!bot.isAdmin());
                    break;
                default:
                    showSyntaxError(sender);
                    break;
            }
        } catch (Exception e) {
            bot.setEnabled(!bot.isEnabled());
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "BOT: " + (bot.isEnabled() ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
        }
    }

    private void showMessage(String message, ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText(message));
    }

    private void showHelpMessage(ICommandSender sender) {
        showMessage(EnumChatFormatting.LIGHT_PURPLE + "QuizBot " + EnumChatFormatting.GOLD + "is still under development so expect some bugs.", sender);
        showMessage(EnumChatFormatting.GOLD + "The calculator is case sensitive and is still under development.", sender);
        showMessage(EnumChatFormatting.GOLD + "Type: " + EnumChatFormatting.BLUE + "/quizbot help calculator " + EnumChatFormatting.GOLD + "for further assistance with the calculator.", sender);
        showMessage(EnumChatFormatting.GOLD + "Type: " + EnumChatFormatting.BLUE + "/quizbot help commands " + EnumChatFormatting.GOLD + "to see a list of commands.", sender);
    }

    private void calculatorHelp(ICommandSender sender) {
        showMessage(EnumChatFormatting.YELLOW + "Type: " + EnumChatFormatting.BLUE + "'calculate:<equation>'" + EnumChatFormatting.YELLOW + " to evaluate an equation.", sender);
        showMessage(EnumChatFormatting.YELLOW + "Constants are case-sensitive and are: " + EnumChatFormatting.GREEN + "pi, e, phi", sender);
    }

    private void commandList(ICommandSender sender) {
        showMessage(EnumChatFormatting.LIGHT_PURPLE + "/quizbot " + EnumChatFormatting.AQUA + "to enable or disable the quizbot.", sender);
        showMessage(EnumChatFormatting.LIGHT_PURPLE + "/quizbot pit " + EnumChatFormatting.AQUA + "to enable the auto answer for Quick Maths pit event on Hypixel." + EnumChatFormatting.RED + " USE AT YOUR OWN RISK! (I advise not to use this feature)", sender);
        showMessage(EnumChatFormatting.LIGHT_PURPLE + "/quizbot pit <delay>" + EnumChatFormatting.AQUA + "to set the delay for the quick maths event- can be smaller than one." + EnumChatFormatting.RED + " USE AT YOUR OWN RISK! (I advise not to use this feature)", sender);
    }

    private void showSyntaxError(ICommandSender sender) {
        showMessage(EnumChatFormatting.RED + "Usage: " + getCommandUsage(sender), sender);
    }
}
