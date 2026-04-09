import java.util.Scanner;

class Penjumlahan {
    private int[] hasil; // array untuk menyimpan hasil dari setiap thread

    public Penjumlahan(int jumlahThread) {
        hasil = new int[jumlahThread]; // ukuran array sesuai jumlah thread
    }

    public void hitung(int index, int start, int end) {
        int sum = 0; // variabel untuk menyimpan hasil sementara

        // menampilkan tugas masing-masing thread
        System.out.println("Thread-" + (index+1) +
                " mengerjakan: " + start + " sampai " + end);

        // proses penjumlahan sesuai range
        for (int i = start; i <= end; i++) {
            sum += i;
        }

        hasil[index] = sum; // menyimpan hasil ke array sesuai index thread

        // menampilkan hasil parsial dari tiap thread
        System.out.println("Thread-" + (index+1) +
                " hasil parsial = " + sum);
    }

    public int getTotal() {
        int total = 0;

        // menjumlahkan semua hasil parsial
        for (int i = 0; i < hasil.length; i++) {
            total += hasil[i];
        }

        return total; // mengembalikan total akhir
    }
}

public class PenjumlahanParalelMain {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        // input jumlah thread
        System.out.print("Masukkan jumlah thread: ");
        int jumlahThread = input.nextInt();

        // input angka akhir
        System.out.print("Masukkan angka akhir: ");
        int angkaAkhir = input.nextInt();

        Penjumlahan pj = new Penjumlahan(jumlahThread);

        Thread[] threads = new Thread[jumlahThread]; // array untuk menyimpan thread

        int range = angkaAkhir / jumlahThread; // membagi range ke setiap thread

        // membuat dan menjalankan thread
        for (int i = 0; i < jumlahThread; i++) {
            int index = i; // disimpan agar bisa dipakai di dalam thread

            int start = i * range + 1; // menentukan angka awal
            int end;

            // thread terakhir mengambil sisa range
            if (i == jumlahThread - 1) {
                end = angkaAkhir;
            } else {
                end = (i + 1) * range;
            }

            // membuat thread
            threads[i] = new Thread(() -> {
                pj.hitung(index, start, end); // memanggil method hitung
            });

            threads[i].start(); // menjalankan thread
        }

        // menunggu semua thread selesai
        for (int i = 0; i < jumlahThread; i++) {
            threads[i].join();
        }

        // menampilkan hasil akhir
        System.out.println("\n--- HASIL AKHIR ---");
        System.out.println("Total = " + pj.getTotal());

        input.close(); 
    }
}