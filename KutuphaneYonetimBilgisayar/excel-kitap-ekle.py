#YAPILACAKLAR
#ilk önce bir excel talosundan satır satır veri alma yap
#daha sonra bu alınan verileri firebase deposuna kaydet

import pandas as pd
df = pd.read_excel("KitapEkle.xlsx")


def bilgi_sayi():
    print(f"Satır sayısı {int((df.iloc[0::]).size/df.columns.size)} ::::: \n Sütun sayısı {df.columns.size}")

def bilgi_satir_yazdir():
    for i in range(int(df.iloc[::].size/df.columns.size)):
        print(f"{i}. satır bilgisi : {df.iloc[i]}")

def bilgi_satir_deger():
    for i in range(int(df.iloc[::].size/df.columns.size)):
        print(f"{i}. satır bilgisi : {df.iloc[i].values}")

def firebase_ekle():
    import firebase_admin
    from firebase_admin import credentials, firestore, storage,auth
    credentialData = credentials.Certificate("firebase.json")
    firebase_admin.initialize_app(credentialData, {
    'storageBucket': 'firebase.appspot.com'
    })
    firestoreDb = firestore.client()
    bucket = storage.bucket()
    
    print("firebase için ekleme")

    user = auth.get_user_by_email('....@....com')
    uid = user.uid
    string_uid = str(uid)
    print(f"Kullanıcı ID : {uid}")

    veri_kutuphane = firestoreDb.collection('kullaniciBilgileri').document(string_uid).get().to_dict().get('kutuphane_bilgi')
    print(veri_kutuphane)

    #document sayısını öğrenme
    veri_document_sayi = firestoreDb.collection(str(veri_kutuphane)).get()
    print(len(veri_document_sayi))

    #tarih
    import datetime
    timestamp = datetime.datetime.now()
    tarih = timestamp.strftime("%Y-%m-%d")

    timestamp = firestore.SERVER_TIMESTAMP

    import time

    #veri ekleme
    print(f"Toplam Satır : {df.shape[0]}")


    deger = len(veri_document_sayi)+1

    belge_verileri = None
    
    for i in range(df.shape[0]):
        belge_ref = firestoreDb.collection(veri_kutuphane).document("kitap_"+str(deger))
        print(f"Satır : {i}")
        #print(f"{i}. satır bilgisi : {df.iloc[i].values}")

        belge_verileri = {
            'kitap_id': str(df.iloc[i].values[4]),
            'kitap_isim': str(df.iloc[i].values[0]),
            'kitap_sayfa_sayi': str(df.iloc[i].values[2]),
            'kitap_teslim': "Hayır",
            'kitap_yayinevi' : str(df.iloc[i].values[1]),
            'kitap_yazar' : str(df.iloc[i].values[3]),
            'kitap_teslim_alan_kisi': "yok",
            'kitap_teslim_alan_kisi_telefon': "yok",
            'kitap_teslim_alma_tarih': timestamp,
        }

        
        print(i)
        print(belge_verileri)
        belge_ref.set(belge_verileri, merge=True)
        deger = deger + 1
        



print(df)
satir_sayisi = df.shape[0]
print("DataFrame'in satır sayısı:", satir_sayisi)

firebase_ekle()
#print(df)
