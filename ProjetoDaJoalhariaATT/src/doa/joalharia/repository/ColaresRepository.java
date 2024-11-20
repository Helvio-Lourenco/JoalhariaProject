package doa.joalharia.repository;

import doa.joalharia.entity.ColaresEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColaresRepository {

    private static final String filePath = "C:\\\\Users\\\\Tiago lourenço\\\\OneDrive\\\\Nova pasta\\\\OneDrive\\\\Ambiente de Trabalho\\\\Lusofona\\\\Mestrado\\\\DOA\\\\colares.csv";

    public ColaresEntity buscarPorId(Long id) {
        return listarColares().stream()
                .filter(colar -> colar.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void salvarColar(ColaresEntity colar) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String linha = colar.getId() + "," + colar.getNome() + "," + colar.getComprimento() + "," +
                    colar.getMaterial() + "," + colar.getPeso() + "," + colar.getPreco();
            writer.write(linha + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public List<ColaresEntity> listarColares() {
        List<ColaresEntity> colares = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(",");
                if (columns.length == 6) {
                    long id = Long.parseLong(columns[0]);
                    String nome = columns[1];
                    String comprimento = columns[2];
                    String material = columns[3];
                    double peso = Double.parseDouble(columns[4]);
                    double preco = Double.parseDouble(columns[5]);
                    colares.add(new ColaresEntity(id, nome, comprimento, material, peso, preco));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        return colares;
    }




    // Método para excluir um colar pelo ID
    public void excluirColar(long id) {
        List<ColaresEntity> colares = listarColares();
        List<ColaresEntity> colaresRestantes = new ArrayList<>();

        // Percorrer os colares e adicionar à lista apenas os que não têm o ID especificado
        for (ColaresEntity colar : colares) {
            if (colar.getId() != id) {
                colaresRestantes.add(colar);
            }
        }

        // Reescrever o arquivo com os colares restantes
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            // Escrever cabeçalho, se necessário
            writer.println("ID,Nome,Comprimento,Material,Peso,Preço");

            for (ColaresEntity colar : colaresRestantes) {
                writer.println(colar.toCSV());  // Gravar os colares restantes
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }








    public void atualizarPrecoColares(long id, double novoPreco) {
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
            System.out.println("Colar com ID " + id + " não encontrado.");
        }
    }
}

