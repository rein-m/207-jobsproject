package interface_adapter.userAccountInfo;

import interface_adapter.ViewModel;

public class AccountInforViewModel extends ViewModel<AccountInfoState> {
        public AccountInforViewModel() {
            super("account info");
            setState(new AccountInfoState());
        }

}
