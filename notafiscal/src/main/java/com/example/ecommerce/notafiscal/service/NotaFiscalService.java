package com.example.ecommerce.notafiscal.service;

import com.example.ecommerce.notafiscal.domain.NotaFiscal;
import com.example.ecommerce.notafiscal.infraestructure.message.dto.NovaVendaDTO;
import com.example.ecommerce.notafiscal.repository.NotaFiscalRepository;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {
    private final NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
    }

    public void criarNota(NovaVendaDTO novaVendaDTO) {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setCpfCliente(novaVendaDTO.getCpfCliente());
        notaFiscal.setValor(novaVendaDTO.getTotal());
        notaFiscal.setCodigoRetornoReceita("00000000000-000000000000000-000000000000000000-0000000000000000-000000");
        notaFiscal.setXml("<teste><nota valor=\""+novaVendaDTO.getTotal() + "  \" /> <teste>");
        notaFiscalRepository.save(notaFiscal);
    }
}

