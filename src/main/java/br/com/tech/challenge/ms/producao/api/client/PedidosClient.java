package br.com.tech.challenge.ms.producao.api.client;

import br.com.tech.challenge.ms.producao.domain.dto.PedidoDTO;
import br.com.tech.challenge.ms.producao.domain.dto.external.FilaPedidosDTO;
import br.com.tech.challenge.ms.producao.domain.entidades.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "pedidos", url = "${pedidos.api.url}")
public interface PedidosClient {

    @GetMapping("/pedidos")
    Page<FilaPedidosDTO> findAll(Pageable pageable);


    @GetMapping("/pedidos/{pedidoID}")
    PedidoDTO findById(@PathVariable("pedidoID") Long pedidoID);


    @PutMapping("/pedidos")
    PedidoDTO save(@RequestBody PedidoDTO pedidoDTO);
}
