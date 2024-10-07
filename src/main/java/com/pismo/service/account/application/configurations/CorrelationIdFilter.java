package com.pismo.service.account.application.configurations;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter implements Filter {

    private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // Verifica se existe um CorrelationId no cabeçalho, caso contrário gera um novo.
        String correlationId = httpServletRequest.getHeader(CORRELATION_ID_HEADER_NAME);
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }

        // Define o CorrelationId no MDC para ser capturado nos logs.
        MDC.put("correlationId", correlationId);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove("correlationId"); // Remove o CorrelationId ao final da requisição.
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização, caso necessário
    }

    @Override
    public void destroy() {
        // Limpeza de recursos, caso necessário
    }
}

