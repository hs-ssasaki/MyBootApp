package com.tuyano.springboot;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/* 独自のバリデーションを追加するには、アノテーションクラスと、バリデータクラスの定義が必要 
 * バリデータクラスは、ConstraintValidator インターフェースを継承する。
 * ConstraintValidator<アノテーションクラス, 設定される値の型> という指定をする。
 * この場合、String値を入力し、Phoneアノテーションによってバリデーションが指定されるバリデータ PhoneValidator を定義している */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

	// 初期化メソッド。
	// 引数にはアノテーションクラスをとる。ここでアノテーションに関する情報を取得できるので、
	// 必要に応じて初期化処理を行う。
	@Override
	public void initialize(Phone phone) {};
	
	@Override
	// 実際のバリデーション処理。
	// 引数は、バリデーションチェックの対象となる入力された値と、ConstraintValidatorContextインスタンスをとる。
	// 正常と判断したならtrue, 異常ならfalseを返す。
	public boolean isValid(String input, ConstraintValidatorContext cxt) {
		if (input == null) {
			return false;
		}
		// 正規表現でチェック
		return input.matches("[0-9()-]*");
	}

}
