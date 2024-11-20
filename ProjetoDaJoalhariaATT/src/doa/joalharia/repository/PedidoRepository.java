package doa.joalharia.repository;

import doa.joalharia.entity.ClientesEntity;
import doa.joalharia.entity.PedidosEntity;
import doa.joalharia.entity.interfac.Joia;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class PedidoRepository {
    private final String filePath = "C:\\Users\\Tiago lourenço\\OneDrive\\Nova pasta\\OneDrive\\Ambiente de Trabalho\\Lusofona\\Mestrado\\DOA\\pedidos.csv";

    public void salvarPedido(PedidosEntity pedido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(pedido.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar pedido: " + e.getMessage());
        }
    }




    // Metodo para listar pedidos com o ajuste para buscar cliente por NIF
    public List<PedidosEntity> listarPedidos() {
        List<PedidosEntity> pedidos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            boolean isHeader = true;

            while ((linha = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados.length != 7) {
                    System.out.println("Linha inválida no arquivo CSV: " + linha);
                    continue;
                }

                try {
                    Long id = Long.parseLong(dados[0]);
                    Date data = dateFormat.parse(dados[1]);
                    ClientesEntity cliente = buscarClientePorNif(dados[2]);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado para o NIF: " + dados[2]);
                        continue;
                    }

                    List<Joia> itens = parseItens(dados[3]);
                    if (itens.isEmpty()) {
                        System.out.println("Itens inválidos no pedido com ID: " + id);
                        continue;
                    }

                    PedidosEntity.Status status = parseStatus(dados[4]);
                    double valorTotal = Double.parseDouble(dados[5]);
                    String metodoDePagar = dados[6];

                    PedidosEntity pedido = new PedidosEntity(
                            id, data, cliente, itens, status, null, valorTotal, metodoDePagar
                    );
                    pedidos.add(pedido);
                } catch (Exception e) {
                    System.out.println("Erro ao processar linha: " + linha + " -> " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler os pedidos: " + e.getMessage());
        }

        return pedidos;
    }


    // Metodo para converter String para Date
    private Date parseData(String dataString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            return dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + dataString);
            return null;
        }
    }



    // Metodo para parsear o status de forma segura
    private PedidosEntity.Status parseStatus(String statusString) {
        try {
            return PedidosEntity.Status.valueOf(statusString);
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido: " + statusString);
            return PedidosEntity.Status.Pendente; // Valor padrão
        }
    }

    // Metodo para reconstruir a lista de itens a partir da string (agora com IDs)
    private List<Joia> parseItens(String itensString) {
        List<Joia> itens = new ArrayList<>();
        if (itensString != null && !itensString.isEmpty()) {
            String[] itensArray = itensString.split(";"); // Supondo que itens são separados por ";"
            for (String item : itensArray) {
                try {
                    Joia joia = buscarJoiaPorId(Long.parseLong(item.trim())); // Metodo para buscar Joia por ID
                    if (joia != null) {
                        itens.add(joia);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID de joia inválido: " + item);
                }
            }
        }
        return itens;
    }

    // Metodo para reconstruir a lista de pagamentos a partir da string
    private List<Joia> parsePagamentos(String pagamentosString) {
        List<Joia> pagamentos = new ArrayList<>();
        String[] pagamentosArray = pagamentosString.split(";");  // Considerando que os pagamentos são separados por ";"
        for (String pagamento : pagamentosArray) {
            if (!pagamento.trim().isEmpty()) {
                try {
                    // Converter o ID de pagamento para Long antes de buscar
                    Long idPagamento = Long.parseLong(pagamento.trim());  // Convertendo para Long
                    Joia joia = buscarJoiaPorId(idPagamento);  // Agora você passa o Long
                    if (joia != null) {
                        pagamentos.add(joia);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID de pagamento inválido: " + pagamento.trim());
                }
            }
        }
        return pagamentos;
    }

    // Metodo para buscar um pedido pelo ID
    public PedidosEntity buscarPorId(Long id) {
        List<PedidosEntity> pedidos = listarPedidos();
        for (PedidosEntity pedido : pedidos) {
            if (pedido.getId().equals(id)) {
                return pedido;
            }
        }
        return null;  // Retorna null se o pedido não for encontrado
    }

    // Metodo para buscar uma joia pelo id
    private Joia buscarJoiaPorId(Long id) {
        // Aqui você pode buscar a joia nos repositórios conforme o tipo de joia
        Joia joia = new AneisRepository().buscarPorId(id);  // Modifique conforme necessário
        if (joia == null) {
            joia = new ColaresRepository().buscarPorId(id);
        }
        if (joia == null) {
            joia = new BrincosRepository().buscarPorId(id);
        }
        return joia;
    }


    private ClientesEntity buscarClientePorNif(String nif) {
        // Lógica para buscar o cliente no repositório ou criar um cliente fictício se necessário
        ClientesRepository clientesRepository = new ClientesRepository(); // Exemplo de instância do repositório
        ClientesEntity cliente = clientesRepository.buscarClientePorNif(nif);  // Método para buscar pelo NIF

        if (cliente == null) {
            System.out.println("Cliente não encontrado para o NIF: " + nif);
        }

        return cliente; // Se não encontrar, retorna null
    }

}




