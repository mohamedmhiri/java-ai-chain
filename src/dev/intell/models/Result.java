package dev.intell.models;


public class Result {

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Result(String input) {
        this.input = input;
    }

    public Result() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.getInput())
                .append(this.getText())
                .append(this.getOutput())
                .toString();
    }

    private String input;
    private String output;
    private String text;
}
