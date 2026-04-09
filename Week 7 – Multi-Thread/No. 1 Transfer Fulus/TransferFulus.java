class Account {
    int balance = 150;  // Atribut balance digunakan untuk menyimpan saldo tiap akun
}
public class TransferFulus {
    public static void main(String[] args) throws InterruptedException {
        // Membuat dua objek akun yang akan digunakan bersama oleh thread 
        Account acc1 = new Account();
        Account acc2 = new Account();

        // Thread 1: Menjumlahkan/ transfer fulus dari acc1 ke acc2
        Thread t1 = new Thread(() -> {
            synchronized (acc1) { // Mengunci acc1 agar tidak diakses thread lain secara bersamaan
                System.out.println("Thread 1: Mengunci acc1");
                try { 
                    Thread.sleep(100); 
                } catch (Exception e) {}// Memberikan jeda simulasi proses, perlu try-catch karena bisa terjadi InterruptedException

                synchronized (acc2) { // Mengunci acc2 setelah acc1 berhasil dikunci
                    System.out.println("Thread 1: Mengunci acc2 dan melakukan transfer");
                    acc2.balance += acc1.balance;   // Proses transfer saldo dari acc1 ke acc2
                }
            }
        });

        // Thread 2: Menjumlahkan/ transfer fulus dari acc2 ke acc1
        Thread t2 = new Thread(() -> {
            synchronized (acc1) { // Mengunci acc1 dulu (urutan disamakan dengan Thread 1 untuk mencegah deadlock)
                System.out.println("Thread 2: Mengunci acc1");
                try { 
                    Thread.sleep(100); 
                } catch (Exception e) {}

                synchronized (acc2) { // Mengunci acc2 setelah acc1
                    System.out.println("Thread 2: Mengunci acc2 dan melakukan transfer");
                    acc1.balance += acc2.balance;   // Proses transfer saldo dari acc2 ke acc1
                }
            }
        });

        // Menjalakan kedua thread secara paralel
        t1.start();
        t2.start();

        // Memastikan kedua thread selesai sebelum melanjutkan ke output
        t1.join();
        t2.join();

        // Menampilkan hasil akhir saldo setelah proses transfer selesai.
        System.out.println("--- HASIL AKHIR ---");
        System.out.println("Saldo Akhir acc1: " + acc1.balance);
		System.out.println("Saldo Akhir acc2: " + acc2.balance);

    }
}
