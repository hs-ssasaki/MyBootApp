package com.tuyano.springboot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/*
 * アノテーションを定義する。messageはエラー時に送られるメッセージだが、
 * @interface アノテーション名 {
 * 	キーの型　キーの名前
 * }
 * という記述の仕方。引数なしとthrowsなしのメソッドとしてキーを定義する。
 * 後ろに、default をつけることで、デフォルト値を指定することもできる。
 * メソッドの戻り値として使えるのは、プリミティブ型、String型、Class型、enum定数、アノテーション型、それらの配列、のみ
 * @interface sample {
 * 	int num();
 *  String name() default "default text";
 * }
 * で、
 * @sample(num = 1, name = "aaa")
 * というアノテーションの定義になる
 * 
 * @Targetは、指定できる構文要素を制限するメタアノテーション。
 * @Retentionは、アノテーションの有効範囲を指定するメタアノテーション。
 * @Documentedは、javadocなどのツールでドキュメントを作成した場合に、アノテーションが付いていることをドキュメントに載せることを指定するアノテーション。
 * 
 * @Constraintで、バリデータクラスを指定
 * @ReportAsSingleViolationは、複数のアノテーションのエラーメッセージを個々に返すのではなく、ここで定義する message() をエラーメッセージとして使用することを宣言するアノテーション。
 * 
 * 以下の２つは決まりごと。
 * payload は、チェックにメタデータを付与するメソッド？
 * groups は、チェックのグループ情報を定義するメソッド？
 * */
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
	String message() default "please input a phone number.";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};
	// 引数のあるアノテーションの定義の仕方。
	// メソッドでキーの型とキーの名前を指定する。
	boolean onlyNumber() default false;
}
