package br.com.tech.challenge.ms.producao.servicos;


import br.com.tech.challenge.ms.producao.api.client.FilaPedidosClient;
import br.com.tech.challenge.ms.producao.api.client.PedidosClient;
import br.com.tech.challenge.ms.producao.bd.repositorios.FilaPedidosRepository;
import br.com.tech.challenge.ms.producao.domain.dto.PedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.enums.StatusPedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilaPedidosService {



   private final FilaPedidosRepository filaPedidosRepository;

    private final FilaPedidosClient filaPedidosClient;

    private final PedidosClient pedidosClient;

    private final ModelMapper mapper;


    public List<FilaPedidosDTO> listFilaPedidos() {
        log.info("Listando fila de pedidos");

        return filaPedidosRepository.findAll();
    }


    public void salvaPedido(String idPedido){

      PedidoDTO pedido = pedidosClient.findById(Long.parseLong(idPedido));

      FilaPedidosDTO filaPedido = new FilaPedidosDTO();
      filaPedido.setStatusPedido(pedido.getStatusPedido());
      filaPedido.setSenhaRetirada(pedido.getSenhaRetirada());
      filaPedido.setNomeCliente(pedido.getCliente().getNome());
      filaPedido.setId(pedido.getId().toString());

      filaPedidosRepository.save(filaPedido);
    }

}
