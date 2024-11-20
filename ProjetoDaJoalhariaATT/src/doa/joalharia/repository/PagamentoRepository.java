package doa.joalharia.repository;

import doa.joalharia.entity.PagamentosEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PagamentoRepository {
    private final String filePath = "C:\\Users\\Tiago lourenço\\OneDrive\\Nova pasta\\OneDrive\\Ambiente de Trabalho\\Lusofona\\Mestrado\\DOA\\pagamento.csv";

    public void salvarPagamento(PagamentosEntity pagamento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(pagamento.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar pagamento: " + e.getMessage());
        }
    }

    public List<PagamentosEntity> listarPagamentos() {
        List<PagamentosEntity> pagamentos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato de data esperado no CSV

        try (Scanner scanner = new Scanner(new FileReader(filePath, StandardCharsets.UTF_8))) {
            if (scanner.hasNextLine()) scanner.nextLine(); // Ignorar cabeçalho
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(",");
                if (columns.length == 4) {
                    long id = Long.parseLong(columns[0].trim());
                    double valor = Double.parseDouble(columns[1].trim());
                    PagamentosEntity.MetodoPagamento metodo = PagamentosEntity.MetodoPagamento.valueOf(columns[2].trim());
                    Date data = null;
                    try {
                        data = dateFormat.parse(columns[3].trim()); // Converter string para Date
                    } catch (ParseException e) {
                        System.out.println("Erro ao converter a data: " + e.getMessage());
                    }
                    pagamentos.add(new PagamentosEntity(id, metodo, data, valor));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        } catch ( IllegalArgumentException e) {
            System.out.println("Erro ao converter dados de pagamento: " + e.getMessage());
        }
        return pagamentos;
    }


    public List<PagamentosEntity> listarPagamentosPorPedido(Long pedidoId) {
        // Para simplicidade, vamos listar todos os pagamentos
        // Este método pode ser ajustado para buscar os pagamentos específicos por pedido
        return listarPagamentos();
    }
}
