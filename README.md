# kutuphane_yonetim_app
Bu repo, kütüphaneler için ilgili yönetici işlemlerinin kolayca yönetilmesi için oluşturulmuştur.

<div align="center">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/cf88555f-653c-4170-a25e-82653edf4c9d" width="100%">
</div>

## Kütüphane Yönetim Uygulaması

<div align="center">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/1fe7c2d8-8d0a-493b-ad60-bc9c8515d0e9" width="33%">
</div>


Kütüphane yönetimi için oluşturulan uygulama ile kullanıcılar; istedikleri kitapları kendi kitaplıklarına ait veri tabanına ekleyebilecek, kitaplarını istedikleri kişilere teslim edebilecek veya istedikleri kişilerden iade alabilecek, tüm bu işlemleri gerçekleştirmek için ise kitap üzerinde bulunan ID numarasını el ile yazarak veya bu ID numarasını temsil eden QR kod ile işlem yapabilecektir. Tüm kitapların bilgilerini kitap ID numarası ile erişebilecek, kütüphane dışına teslim edilen kitapları takip edebilecek ve tüm kitapları takip edebilecektir. 

Google Play Store : https://play.google.com/store/apps/details?id=com.bogazliyan.ktphaneuygulamas 

Gizlilik Politikası : https://www.denizhansahin.com/p/gizlilik-politikas-kutuphane-yonetim.html 

Mağaza Tanıtım Metni : 
Kütüphanelerinizin yönetimini kolaylaştırın!

Kütüphane Yönetim Uygulaması, kütüphanelerinizin kitap, dergi, gazete ve diğer materyallerini yönetmenize yardımcı olan bir uygulamadır. Uygulama, QR okuma veya kitap ID ile kullanıcıların kitaplarını takip etmesini, kitap teslimi ve iade işleminin yapılmasını, tüm kitapların bilgilerine erişilmesini veya harici kitapların görüntülenmesini sağlar.

Uygulamanın genel özellikleri şunlardır:

Kitapları QR okuma veya kitap kimliği ile takip edin.
Kitap teslimi ve iade işlemini yapın.
Tüm kitapların bilgisine ulaşın.
Kütüphanedeki kitaplar görüntülenir.
Kitap ekleyin.
Kütüphane Yönetim Uygulaması, kütüphanelerinizin yönetimini kolaylaştıracak ve kullanıcılarınızın deneyimini iyileştirecektir.

Uygulamayı hemen yükleme ve kapasite yönetimini kontrol belgesinden alın!

Özellikler:

QR okuma veya kitap kimliği ile kitap takip etme
Kitap teslimi ve iade işlemi yapma
Tüm kitapların bilgisine ulaşma
Kütüphanedeki kitapları yayınlama
Kitap ekleme
Uygulama, yalnızca yetkili kişiler tarafından yapılabilir.

## Kulllanılan Teknolojiler
<div align="center">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/5610f905-e402-4750-b2bf-14b0754ca958" width="20%">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/7ebbcc60-a588-4a22-b4bd-c48f0afa32a7" width="20%">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/3b1445a4-798f-49d2-a8a9-81b9ea90cb6b" width="20%">
</div>

Android işletim sistemi günümüzde milyarlarca cihaz tarafından kullanılan bir Linux tabanlı işletim sistemidir. Android çeşitli cihaz türlerinde, fiyat aralıklarınaki cihazlarda ve daha fazla cihaz seçeneğinde kullanılabilmektedir. Android işletim sistemi üzerinde geliştirme yapılmasının nedeni büyük bir open source yazılım olması, geliştirici desteğinin ve dokümantasyonunun fazla olması, geliştirme maliyetlerinin çok düşük olması, daha fazla kullanıcıya ulaşmak içindir. 

Kotlin programlama dili ise en yeni programlama dilleri arasında yer almaktadır ve Java gibi dillere göre daha esnek programlama imkanu vermektedir. Programlaması yapılan yazılım, daha kolay geliştirilecek, hataların düzeltilmesi kolaylaşacaktır. 

Firebase ise Google tarafından geliştirilen ve uygulama geliştiricileri için hizmet veren bir yazılımdır. Kullanıcılar için kimlik doğrulama ile giriş, bulut depolama ve veri tabanı yönetimi için kullanılmıştır. Kullanıcılar mobil uygulama ile giriş yaptıktan sonra sadece kendilerine tanımlı collection ve documentlerden veri çekecek ve veri yazacaktır.

