# Kriteria Submission #
=============

## Submission 2 ##
---------------
* Daftar film
  * Terdapat 2 (dua) halaman yang menampilkan daftar film (Movies dan TV Show) dengan jumlah minimal 10 item
  * Menerapkan ViewModel untuk menampung data Movies dan TV 
  * __Menerapkan ViewModel, LiveData dan Repository__
* Detail film
  * Menampilkan poster dan informasi film pada halaman detail film
  * Menerapkan ViewModel untuk menampung detail film
  * __Menerapkan ViewModel, LiveData dan Repository.__
* Unit Test
  * Menerapkan unit test pada semua fungsi yang digunakan untuk mendapatkan data Movie dan TV Show
  * __Menerapkan unit test pada semua fungsi yang digunakan untuk mendapatkan data Movie dan Tv Show dari API atau Lokal.__
* Instrumentation Test
  * Menerapkan instrumentation test untuk memastikan fitur-fitur yang ada berjalan dengan semestinya.
  * Tuliskan skenario instrumentation test pada kolom Catatan ketika Anda ingin mengumpulkan tugas ini. Untuk format penulisan skenario pengujian, Anda bisa melihat contoh di modul [Proyek Academy : Pengujian ViewModel](https://www.dicoding.com/academies/129/tutorials/4454 "Proyek Academy : Pengujian ViewModel").
  * __Menerapkan instrumentation test untuk memastikan fitur-fitur yang ada berjalan dengan semestinya.__
  * __Jika pada aplikasi terdapat proses asynchronous, maka Anda wajib menerapkan Idle Resources.__
  * __Tuliskan skenario instrumentation test pada kolom Catatan ketika Anda ingin mengumpulkan tugas ini. Untuk format penulisan sekenario pengujian, Anda bisa melihat contoh di modul [Proyek Academy : Pengujian ViewModel](https://www.dicoding.com/academies/129/tutorials/4454 "Proyek Academy : Pengujian ViewModel").__
  
### Instrumental Testing : ###
---------------
* Menampilkan data movie
  * Memastikan __rv_movie__ dalam keadaan tampil.
  * Gulir __rv_movie__ ke posisi data terakhir.
* Menampilkan data detail movie
  * Memberi tindakan klik pada data pertama di __rv_movie__.
  * Memastikan __TextView__ untuk __title__ tampil sesuai dengan yang diharapkan.
  * Memastikan __TextView__ untuk __date__ tampil sesuai dengan yang diharapkan.
  * Memastikan __TextView__ untuk __description__ tampil sesuai dengan yang diharapkan.
* Menampilkan data tv show
  * Klik TabLayout dengan teks __TV Show__
  * Memastikan __rv_movie__ dalam keadaan tampil.
  * Gulir __rv_movie__ ke posisi data terakhir.
* Menampilkan data detail tv show
  * Klik TabLayout dengan teks __TV Show__
  * Memberi tindakan klik pada data pertama di __rv_movie__.
  * Memastikan __TextView__ untuk __title__ tampil sesuai dengan yang diharapkan.
  * Memastikan __TextView__ untuk __date__ tampil sesuai dengan yang diharapkan.
  * Memastikan __TextView__ untuk __description__ tampil sesuai dengan yang diharapkan.

