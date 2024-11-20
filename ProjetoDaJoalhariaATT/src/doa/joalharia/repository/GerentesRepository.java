package doa.joalharia.repository;

import doa.joalharia.entity.GerentesEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerentesRepository {

    private static final String filePath = "C:\\Users\\Tiago lourenço\\OneDrive\\Nova pasta\\OneDrive\\Ambiente de Trabalho\\Lusofona\\Mestrado\\DOA\\gerentes.csv";

    public void salvarGerente(GerentesEntity gerente) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String linha = gerente.getNome() + "," + gerente.getNif() + "," +
                    gerente.getDataDeContratacao() + "," + gerente.getSalario() + "," + gerente.getVendasEquipe();
            writer.write(linha + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public List<GerentesEntity> listarGerentes() {
        List<GerentesEntity> gerentes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Scanner scanner = new Scanner(new FileReader(filePath, StandardCharsets.UTF_8))) {
            if (scanner.hasNextLine()) scanner.nextLine(); // Ignorar cabeçalho
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(",");
                if (columns.length == 5) {  // Atualizado para 5 campos
                    int nif = Integer.parseInt(columns[1]);
                    String nome = columns[0];
                    LocalDate dataDeContratacao = LocalDate.parse(columns[2], formatter);
                    double salario = Double.parseDouble(columns[3]);
                    int vendasEquipe = Integer.parseInt(columns[4]);
                    gerentes.add(new GerentesEntity(nome, nif, dataDeContratacao, salario, vendasEquipe));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        return gerentes;
    }

    public void excluirGerente(int nif) {
        List<GerentesEntity> gerentes = listarGerentes();
        List<GerentesEntity> gerentesRestantes = new ArrayList<>();

        for (GerentesEntity gerente : gerentes) {
            if (gerente.getNif() != nif) {
                gerentesRestantes.add(gerente);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println("Nome,NIF,DataDeContratacao,Salario,VendasEquipe");  // Cabeçalho atualizado
            for (GerentesEntity gerente : gerentesRestantes) {
                writer.println(gerente.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }

    public void atualizarSalarioGerente(int nif, double novoSalario) {
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
            System.out.println("Gerente com NIF " + nif + " não encontrado.");
        }
    }
}
