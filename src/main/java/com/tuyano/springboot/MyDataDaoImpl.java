package com.tuyano.springboot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
	// シリアライズ処理を実装するクラスには、どの時点のどのクラスによってシリアライズされたのかを示すIDを宣言しておく
	// 必要がある。Serializableインターフェースの実装クラスを実装時に、補完の注意がでるので従えば良い。
	private static final long serialVersionUID = 1L;
	
	// エンティティを操作するのに必要な機能を提供するクラス EntitiManagerのインスタンスを使う
	private EntityManager entityManager;
	
	public MyDataDaoImpl() {
		super();
	}
	
	public MyDataDaoImpl(EntityManager manager) {
		// コントローラで作った、entityManagerを受け取る
		entityManager = manager;
	}
	
	@Override
	public List<MyData> getAll() {
		// JPQLによるクエリを格納する、Queryクラスを使った方法。
		// entityManagerのcreateQuery()メソッドでクエリを生成する。
		Query query = entityManager.createQuery("from MyData");
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;
	}
	

}
