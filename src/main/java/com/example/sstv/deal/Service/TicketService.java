package com.example.sstv.deal.Service;

import com.example.sstv.deal.DAO.TicketDAO;
import com.example.sstv.deal.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    private TicketDAO ticketDAO;
    @Autowired
    public TicketService(TicketDAO ticketDAO)throws  Exception{
        this.ticketDAO= ticketDAO;
    }
    public void addTicket(Ticket ticket)throws  Exception{ticketDAO.addTicket(ticket);}

    public List<Ticket> getTicketList(String userId){
        return ticketDAO.getTicketList(userId);
    }
    public void useTicket(Ticket ticketNo)throws Exception{
        ticketDAO.useTicket(ticketNo);
    }
    public void TkRecentCoin(Ticket ticket)throws  Exception{
        ticketDAO.TkRecentCoin(ticket);
    }
}
