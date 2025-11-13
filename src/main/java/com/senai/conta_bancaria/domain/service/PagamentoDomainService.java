package com.senai.conta_bancaria.domain.service;

import com.senai.conta_bancaria.domain.entity.Pagamento;
import com.senai.conta_bancaria.domain.entity.StatusPagamento;
import com.senai.conta_bancaria.domain.exceptions.SaldoInsuficienteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PagamentoDomainService {
    public Pagamento pagamento(Pagamento pagamento){
        try {
            BigDecimal taxasSomadas = BigDecimal.valueOf(0.0);
            BigDecimal taxas;

            //TODO: taxas
//            pagamento.getTaxas().forEach(taxa -> {
//                taxas = taxa.getPercentual().add(BigDecimal.valueOf(1));
//                taxasSomadas = porcentagem.multiply(taxasSomadas);
//            });

            var valorFinal = pagamento.getValorPago().add(pagamento.getValorPago().multiply(taxasSomadas));
            pagamento.getConta().sacar(valorFinal);
            pagamento.setStatus(StatusPagamento.SUCESSO);
        } catch (SaldoInsuficienteException ex){
            pagamento.setStatus(StatusPagamento.SALDO_INSUFICIENTE);
        } catch (Exception ex){
            pagamento.setStatus(StatusPagamento.FALHA);
        }

        return pagamento;
    }
}
