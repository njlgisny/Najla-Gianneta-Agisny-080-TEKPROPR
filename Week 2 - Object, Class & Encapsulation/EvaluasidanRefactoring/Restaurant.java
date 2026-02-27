public class Restaurant {
    private String[] nama_makanan;
    private double[] harga_makanan;
    private int[] stok;
    private byte id = 0;

    public Restaurant() {
        nama_makanan = new String[10];
        harga_makanan = new double[10];
        stok = new int[10];
    }

    // Getter
    public int getStok(int index) {
        return stok[index];
    }

    //Setter dan Validasi
    public void setStok(int index, int stokBaru) {
        if (stokBaru >= 0) {
            stok[index] = stokBaru;
        } else {
            System.out.println("Stok tidak boleh negatif!");
        }
    }

    public void tambahMenuMakanan(String nama, double harga, int stokBaru) {
        if (stokBaru < 0) {
             System.out.println("\n[ERROR] Stok tidak valid untuk menu: " + nama);
            return;
        }

        this.nama_makanan[id] = nama;
        this.harga_makanan[id] = harga;
        this.stok[id] = stokBaru;
        id++;
    }

    public void tampilMenuMakanan() {
        for (int i = 0; i < id; i++) {
            if (!isOutOfStock(i)) {
                // menu masih ada stok
                System.out.println(
                    nama_makanan[i] + "[" + stok[i] + "]" +
                    "\tRp. " + harga_makanan[i]
                );
            } else {
                // menu habis stok
                System.out.println(
                    nama_makanan[i] + "[0] (HABIS)" +
                    "\tRp. " + harga_makanan[i]
                );
            }
        }
    }

    public void tampilIndexMenu() {
        for (int i = 0; i < id; i++) {
            System.out.println(i + ". " + nama_makanan[i]);
        }
    }

    public boolean isOutOfStock(int index) {
        return stok[index] == 0;
    }

    public void pesanMenu(int index, int jumlah) {

        if (index < 0 || index >= id) {
            System.out.println("Index menu tidak valid!");
            return;
        }

        if (jumlah <= 0) {
            System.out.println("Jumlah pesanan tidak valid.");
            return;
        }

        int stokAwal = stok[index];

        if (stokAwal >= jumlah) {
            setStok(index, stokAwal - jumlah);

            System.out.println(
                "Pesanan berhasil: " + nama_makanan[index] +
                " | dipesan: " + jumlah
            );

        } else {
            System.out.println(
                "Pesanan ditolak: " + nama_makanan[index] +
                " | diminta: " + jumlah +
                " | stok tersedia: " + stokAwal
            );
        }
    }
}

