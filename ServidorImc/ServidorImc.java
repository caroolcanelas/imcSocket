package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorImc {

    private ServerSocket sckServidor;

    public ServidorImc() {
        try {
            this.sckServidor = new ServerSocket(4000);

            while (true) {
                Socket sckEcho = this.sckServidor.accept();
                System.out.println("Cliente conectado!");

                processarCliente(sckEcho);
            }

        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

    private void processarCliente(Socket sckEcho) {
        try {
            InputStream canalEntrada = sckEcho.getInputStream();
            OutputStream canalSaida = sckEcho.getOutputStream();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(canalEntrada));
             PrintWriter saida = new PrintWriter(canalSaida, true);

            double peso = Double.parseDouble(entrada.readLine());
            double altura = Double.parseDouble(entrada.readLine());

            double imc = peso / (altura * altura);

            saida.println(imc);

            sckEcho.close();

        } catch (IOException e) {
            System.out.println("Erro ao processar cliente: " + e.getMessage());
        }
    }
}