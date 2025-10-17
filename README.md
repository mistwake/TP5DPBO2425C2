# TP5

## Alur Program
1. Saat program dijalankan, jendela utama (ProductMenu) terbuka dan langsung menampilkan daftar produk yang diambil dari database db_product melalui class Database.
2. User dapat menambahkan produk baru dengan mengisi form (ID, Nama, Harga, Stok, Kategori, dan Merk), lalu menekan tombol Add. Data yang dimasukkan akan langsung disimpan ke tabel product di database.
3. Ketika salah satu baris pada tabel diklik, data produk tersebut otomatis muncul di form, tombol Add berubah menjadi Update, dan tombol Delete ikut ditampilkan.
4. User dapat:
   - Mengubah data produk lalu menyimpannya dengan menekan tombol Update (data di database ikut diperbarui), atau
   - Menghapus produk dengan menekan tombol Delete (data di database juga terhapus setelah konfirmasi).
5. Tombol Cancel digunakan untuk mengosongkan seluruh form dan mengembalikan tampilan ke mode penambahan data baru.
6. Setiap perubahan (tambah, ubah, hapus) langsung diperbarui pada tabel dan tersimpan di database tanpa perlu me-restart program.
