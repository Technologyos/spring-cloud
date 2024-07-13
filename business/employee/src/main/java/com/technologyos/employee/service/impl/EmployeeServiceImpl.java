package com.technologyos.employee.service.impl;

import com.technologyos.employee.service.IEmployeeService;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private WebClient.Builder taskClient;

    private final TcpClient tcpClient = TcpClient
        .create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
        .doOnConnected(connection -> {
            connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
        });

    @PostConstruct
    public void init(){
        //this.taskClient.baseUrl("http://task-service/api/v1/task").build();
    }


    @Override
    public void getTasks() {
        WebClient client = taskClient.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
            .baseUrl("http://task-service/api/v1/task")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        List<Object> block = client.method(HttpMethod.GET)
            .retrieve()
            .bodyToFlux(Object.class)
            .collectList()
            .block();
        System.out.println("---------");
        System.out.println("---------");
        System.out.println(block);
    }
}
