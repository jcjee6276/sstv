package com.example.sstv.deal.Service;

import com.example.sstv.deal.DAO.TicketProductDAO;
import com.example.sstv.deal.TicketProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketProductService {

    private TicketProductDAO ticketProductDAO;
    @Autowired
    public TicketProductService(TicketProductDAO ticketProductDAO){
        this.ticketProductDAO = ticketProductDAO;
    }
    public TicketProduct getTicketProductAdminList(int TicketProductNo){
        return ticketProductDAO.getTicketProductAdminList(TicketProductNo);
    }

    public void addTicketProductAdmin(TicketProduct ticketProduct){
        ticketProductDAO.addTicketProductAdmin(ticketProduct);
    }
    public void removeTicketProductAdmin(TicketProduct ticketProdNo){
        ticketProductDAO.removeTicketProductAdmin(ticketProdNo);
    }


}
