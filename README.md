# Turizm Acente Uygulaması

## Kullanılan Teknolojiler
* Java (Version 21)
* PostgreSQL (Version 16)

## Yüklenecek Kütüphaneler
* postgresql-42.7.3.jar
* flatlaf-3.4.1.jar
* flatlaf-intellij-themes-3.4.1.jar

## Veritabanı Bilgisi

### user Tablosu
 * id -> (bigint, not null, unique)
 * first_name -> (text, not null)
 * last_name -> (text, not null)
 * email -> (text, not null)
 * mobile_phone -> (text, not null)
 * password -> (text, not null)
 * role -> (text, not null)


### Proje Bilgisi

#### Giriş Ekranı

Giriş ekranında giriş yapılabilmesi için `user` veritabanına kayıtlı `email` ve `password` ile giriş yapılabilmektedir. 
`email` ve `password` yazıldıktan sonra `business` katmanında yer alan `UserManager` sınıfında girilen kullanıcın rolü (ADMIN veya PERSONEL) sorgulanmaktadır.
Admin ise AdminView ekranına gitmekte eğer Personel ise PersonelView ekranına gitmektedir.
Aşağıda giriş ekranını ekran görüntüsü verilmiştir.

<img src="C:\Users\atmac\OneDrive\Masaüstü\Patika\turizmAcenteSistemi\screenfolder\1.jpg">



