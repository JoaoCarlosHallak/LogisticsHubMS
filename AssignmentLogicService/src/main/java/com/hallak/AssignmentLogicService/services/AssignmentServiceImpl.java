package com.hallak.AssignmentLogicService.services;

import com.hallak.shared_libraries.dtos.OrderDTO;
import com.hallak.shared_libraries.dtos.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {



    private static final Logger log = LoggerFactory.getLogger(AssignmentServiceImpl.class);

    @Autowired
    public AssignmentServiceImpl(){
    }



    @Override
    @RabbitListener(queues = "{rabbitmq.queue.order}")
    public Object assignmentOrderAndVehicle(@Payload OrderDTO orderDTO) {
        Specification specification = orderDTO.getSpecification();
        Double weight = orderDTO.getWeight();
        return Object.class;

    }
}
