package com.gm_digital.cursomc.services;

import com.gm_digital.cursomc.domain.ItemPedido;
import com.gm_digital.cursomc.domain.PagamentoComBoleto;
import com.gm_digital.cursomc.domain.Pedido;
import com.gm_digital.cursomc.domain.enums.EstadoPagamento;
import com.gm_digital.cursomc.repositories.ItemPedidoRepository;
import com.gm_digital.cursomc.repositories.PagamentoRepository;
import com.gm_digital.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    private final EmailService emailService;

    public PedidoService(EmailService emailService) {
        this.emailService = emailService;
    }

    public Optional<Pedido> findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstant(new Date());
        obj.setCliente(clienteService.findById(obj.getCliente().getId()).get());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstant());
        }
        obj = repository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.findById(ip.getProduto().getId()).get());
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }

}
