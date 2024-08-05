package br.com.tech.challenge.ms.producao.servicos;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {


    private final FilaPedidosService filaPedidosService;

    public KafkaConsumer(FilaPedidosService filaPedidosService) {
        this.filaPedidosService = filaPedidosService;
    }


    @KafkaListener(topics = "fila-pedidos", groupId = "grupo-pedidos")
    public void consume(String idPedido) {
        log.debug("Mensagem recebida: {}", idPedido);
        // Tente converter a mensagem manualmente

        try {
//            Pedido pedido = new ObjectMapper().readValue(message, Pedido.class);
            log.info("Mensagem recebida no consumer: {}", idPedido);


            filaPedidosService.salvaPedido(idPedido);

        } catch (Exception e) {
            log.error("Erro ao processar a mensagem: {}", e.getMessage());
        }
    }
}
