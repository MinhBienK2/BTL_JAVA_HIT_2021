package Admin;

public interface IFunctionOfAdmin {
    void insertCMND();
    void removeById();
    void findById(long numberId);
    void filterByName(String filterName);
    void filterByAge(long filterAge,String isCheck);
    void filterByHomeTown(String filterHomeTown);
    void filterExpiresCMND();
    void filterByGender(String filterGender);
    void sortByName(String invariant);
    void sortByAge(String invariant);
    void sortByExpiredYear(String invariant);
    void displayAllCMND();
    void showAllAccountClient();
    void showAllAccountAdmin();
    void createAccountClientToCMND();
    void createAccountAdmin();
    void updateDataCMND(long numberCMND,String works);
    void editPassworkAccount(String filename ,String username,String passwork);
}
