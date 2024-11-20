package doa.joalharia.repository;

import doa.joalharia.entity.VendedorEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendedorRepository {
    private static final String filePath = "C:\\Users\\Tiago lourenço\\OneDrive\\Nova pasta\\OneDrive\\Ambiente de Trabalho\\Lusofona\\Mestrado\\DOA\\vendedor.csv";

    // Salvar um novo vendedor
    public void salvarVendedor(VendedorEntity vendedor) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String linha = vendedor.getNome() + "," + vendedor.getNif() + "," +
                    vendedor.getDataDeContratacao() + "," + vendedor.getSalario() + "," + vendedor.getVendasFeitas();
            writer.write(linha + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    // Listar todos os vendedores
    public List<VendedorEntity> listarVendedores() {
        List<VendedorEntity> vendedores = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Scanner scanner = new Scanner(new FileReader(filePath, StandardCharsets.UTF_8))) {
            boolean isHeader = true; // Variável para controlar a linha do cabeçalho

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();

                // Pular o cabeçalho
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] columns = linha.split(",");
                if (columns.length == 5) {  // Se a linha tem 5 colunas
                    try {
                        int nif = Integer.parseInt(columns[1]);
                        String nome = columns[0];
                        LocalDate dataDeContratacao = LocalDate.parse(columns[2], formatter);
                        double salario = Double.parseDouble(columns[3]);
                        int vendasFeitas = Integer.parseInt(columns[4]);
                        vendedores.add(new VendedorEntity(nome, nif, dataDeContratacao, salario, vendasFeitas));
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao processar dados de um vendedor: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }

        return vendedores;
    }



    // Excluir um vendedor pelo NIF
    public void excluirVendedor(int nif) {
        List<VendedorEntity> vendedores = listarVendedores();
        List<VendedorEntity> vendedoresRestantes = new ArrayList<>();

        for (VendedorEntity vendedor : vendedores) {
            if (vendedor.getNif() != nif) {
                vendedoresRestantes.add(vendedor);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println("Nome,NIF,DataDeContratacao,Salario,VendasFeitas");  // Cabeçalho atualizado
            for (VendedorEntity vendedor : vendedoresRestantes) {
                writer.println(vendedor.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }

    // Atualizar o salário de um vendedor pelo NIF
    public void atualizarSalarioVendedor(int nif, double novoSalario) {
        File inputFile = new File(filePath);
        StringBuilder novoConteudo = new StringBuilder();
        boolean encontrado = false;

        try (Scanner scanner = new Scanner(inputFile)) {
            // Processa o arquivo linha por linha
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] colunas = linha.split(",");

                // Ignorar cabeçalho ou entradas inválidas
                if (colunas.length < 5 || linha.startsWith("Nome")) {  // Verifica se tem os 5 campos
                    novoConteudo.append(linha).append("\n");
                    continue;
                }

                // Se o NIF corresponder, atualize o salário
                if (Integer.parseInt(colunas[1]) == nif) {
                    colunas[3] = String.valueOf(novoSalario); // Atualiza o salário na posição correta (índice 3)
                    linha = String.join(",", colunas);       // Reconstrói a linha
                    encontrado = true;
                }

                novoConteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Verifica se o NIF foi encontrado e reescreve o arquivo
        if (encontrado) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.write(novoConteudo.toString());
                System.out.println("Salário atualizado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar alterações no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Vendedor com NIF " + nif + " não encontrado.");
        }
    }
}
