package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Model.Choice;

import java.util.ArrayList;
import java.util.List;

public class ChoiceContainer {
    private List<Choice> choices;



    public ChoiceContainer()
    {
        choices = new ArrayList<>();
    }

    public Choice CreateChoice(String name)
    {
        Choice temp = new Choice(choices.size(), name);
        return temp;
    }

}
