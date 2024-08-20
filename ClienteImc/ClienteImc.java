package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteImc {

    public static void main(String[] args) {

        Socket socket;
        InputStream canalEntrada;
        OutputStream canalSaida;
        BufferedReader entrada;
        PrintWriter saida;

        try {
            socket = new Socket("127.0.0.1", 4000);

            canalEntrada = socket.getInputStream();
            canalSaida = socket.getOutputStream();

            entrada = new BufferedReader(new InputStreamReader(canalEntrada));
            saida = new PrintWriter(canalSaida, true);

            Scanner leitor = new Scanner(System.in);
            System.out.print("Digite seu peso:");
            double peso = leitor.nextDouble();
            System.out.println("Digite sua altura:");
            double altura = leitor.nextDouble();

            saida.println(peso);
            saida.println(altura);

            String imcReposta = entrada.readLine();
            System.out.println("Seu IMC é: %.2f ", imcReposta);

            socket.close();
        } catch (IOException ioe) {
            System.out.println("Erro no cliente: " + ioe.getMessage());
        }
    }
}