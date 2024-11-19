package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QuestionData")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	int ques_id;
	@Column
	String ques_text;
	@Column
	String op1;
	@Column
	String op2;
	@Column
	String op3;
	@Column
	String op4;
	@Column
	int correct_op;
	public int getQues_id() {
		return ques_id;
	}
	public void setQues_id(int ques_id) {
		this.ques_id = ques_id;
	}
	public String getQues_text() {
		return ques_text;
	}
	public void setQues_text(String ques_text) {
		this.ques_text = ques_text;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	public String getOp3() {
		return op3;
	}
	public void setOp3(String op3) {
		this.op3 = op3;
	}
	public String getOp4() {
		return op4;
	}
	public void setOp4(String op4) {
		this.op4 = op4;
	}
	public int getCorrect_op() {
		return correct_op;
	}
	public void setCorrect_op(int correct_op) {
		this.correct_op = correct_op;
	}
	@Override
	public String toString() {
		return "Question [ques_id=" + ques_id + ", ques_text=" + ques_text + ", op1=" + op1 + ", op2=" + op2 + ", op3="
				+ op3 + ", op4=" + op4 + ", correct_op=" + correct_op + "]";
	}
	
}
