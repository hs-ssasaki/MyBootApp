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
		// 結果は、query.getResultList() や query.getSingleResut() で取得する。
		Query query = entityManager.createQuery("from MyData");
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public MyData findById(long id) {
		// getSingleResult() はクエリから得られるエンティティを一つだけ取り出して返すメソッド
		return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
	}

	@Override
	public List<MyData> findByName(String name) {
		// getResultList() は、クエリから得られたエンティティをリストにして返すメソッド
		return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
	}

	@Override
	public List<MyData> find(String fstr) {
		List<MyData> list = null;
		// @NamedQueryは、entityManager.createNamedQuery(名付けたクエリの名前)で取得できる。
		Query query = entityManager.createNamedQuery("findWithName")
				.setParameter("fname", "%" + fstr + "%");
		list = query.getResultList();
		return list;
	}
}
