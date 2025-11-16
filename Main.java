import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan path file: ");
        String filePath = sc.nextLine();

        System.out.print("Berapa baris per file: ");
        int chunkSize = sc.nextInt();

        Queue<String> queue = new LinkedList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                queue.offer(line);
            }
            reader.close();

            int fileIndex = 1;
            while (!queue.isEmpty()) {
                FileWriter writer = new FileWriter("output_part_" + fileIndex + ".txt");
                int count = 0;

                while (count < chunkSize && !queue.isEmpty()) {
                    writer.write(queue.poll() + "\n");
                    count++;
                }

                writer.close();
                fileIndex++;
            }

            System.out.println("File berhasil dipotong dan disimpan sebagai output_part_*.txt");

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca atau menulis file.");
        }
    }
}
