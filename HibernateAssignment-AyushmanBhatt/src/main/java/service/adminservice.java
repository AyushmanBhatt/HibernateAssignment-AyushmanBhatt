package service;



public interface adminservice {
	
    void registerUser(String name,String email,String password);
    void removeUser(int user_id);
    void updateUser(int user_id,String name,String email,String password);
    void registerAdmin(String email,String password,String name);
    boolean loginAdmin(String email,String password);
    void addques(String ques_text,String op1,String op2,String op3,String op4,int cor_op);
    void removeques(int ques_id);
    void modifyques(int ques_id,String ques_text,String op1,String op2,String op3,String op4,int cor_op);
}
