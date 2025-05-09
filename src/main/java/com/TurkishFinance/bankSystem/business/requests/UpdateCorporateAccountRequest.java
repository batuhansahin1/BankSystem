package com.TurkishFinance.bankSystem.business.requests;

import lombok.Data;

//buna yapılan isteğin kim tarafından yapılacağına bakmak lazım
//frontEndden mi istek gelicek yoksa biz transaction request yaptıktan sonra banka olarak totalMoney'i mi
//güncelleyeceğiz
//çözüm olarak biz birine para transferi gerçekleştirdiğimizde  zaten biz back-end olarak 
//transaction request yapıyoruz merkez bankasına oradan para transferi başarıyla gerçekleşti bildirimi gelince
//update request başlatmamız lazım kendi back-end'imizden kendi bankamızın apisine update request atıcaz
//aslında bunu banka gerçekleştiriyor bir nevi front'endde başlayan bir işlem burada bitmiş oldu
//ya da bana döndrülen cevabı ben front'ende gönderirirm o api request yapar api requeste gerek yok kullanıcı
//zaten benim veritabanımda direkt  onu çekip totalMoney'i güncelleyip veritabanıa kaydedebilirim
//id aynı olunca update request oluyor zaten üsttekilerin hepsi overthinking
//bu class benim bankamdaki bir hesaba para geldiğinde çalıştırılması gereken bir dışarıdan
//api request yapılması lazım class bu yüzden bunun içine
//accountNumber ve amount ekleyeceğim hesabın pasif olu olmadığını da businessRules ile kontrol ederim
@Data
public class UpdateCorporateAccountRequest {

	private String accountNumber;
	private long recievedAmount;
	

}
