package com.springboot.app.controller;


import com.springboot.app.entities.Bid;
import com.springboot.app.exception_handling.NoSuchEntityException;
import com.springboot.app.service.impl.BidServerInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "BidController", description = "BidController | Rest questions")
@RequestMapping("/bidApi")
public class BidController {

    BidServerInterface bidServerInterface;

    @Autowired
    public BidController(BidServerInterface bidServerInterface) {
        this.bidServerInterface = bidServerInterface;
    }

    @GetMapping("/bids")
    @ApiOperation("Get all bids")
    public List<Bid> getBids(){
        return bidServerInterface.getAllBids();
    }

    @GetMapping("/bid/{id}")
    @ApiOperation("Get bid by id")
    public Bid getBid(@PathVariable long id){
        Bid bid =  bidServerInterface.getBid(id);
        if(bid == null){
            throw new NoSuchEntityException("There is no Bid with id = " +
                    id + " in database");
        }
        return bid;
    }

    @PostMapping("/bid")
    @ApiOperation("Save bid")
    public Bid saveBid(@RequestBody Bid bid){
        bidServerInterface.saveOrUpdateBid(bid);
        return bid;
    }

    @DeleteMapping("/bid/{id}")
    @ApiOperation("Delete bid by id")
    public String deleteBid(@PathVariable long id){
        Bid bid = bidServerInterface.getBid(id);
        if(bid == null){
            throw new NoSuchEntityException("There is no Bid with id = "
                    + id + " in database");
        }
        bidServerInterface.deleteBid(id);
        return "Bid with id = " + id + " was delete";
    }
}
