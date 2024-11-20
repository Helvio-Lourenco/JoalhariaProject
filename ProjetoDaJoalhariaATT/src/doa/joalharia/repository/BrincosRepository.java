package doa.joalharia.repository;

import doa.joalharia.entity.BrincosEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BrincosRepository {

    private static final String filePath = "C:\\\\Users\\\\Tiago lourenço\\\\OneDrive\\\\Nova pasta\\\\OneDrive\\\\Ambiente de Trabalho\\\\Lusofona\\\\Mestrado\\\\DOA\\\\brincos.csv";

    public BrincosEntity buscarPorId(Long id) {
        return listarBrincos().stream()
                .filter(brinco -> brinco.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void salvarBrinco(BrincosEntity brinco) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String linha = brinco.getId() + "," + brinco.getNome() + "," + brinco.getTipoFecho() + "," +
                    brinco.getMaterial() + "," + brinco.getPeso() + "," + brinco.getPreco();
            writer.write(linha + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public List<BrincosEntity> listarBrincos() {
        List<BrincosEntity> brincos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(filePath,StandardCharsets.UTF_8))) {
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(",");
                if (columns.length == 6) {
                    long id = Long.parseLong(columns[0]);
                    String nome = columns[1];
                    String tipoFecho = columns[2];
                    String material = columns[3];
                    double peso = Double.parseDouble(columns[4]);
                    double preco = Double.parseDouble(columns[5]);
                    brincos.add(new BrincosEntity(id, nome, tipoFecho, material, peso, preco));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        return brincos;
    }

    public void excluirBrinco(long id) {
        List<BrincosEntity> brincos = listarBrincos();
        List<BrincosEntity> brincosRestantes = new ArrayList<>();

        for (BrincosEntity brinco : brincos) {
            if (brinco.getId() != id) {
                brincosRestantes.add(brinco);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println("ID,Nome,Tipo Fecho,Material,Peso,Preço");

            for (BrincosEntity brinco : brincosRestantes) {
                writer.println(brinco.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }



    public void atualizarPrecoBrincos(long id, double novoPreco) {
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
