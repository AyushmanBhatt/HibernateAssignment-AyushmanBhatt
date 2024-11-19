package service;



import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Admin;
import entity.Question;
import entity.User;



public class userimpl implements userservice{
	Scanner sc=new Scanner(System.in);
	SessionFactory fac = new Configuration().configure("hiber.config.xml")
            .addAnnotatedClass(Admin.class)
            .buildSessionFactory();
	SessionFactory fac1 = new Configuration().configure("hiber.config.xml")
            .addAnnotatedClass(User.class)
            .buildSessionFactory();
	SessionFactory fac2 = new Configuration().configure("hiber.config.xml")
            .addAnnotatedClass(Question.class)
            .buildSessionFactory();
	Session ses;
	boolean isLoggedIn=false;
	User currentUser=null;
	
	public void registerUser(String name,String email,String password) {
		ses=fac1.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			User u = new User();
			u.setEmail(email);
			u.setPassword(password);
			u.setName(name);
			ses.save(u);
			tx.commit();
			System.out.println("User added");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to add user!! Some error Occured");
		}
	}
	
	public boolean userLogin(String email,String password) {
		ses=fac1.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			Query<User> q=ses.createQuery("from User where email = :e and password = :p", User.class);
			q.setParameter("e",email);
            q.setParameter("p",password);
            List <User> li = q.list();
            if(!li.isEmpty())
            {
            	isLoggedIn=true;
            	currentUser=li.get(0);
            }
            else {
            	isLoggedIn=false;
            }
            tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isLoggedIn;
	}
	
	
	
	public void updateUser(String name,String email,String password) {
		ses=fac1.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			int user_id = currentUser.getUser_id();
			User u=ses.find(User.class,user_id);
	        u.setEmail(email);
	        u.setName(name);
	        u.setPassword(password);
	        ses.saveOrUpdate(u);
	        tx.commit();
	        System.out.println("User Updated");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void viewQues() {
		ses=fac2.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			Query<Question> ques=ses.createQuery("from Question");
			List<Question> qlist = ques.list();
			if(!qlist.isEmpty()) {
				for(Question q : qlist) {
					System.out.println(q.toString());
				}
			}
			else {
				System.out.println("No Questions Found");
			}
	    }
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int[] takeExam() {
		ses=fac2.openSession();
		Transaction tx;
		int marks=0;
		int noofques=0;
		try {
			System.out.println("Welcome to the Exam. Answer the following Questions. Don't take help from CHATGPT" );
			tx=ses.beginTransaction();
			Query<Question> ques=ses.createQuery("from Question");
			List<Question> queslist=ques.list();
			if(!queslist.isEmpty()) {
				for(Question q : queslist) {
					noofques++;
					boolean flag=false;
					while(flag!=true) {
						System.out.println(q.getQues_text());
						System.out.println("1. "+q.getOp1());
						System.out.println("2. "+q.getOp2());
						System.out.println("3. "+q.getOp3());
						System.out.println("4. "+q.getOp4());
						System.out.println("Enter Your Answer 1/2/3/4");
						int ch=sc.nextInt();
						sc.nextLine();
						if(ch>=1 && ch<=4) {
							flag=true;
							if(ch==q.getCorrect_op()) {
								marks++;
								System.out.println("Correct Answer");
							}
							else {
								System.out.println("Inorrect Answer");
							}
						}
						else {
							System.out.println("TRY AGAIN !! Enter from given options!!");
						}
					}	
				}
			}
			else {
				System.out.println("No Questions Found");
			}
			tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			ses.close();
		}
		return new int[]{marks, noofques};
		
	}
	 
	
}
