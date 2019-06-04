package idu.cs.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import idu.cs.domain.User;

@Entity
@Table(name = "question")
public class QuestionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	// database에서 sequence number, primary key 역할
	@Column(nullable=false, length=20, unique=true)
	private String questionId;
	/*
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_user"))
	*/
	private String user;
	private String title;
	
	@Lob
	private String contents;	
	private LocalDateTime CreateDate;
	
	public QuestionEntity() {}
	public QuestionEntity(String user, String title, String contents) {
		super();
		this.user = user;
		this.title = title;
		this.contents = contents;
		this.CreateDate = LocalDateTime.now();
	}
}