package use_case.AccountInfo;

import entity.User;

import java.sql.SQLException;

public interface AccountInfoDataAccessInterface {
    User get(String username) throws SQLException;
}
