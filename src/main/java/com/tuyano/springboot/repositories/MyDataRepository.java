package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
