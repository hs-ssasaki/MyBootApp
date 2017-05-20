package com.tuyano.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tuyano.springboot.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
	/* find + By + カラム名 + 条件 + (引数) という書式になる
	 * findByカラム名And_(x, y)
	 * findByカラム名Or_(x, y)
	 * findByカラム名Between(x, y) 
	 * findByカラム名LessThan(x)
	 * findByカラム名GreaterThan(x) 
	 * findByカラム名IsNull
	 * findByカラム名NotNull
	 * findByカラム名Like(x)
	 * findByカラム名NotLike(x)
	 * findByカラム名OrderByカラム名Asc(x, y)
	 * findByカラム名Not(x) 
	 * findByカラム名In(Collection<>) 
	 * findByカラム名NotIn(Collection<>) */
	public MyData findById(long id);
	
	// @Query でJPQLを指定すると、そのメソッドが呼ばれたときにこの、JPQLが実行される
	// ※インターフェースの実装はいらない
	@Query("SELECT d FROM MyData d ORDER BY d.name")
	public List<MyData> findAllOrderByName();
	
	// @Query で指定するJPQLにも引数が使える
	// メソッドの対応付ける引数に、@Param(JPQLの変数名) をつけて、対応付ければよい。
	@Query("from MyData where age > :min and age < :max")
	public List<MyData> findByAge(@Param("min") int min, @Param("max") int max);
}
