package com.tuyano.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GenerationType;


@Entity
@Table(name = "msgdata")
public class MsgData {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	@NotNull
	private long id;

	@Column
	private String title;

	@Column(nullable=false)
	@NotEmpty
	private String message;

	// MsgData（メッセージ）は、MyData（送信者）と多対一の関係なので、@ManyToOneをつける。
	@ManyToOne
	private MyData myData;
	
	// コンストラクタで、myDataプロパティの値を生成しておく。
	public MsgData() {
		super();
		myData = new MyData();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MyData getMyData() {
		return myData;
	}

	public void setMyData(MyData myData) {
		this.myData = myData;
	}
}
