package Client;

import UserAccount.Account;

public interface IFunctionOfClient {
    void showYourInformationCMND(Account account);
    void updateDataCMNDWithClient(Account account);
    void editPassworkOfAccount(Account account);
    void forgotPassworkOfClient(long numberCMND);
}
