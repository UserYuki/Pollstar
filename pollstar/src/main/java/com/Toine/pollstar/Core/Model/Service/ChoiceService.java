package com.Toine.pollstar.Core.Model.Service;

import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Repository.Interfaces.IChoiceStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService
{
    IChoiceStorage dal;

    @Autowired
    public ChoiceService(IChoiceStorage dal)
    {
        this.dal = dal;
    }




}
