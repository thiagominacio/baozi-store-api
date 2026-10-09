
package br.com.baozistore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.baozistore.model.Pedido;
import br.com.baozistore.repository.ClienteRepository;
import br.com.baozistore.repository.ProdutoRepository;
import br.com.baozistore.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository) {

        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    // Cadastrar um pedido
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Pedido pedido) {

        if (pedido.getClienteId() == null ||
            pedido.getProdutoId() == null ||
            pedido.getQuantidade() == null ||
            pedido.getQuantidade() <= 0) {

            return ResponseEntity.badRequest()
                    .body("Dados do pedido invalidos.");
        }

        if (!clienteRepository.existsById(pedido.getClienteId())) {
            return ResponseEntity.badRequest()
                    .body("Cliente nao encontrado.");
        }

        if (!produtoRepository.existsById(pedido.getProdutoId())) {
            return ResponseEntity.badRequest()
                    .body("Produto nao encontrado.");
        }

        Pedido novoPedido = pedidoRepository.save(pedido);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novoPedido);
    }

    // Listar todos os pedidos
    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    // Consultar pedido pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pedidoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
