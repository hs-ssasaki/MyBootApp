package com.tuyano.springboot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;

/*
 * Dialectクラスの定義。
 * AbstractDialectを継承、IExpressionEnhancingDialectインターフェースを実装。
 * getPrefix()とgetAdditionalExpressionObjects()の実装内容は定形。
 */
public class MyTLDialect extends AbstractDialect implements IExpressionEnhancingDialect {
	private static final Map<String, Object> EXPRESSION_OBJECTS;

	// objects.put(追加するユーティリティオブジェクト名, 定義したユーティリティクラス）
	// という記述で、登録する。
	static {
		Map<String, Object> objects = new HashMap<>();
		objects.put("myTLHelper", new MyTLUtility());
		EXPRESSION_OBJECTS = Collections.unmodifiableMap(objects);
	}

	public MyTLDialect() {
		super();
	}
	
	@Override
	public String getPrefix() {
		return null;
	}

	@Override
	public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
		return EXPRESSION_OBJECTS;
	}
}
