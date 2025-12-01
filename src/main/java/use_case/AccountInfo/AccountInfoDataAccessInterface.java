package use_case.AccountInfo;

import entity.User;

public interface AccountInfoDataAccessInterface {
    public User get(String identifier);

}
