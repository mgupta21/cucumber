package com.java.cucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgupta on 9/3/16.
 */
public class TicTacToeStepDef {

    List<List<String>> board = new ArrayList<>();

    @Given("^a board like this:$")
    public void aBoardLikeThis(DataTable board) throws Throwable {
        board.raw().forEach(r -> this.board.add(new ArrayList<>(r)));
    }

    @When("^player x plays in row (\\d+), column (\\d+)$")
    public void playerXPlaysInRowColumn(int row, int col) throws Throwable {
        board.get(row).set(col, "x");
    }

    @Then("^board should look like this$")
    public void boardShouldLookLikeThis(DataTable expectedBoard) throws Throwable {
        expectedBoard.diff(board);
    }

}