## Firebase Kullanımı
Firebase Authentication : Kullanıcıların e-posta ve şifreler ile giriş yapması sağlanmaktadır. Burada yer alan User ID ile ilgili işlemler gerçekleştirilmektedir.

Firebase Firestore : İlgili User ID ile işlemlerin gerçekleştirilmesi için birbiri ile ilişkilendirilmiş Collectionlar yer almaktadır. Collectionlar bir üst gruptur. Bu grup altında kendisine bağlı veriler yer almaktadır. Bu verilerin ise veri alanları yer almaktadır. Bunun ismi ise fiel olarak tanımlanmaktadır. Fieldların key ve value değerlerine göre işlem yapılmaktadır.

Firebase Firestore Collection ve Documentler : 
<div align="center">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/92bab6f6-2c79-4b6c-a566-7aca18522268" width="100%">
</div>

kullaniciBilgileri : User ID ile kullanıcı ile ilgili bir document oluşturulur. 

Bu document içinde ise sırası ile bu fieldlar bulunmaktadır : eposta, firebasID, isim, kutuphane_bilgi, soyisim, yonetici_yetki. 

Burada yer alan yonetici_yeki ile ilgili kullanıcıya sistem üzerinde yönetim yetkisi verilir. kutuphane_bilgi ise bu kullanıcıya tanımlı olan kütüphane ile işlem yapmasını sağlamaktadır.

kutuphane_bilgi : Bu bir collectiondur. İlgili kütüphane ile ilgili işlemlerin kaydedileceği collection bilgisini bir document ile saklamaktadır. Bu document içinde ise kutuphane_islem_tablo isimli bir field vardır ve ilgili işlemlerin tutulacağı collectionu belirtir. 

kutuphane_(sayı-veya-karakter) : Bu ise kullanıcıya kutuphane_bilgi ile tanımlı bir collectiondur. Her kullanıcı sadece kendisine tanımlı collection için işlem yapmaktadır. Bu colleciton içinde kitaplar sırası ile kitap(sayi(kendisinden_onceki_tum_kitap_sayisi) ile kaydedilmektedir. Bu işlemler ile ilgili documentler oluşur. Bu documentlerin içinde ise sırası ile şu fieldar yer almaktadır. 

kitap_id, kitap_isim, kitap_sayfa_sayi, kitap_teslim, kitap_teslim_alan_kisi, kitap_teslim_alan_kisi_telefon, kitap_teslim_alma_tarih, kitap_yayinevi, kitap_yazar.

kitap_teslim ile bir kitabın teslim edilip edilmediği kontrol edilir ve ilgili kitap için ilgili uygulama içi activity açılır. kitap_teslim_alma_tarih ise ilgili kitap için Firebase Timesmap bilgisini almaktadır.

## Bilgisayar Uygulaması
Bilgisayar uygulaması ile ilgili Firebase Admin SDK Python kullanılmaktadır. Bunun için ilgili düzeltmeleri el ile yapmanız gerekebilir. Excel tablosu içindeki kitap bilgileri kolayca Firebase Firestore Database içine kaydedilmektedir. Lütfen aşağıdaki paketleri kurunuz : 

pip install firebase-admin

pip install pandas
<div align="center">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/998580bf-2fc1-4aec-8535-6812f2cf75cb" width="33%">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/e8a6b7d7-a5a0-4cbe-9a95-6d492209e5ba" width="33%">
</div>


## Ekran Fotoğrafları
<div align="center">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/8cb1d0f2-17ff-4752-87b7-7dba9166f1cf" width="33%">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/dd051a22-aacc-4e54-b9c5-f13bbdda4754" width="33%">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/e8a6b7d7-a5a0-4cbe-9a95-6d492209e5ba" width="33%">
</div>
<div align="center">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/6b7e19d0-8d85-491f-b37d-f9e86be56a4e" width="33%">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/dff487d5-2c2e-48ee-b7b9-ca789d7a931e" width="33%">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/6eb68627-ee71-461f-b74a-4d9f1f26a383" width="33%">
</div>
<div align="center">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/6d301af7-87a6-4a6e-a6b6-6f4b661b6b37" width="33%">
    <img src="https://github.com/denizzhansahin/kutuphane_yonetim_app/assets/95483485/b1272ff3-dfa4-4ecc-a640-9f9a6c821e4c" width="33%">
</div>

