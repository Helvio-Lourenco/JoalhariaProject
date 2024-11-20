package doa.joalharia.repository;

import doa.joalharia.entity.ClientesEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientesRepository {

    private List<ClientesEntity> clientes;
    private static final String filePath = "C:\\Users\\Tiago lourenço\\OneDrive\\Nova pasta\\OneDrive\\Ambiente de Trabalho\\Lusofona\\Mestrado\\DOA\\clientes.csv";

    public ClientesRepository() {
        // Carrega os dados do arquivo para a lista de clientes ao inicializar o repositório
        this.clientes = listarClientes();
    }

    // Método para salvar cliente no arquivo
    public void salvarCliente(ClientesEntity cliente) {
        File file = new File(filePath);
        boolean isFirstLine = !file.exists() || file.length() == 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            if (isFirstLine) {
                writer.println("NIF,Nome,Email,Contacto,Morada");  // Cabeçalho, somente se for o primeiro cliente
            }
            String linha = cliente.getNif() + ","+ cliente.getNome() + "," +
                    cliente.getEmail() + "," + cliente.getContacto() + "," + cliente.getMorada();
            writer.write(linha + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public List<ClientesEntity> listarClientes() {
        List<ClientesEntity> clientes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(filePath, StandardCharsets.UTF_8))) {
            // Ignorar o cabeçalho
            if (scanner.hasNextLine()) scanner.nextLine();  // Ignora o cabeçalho

            // Ler os dados dos clientes
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(",");
                if (columns.length == 5) {  // Verifica se há 5 campos
                    String nif = columns[0].trim();
                    String nome = columns[1].trim();
                    String email = columns[2].trim();
                    String contacto = columns[3].trim();
                    String morada = columns[4].trim();
                    clientes.add(new ClientesEntity(nif, nome, email, contacto, morada));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }

        return clientes;
    }

    // Método para excluir cliente pelo NIF
    public void excluirCliente(String nif) {
        List<ClientesEntity> clientes = listarClientes();
        List<ClientesEntity> clientesRestantes = new ArrayList<>();

        for (ClientesEntity cliente : clientes) {
            if (!cliente.getNif().equals(nif)) {
                clientesRestantes.add(cliente);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println("NIF,Nome,Email,Contacto,Morada"); // Cabeçalho atualizado
            for (ClientesEntity cliente : clientesRestantes) {
                writer.println(cliente.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }

    // Método para buscar cliente pelo NIF
    public ClientesEntity buscarClientePorNif(String nif) {
        List<ClientesEntity> clientes = listarClientes();
        for (ClientesEntity cliente : clientes) {
            if (cliente.getNif().equals(nif)) {
                return cliente;
            }
        }
        return null; // Retorna null se não encontrar o cliente
    }
}
