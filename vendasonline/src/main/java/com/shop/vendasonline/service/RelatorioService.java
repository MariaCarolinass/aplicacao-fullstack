package com.shop.vendasonline.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.shop.vendasonline.dto.RelatorioDTO;
import com.shop.vendasonline.model.Cliente;
import com.shop.vendasonline.model.Pedido;
import com.shop.vendasonline.model.Status;
import com.shop.vendasonline.repository.ClienteRepository;
import com.shop.vendasonline.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RelatorioService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    
    public RelatorioDTO gerarRelatorio() {
        RelatorioDTO relatorio = new RelatorioDTO();
        
        relatorio.setTotalPedidos(pedidoRepository.count());
        relatorio.setValorTotalFaturado(pedidoRepository.sumValorTotal());
        relatorio.setQuantidadeProdutosVendidos(pedidoRepository.countByStatus(Status.FINALIZADO));
        
        return relatorio;
    }

    public List<Pedido> listarPedidosPendentes() {
        return pedidoRepository.findByStatus(Status.EM_ANDAMENTO);
    }

    public List<Cliente> clientesMaisAtivos(int topN) {
        return clienteRepository.encontrarClientesMaisAtivos(PageRequest.of(0, topN));
    }

    public long totalClientes() {
        return clienteRepository.count();
    }

}
