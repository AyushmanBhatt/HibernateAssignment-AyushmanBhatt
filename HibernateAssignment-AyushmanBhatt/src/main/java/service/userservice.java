package service;

public interface userservice {
	void registerUser(String name,String email,String password);
	boolean userLogin(String email,String password);
	void updateUser(String name,String email,String password);
	void viewQues();
	int[] takeExam();
}
