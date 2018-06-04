package africa.apeiron.batafind.HOME;


import africa.apeiron.batafind.R;

/**
 * Created by iiro on 7.6.2016.
 */
public class TabMessage {
    public static String get(int menuItemId, boolean isReselection) {
        String message = "Content for ";

        switch (menuItemId) {
            case R.id.home:
                message += "home";
                break;
            case R.id.men:
                message += "men";
                break;
            case R.id.ladies:
                message += "nearby";
                break;
            case R.id.kids:
                message += "friends";
                break;
            case R.id.profile:
                message += "food";
                break;
        }

        if (isReselection) {
            message += " WAS RESELECTED! YAY!";
        }

        return message;
    }
}
