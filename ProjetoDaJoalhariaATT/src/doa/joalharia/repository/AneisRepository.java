



package doa.joalharia.repository;

import doa.joalharia.entity.AneisEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AneisRepository {

    private static final String filePath = "C:\\\\Users\\\\Tiago lourenço\\\\OneDrive\\\\Nova pasta\\\\OneDrive\\\\Ambiente de Trabalho\\\\Lusofona\\\\Mestrado\\\\DOA\\\\aneis.csv";

    public AneisEntity buscarPorId(Long id) {
        return listarAneis().stream()
                .filter(anel -> anel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void salvarAnel(AneisEntity anel) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String linha = anel.getId() + "," + anel.getNome() + "," + anel.getTamanho() + "," +
                    anel.getMaterial() + "," + anel.getPeso() + "," + anel.getPreco();
            writer.write(linha + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public List<AneisEntity> listarAneis() {
        List<AneisEntity> aneis = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(filePath, StandardCharsets.UTF_8))) {
            if (scanner.hasNextLine()) scanner.nextLine(); // Ignorar cabeçalho
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(",");
                if (columns.length == 6) {
                    long id = Long.parseLong(columns[0]);
                    String nome = columns[1];
                    String tamanho = columns[2];
                    String material = columns[3];
                    double peso = Double.parseDouble(columns[4]);
                    double preco = Double.parseDouble(columns[5]);
                    aneis.add(new AneisEntity(id, nome, tamanho, material, peso, preco));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        return aneis;
    }

    public void excluirAnel(long id) {
        List<AneisEntity> aneis = listarAneis();
        List<AneisEntity> aneisRestantes = new ArrayList<>();

        for (AneisEntity anel : aneis) {
            if (anel.getId() != id) {
                aneisRestantes.add(anel);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println("ID,Nome,Tamanho,Material,Peso,Preco");

            for (AneisEntity anel : aneisRestantes) {
                writer.println(anel.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }



    public void atualizarPrecoAneis(long id, double novoPreco) {
        File inputFile = new File(filePath);
        StringBuilder novoConteudo = new StringBuilder();
        boolean encontrado = false;

        try (Scanner scanner = new Scanner(inputFile)) {
            // Processa o arquivo linha por linha
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] colunas = linha.split(",");

                // Ignorar cabeçalho ou entradas inválidas
                if (colunas.length < 6 || linha.startsWith("ID")) {
                    novoConteudo.append(linha).append("\n");
                    continue;
                }

                // Se o ID corresponder, atualize o preço
                if (Long.parseLong(colunas[0]) == id) {
                    colunas[5] = String.valueOf(novoPreco); // Atualiza o preço na posição correta
                    linha = String.join(",", colunas);     // Reconstrói a linha
                    encontrado = true;
                }

                novoConteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Verifica se o ID foi encontrado e reescreve o arquivo
        if (encontrado) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.write(novoConteudo.toString());
                System.out.println("Preço atualizado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar alterações no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Anel com ID " + id + " não encontrado.");
        }
    }
}

