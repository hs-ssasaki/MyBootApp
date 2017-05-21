package com.tuyano.springboot;

import java.util.Date;

import javax.persistence.Id;

/*
 * 本実行前に、ローカルにMongoDBを用意する。
 * (1) インストール
 * # brew install mongodb
 * (2) Mongodbサービススタート
 * # brew services start mongodb
 * (3) 本プログラム実行
 * (4) データが格納されていることの確認
 * # mongo
 * #> show dbs ※デフォルトでtestというDBが使われるよう
 * #> show collections; ※エンティティ名がコレクションになっている（myDataMongo)
 * #> db.myDataMongo.find(); ※データ内容を確認できる
 */
/*
 * MongoDb用のエンティティクラスを作る。
 * @Entityはつけない。
 * Getterしかない。
 * 他アノテーションも@Idしかない。
 */
public class MyDataMongo {
	@Id
	private String id;
	
	private String name;
	private String memo;
	private Date date;
	
	public MyDataMongo(String name, String memo) {
		super();
		this.name = name;
		this.memo = memo;
		this.date = new Date();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMemo() {
		return memo;
	}

	public Date getDate() {
		return date;
	}

}
