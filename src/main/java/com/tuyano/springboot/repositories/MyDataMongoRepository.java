package com.tuyano.springboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuyano.springboot.MyDataMongo;

/* 
 * MongoDB用のレポジトリインターフェースを実装する。
 * 継承するのは、JPARepositoryではなく、MongoRepository。
 * spring-boot-starter-data-mongodbで追加されたライプラリのインターフェース。 
 */
public interface MyDataMongoRepository extends MongoRepository<MyDataMongo, Long>{
	// JPARepositoryと同様に命名規則でメソッドを定義するだけで、実装が作られる。
	public List<MyDataMongo> findByName(String s);
}
