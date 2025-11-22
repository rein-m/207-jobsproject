package interface_adapter.accountmanagement;
import use_case.edit_account_info.UserAccountInteractor; // Replace with the actual package and class name
import java.awt.event.*;
import java.util.Objects;

public class AccountManagmentController {
    public AccountManagmentController(String action){
        if (Objects.equals(action,"editUI")) {
            UserAccountInteractor.edit_page();
        }
        else if (Objects.equals(action,"resume")) {
            UserAccountInteractor.add_resume();
        }
    }
}
