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
import com.shop.vendasonline.repository.VendaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RelatorioService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;
    
    public RelatorioDTO gerarRelatorio() {
        long totalPedidos = pedidoRepository.count();
        
        Double valorTotal = pedidoRepository.sumValorTotal();
        if (valorTotal == null) {
            valorTotal = 0.0;
        }
        
        long totalProdutos = vendaRepository.count();
        
        RelatorioDTO relatorio = new RelatorioDTO();
        relatorio.setTotalPedidos(totalPedidos);
        relatorio.setValorTotalFaturado(valorTotal);
        relatorio.setQuantidadeProdutosVendidos(totalProdutos);
        
        return relatorio;
    }

    public List<Pedido> listarPedidosPendentes() {
        List<Pedido> pedidos = pedidoRepository.findByStatus(Status.EM_ANDAMENTO);
        pedidos.forEach(p -> p.getCliente().getNome());
        return pedidos;
    }

    public List<Cliente> clientesMaisAtivos(int topN) {
        return clienteRepository.encontrarClientesMaisAtivos(PageRequest.of(0, topN));
    }

    public long totalClientes() {
        return clienteRepository.count();
    }

}
