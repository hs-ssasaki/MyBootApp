package com.tuyano.springboot;

import java.io.Serializable;
import java.util.List;

/*
 * DAOの定義は、
 * まず Serializableインターフェースを継承したインターフェース xxDao を定義する。
 * 次に、このインターフェースの実装クラス xxDaoImpl を実装する。
 */
// ジェネリクスインターフェースについては、
// http://d.hatena.ne.jp/Nagise/20101105/1288938415
// http://www.techscore.com/tech/Java/JavaSE/JavaLanguage/1-2/
// 型引数Tが、Stringとかintとか、具体的な型に置き換わった姿を想像すればいい。List<>が、interface List<E>と定義されているのと一緒。
public interface MyDataDao<T> extends Serializable {
	public List<T> getAll();
	
	public T findById(long id);
	public List<T> findByName(String name);
	public List<T> find(String fstr);
}
