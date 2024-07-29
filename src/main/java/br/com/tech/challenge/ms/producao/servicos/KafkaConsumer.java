package br.com.tech.challenge.ms.producao.servicos;


import br.com.tech.challenge.ms.producao.bd.repositorios.FilaPedidosRepository;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.entidades.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private FilaPedidosRepository filaPedidosRepository;

    @KafkaListener(topics = "fila-pedidos", groupId = "grupo-pedidos")
    public void consume(String message) throws JsonProcessingException {
        // Assumindo que a mensagem é um JSON e que temos uma classe Pedido correspondente
        Pedido pedido = new ObjectMapper().readValue(message, Pedido.class);

       FilaPedidosDTO filaPedido = new FilaPedidosDTO();
       filaPedido.setStatusPedido(pedido.getStatusPedido());
       filaPedido.setSenhaRetirada(pedido.getSenhaRetirada());
       filaPedido.setNomeCliente(pedido.getCliente().getNome());
        filaPedidosRepository.save(filaPedido);
    }
}
