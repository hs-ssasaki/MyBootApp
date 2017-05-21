package com.tuyano.springboot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.repositories.MyDataRepository;

/* DAO を使って DBアクセスしていたコードから、サービスBeanを使ってアクセスする形に変更する */
@Service
public class MyDataService {
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	MyDataRepository myDataRepository;

	private static final int PAGE_SIZE = 2; // 1ページあたりのエンティティ数
	
	public List<MyData> getAll() {
		@SuppressWarnings("unchecked")
		List<MyData> resultList = (List<MyData>)entityManager.createQuery("from MyData").getResultList();
		return resultList;
	}
	
	public MyData get(int num) {
		return (MyData)entityManager.createQuery("from MyData where id = " + num).getSingleResult();
	}
	
	public List<MyData> find(String fstr) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root).where(builder.equal(root.get("name"), fstr));
		List<MyData> list = null;
		list = (List<MyData>) entityManager.createQuery(query).getResultList();
		return list;
	}

	/*
	 * ページネーションするには、ListのサブクラスであるPageコレクションの要素として
	 * エンティティをViewに返す。
	 * pageコレクションを作るには、
	 * （１）PageRequestインスタンスを作成する （引数には、ページ番号と、１ページあたりのエンティティ数を指定する）
	 * （２）レポジトリのメソッドの引数に、PageRequestインスタンスを渡す
	 * PageはListのサブクラスなので、ViewはListを処理するように実装しておけば表示はできる。
	 * （１）で指定したページ番号に収まるエンティティが返る
	 */
	public Page<MyData> getMyDataInPage(Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
		return myDataRepository.findAll(pageRequest);
	}
}
