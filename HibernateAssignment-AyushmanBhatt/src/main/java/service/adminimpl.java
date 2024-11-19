package service;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Admin;
import entity.Question;
import entity.User;



public class adminimpl implements adminservice{
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
	boolean AdminisLoggedIn=false;
	Admin currentAdmin=null;
	
	public void registerAdmin(String email,String password,String name) {
		
		Transaction tx;
		try(Session ses=fac.openSession();) {
			tx=ses.beginTransaction();
			Admin ad = new Admin();
			ad.setEmail(email);
			ad.setPassword(password);
			ad.setName(name);
			ses.save(ad);
			tx.commit();
			System.out.println("Admin added");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to register Admin!! An error Occured");
		}
	}
	
	public boolean loginAdmin(String email,String password) {
		ses=fac.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			Query<Admin> q=ses.createQuery("from Admin where email = :e and password = :p", Admin.class);
			q.setParameter("e",email);
            q.setParameter("p",password);
            List <Admin> li = q.list();
            if(!li.isEmpty())
            {
            	AdminisLoggedIn=true;
            	currentAdmin=li.get(0);
            	
            	
            }
            else {
            	AdminisLoggedIn=false;
            }
            tx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return AdminisLoggedIn;
	}
	
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
			System.out.println("Error Occured!! Unable to Register User");
		}
	}
	
	public void updateUser(int user_id,String name,String email,String password) {
		ses=fac1.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			User u=ses.find(User.class,user_id);
			if(u!=null) {
	        	u.setEmail(email);
	        	u.setName(name);
	        	u.setPassword(password);
	        	ses.saveOrUpdate(u);
	        	tx.commit();
	        	System.out.println("User Updated");
	        }
	        else {
	        	System.out.println("No User Found");
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void removeUser(int user_id) {
		ses=fac1.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			User u=ses.find(User.class,user_id);
	        if(u!=null) {
	        	ses.delete(u);
	        	tx.commit();
	        	System.out.println("User Removed");
	        }
	        else {
	        	System.out.println("User Not Found");
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addques(String ques_text,String op1,String op2,String op3,String op4,int cor_op) {
		
		Transaction tx;
		try(Session ses=fac2.openSession()) {
			tx=ses.beginTransaction();
			Question ques = new Question();
			ques.setQues_text(ques_text);
			ques.setOp1(op1);
			ques.setOp2(op2);
			ques.setOp3(op3);
			ques.setOp4(op4);
			ques.setCorrect_op(cor_op);
			ses.save(ques);
			tx.commit();
			System.out.println("Question added");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Failed to add Question!! Some Error Occured");
		}
	}
	
	public void modifyques(int ques_id,String ques_text,String op1,String op2,String op3,String op4,int cor_op) {
		ses=fac2.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			Question q=ses.find(Question.class,ques_id);
			if(q!=null) {
	        	q.setQues_text(ques_text);
	        	q.setOp1(op1);
	        	q.setOp2(op2);
	        	q.setOp3(op3);
	        	q.setOp4(op4);
	        	q.setCorrect_op(cor_op);
	        	ses.saveOrUpdate(q);
	        	tx.commit();
	        	System.out.println("Question Updated");
	        }
	        else {
	        	System.out.println("No Question Found");
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Failed to update Question!! Some Error Occured");
		}
	}
	
	public void removeques(int ques_id) {
		ses=fac2.openSession();
		Transaction tx;
		try {
			tx=ses.beginTransaction();
			Question q =ses.find(Question.class,ques_id);
	        if(q!=null) {
	        	ses.delete(q);
	        	tx.commit();
	        	System.out.println("Question Removed");
	        }
	        else {
	        	System.out.println("Question Not Found");
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
