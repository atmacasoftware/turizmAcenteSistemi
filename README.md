# Turizm Acente Uygulaması

## Kullanılan Teknolojiler
* Java (Version 21)
* PostgreSQL (Version 16)

## Yüklenecek Kütüphaneler
* postgresql-42.7.3.jar
* flatlaf-3.4.1.jar
* flatlaf-intellij-themes-3.4.1.jar

### Proje Bilgisi

#### Giriş Ekranı

Giriş ekranında giriş yapılabilmesi için `user` veritabanına kayıtlı `email` ve `password` ile giriş yapılabilmektedir. 
`email` ve `password` yazıldıktan sonra `business` katmanında yer alan `UserManager` sınıfında girilen kullanıcın rolü (ADMIN veya PERSONEL) sorgulanmaktadır.
Admin ise AdminView ekranına gitmekte eğer Personel ise PersonelView ekranına gitmektedir.
Aşağıda giriş ekranına ait ekran görüntüsü verilmiştir.

<img src="..\turizmAcenteSistemi\screenfolder\1.jpg">

### Admin Ekranı
Admin ekranında yeni kullanıcı ekleme, kullanıcı düzenleme, kullanıcı silme ve kullanıcıları role durumuna göre filtreleme işlemi yapılabilmektedir. Aşağıdak admin ekranına ait ekran görüntüleri verilmiştir.

### Personel Ekranı
Personel ekranında Otel yönetimi, oda yönetimi, dönem yönetimi ve rezervasyon işlemleri yapılmaktadır. Aşağıda bu ekranlara ait ekran görüntüleri verilmiştir.

