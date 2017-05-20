package com.tuyano.springboot;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/* @NamedQuery というアノテーションで、クエリ文字列に名前をつけられる
 * これを レポジトリではなくエンティティに記述しておくことで、
 * エンティティを操作するのに必要なものをすべてエンティティにまとめられる。
 * @NamedQueryは、エンティティにクエリを持たせるやり方。
 * 一方、@Queryは、レポジトリで使え、レポジトリにクエリを持たせるやり方 */
@Entity
@Table(name="mydata")
@NamedQueries(
	@NamedQuery(
		name="findWithName",
		query="from MyData where name like :fname"
	)
)
public class MyData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String name;

	@Column(length = 200, nullable = true)
	@Email
	private String mail;
	
	@Column(nullable = true)
	@Min(0)
	@Max(200)
	private Integer age;
	
	// 作成したバリデーション @Phone を使う。
	@Column(nullable = true)
	@Phone(onlyNumber=true)
	private String memo;

	// こちらには @OneToMany を指定する。
	// cascade は、伝播させるオペレーションの種類を指定するキー。
	// CascadeType.ALL は、所有者側のエンティティクラスのpersist，remove，merge，およびrefreshの操作を関連先にカスケードする。
	@OneToMany(cascade=CascadeType.ALL)
	@Column(nullable = true)
	private List<MsgData> msgDatas;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<MsgData> getMsgDatas() {
		return msgDatas;
	}

	public void setMsgDatas(List<MsgData> msgDatas) {
		this.msgDatas = msgDatas;
	}
}
